package edu.kalum.kalummanagement.core.utilities;

import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

import com.google.gson.Gson;
import com.rabbitmq.client.AuthenticationFailureException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import edu.kalum.kalummanagement.core.models.domains.ErrorLog;
import edu.kalum.kalummanagement.core.models.domains.ErrorResponse;
import edu.kalum.kalummanagement.core.models.dtos.CandidateProcessRequestDTO;
import edu.kalum.kalummanagement.core.models.dtos.OrderCandidateDTO;
import edu.kalum.kalummanagement.core.models.dtos.OrderDataCandidateDTO;

@PropertySource("classpath:application-${SPRING_PROFILES_ACTIVE}.properties")
@Component
public class Utils {

	@Autowired
	private Gson gson;
	
	@Autowired
	private Environment env;

	private Logger logger = LoggerFactory.getLogger(Utils.class);

	public ErrorResponse getErrorResponse(String code, String description) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(code);
		if (code.equalsIgnoreCase("001")) {
			errorResponse.setErrorType("MSG");
			errorResponse.setErrorCode("400");
			errorResponse.setDescription(description);
		} else if (code.equalsIgnoreCase("002")) {
			errorResponse.setErrorType("MSG");
			errorResponse.setErrorCode("403");
			errorResponse.setDescription(description);
		} else if (code.equalsIgnoreCase("003")) {
			errorResponse.setErrorType("COM");
			errorResponse.setErrorCode("503");
			errorResponse.setDescription(description);
		}
		if (code.equalsIgnoreCase("004")) {
			errorResponse.setErrorType("ISE");
			errorResponse.setErrorCode("500");
			errorResponse.setDescription(description);
		}
		return errorResponse;
	}

	public void log(int responseCode, String message, String logLevel, HttpServletRequest request, Long initialTime) {
		ErrorLog log = new ErrorLog();
		log.setApiName("Api-KalumManagement");
		log.setHostName(request.getHeader(HttpHeaders.HOST));
		log.setApiKey(request.getHeader("Authorization") != null
				? request.getHeader("Authorization").toString().split(" ")[1].split("\\.")[1]
				: "");
		log.setUri(request.getRequestURI());
		log.setResponseCode(responseCode);
		log.setResponseTime(new Date().getTime() - initialTime);
		log.setIp(request.getRemoteAddr());
		log.setMethod(request.getMethod());
		log.setMessage(message);
		log.setDateTime(this.dateFormat(new Date()));
		log.setVersion("1.0");
		if (logLevel.equalsIgnoreCase("debug")) {
			log.setLogLevel((byte) 10);
			logger.debug(gson.toJson(log));
		} else if (logLevel.equalsIgnoreCase("info")) {
			log.setLogLevel((byte) 20);
			logger.info(gson.toJson(log));
		} else if (logLevel.equalsIgnoreCase("warning")) {
			log.setLogLevel((byte) 30);
			logger.warn(gson.toJson(log));
		} else if (logLevel.equalsIgnoreCase("error")) {
			log.setLogLevel((byte) 40);
			logger.error(gson.toJson(log));
		} else if (logLevel.equalsIgnoreCase("critical")) {
			log.setLogLevel((byte) 50);
			logger.trace(gson.toJson(log));
		} else {
			log.setLogLevel((byte) 0);
			logger.debug(gson.toJson(log));
		}
	}

	public String dateFormat(Date fecha) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return format.format(fecha);
	}

	public OrderCandidateDTO createOrderCandidate(CandidateProcessRequestDTO request) throws ConnectException, AuthenticationFailureException, Exception {
		OrderCandidateDTO result = null;
		Connection connection = null;
		Channel channel = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(env.getRequiredProperty("edu.kalum.config.broker.host"));
		connectionFactory.setPort(Integer.parseInt(env.getRequiredProperty("edu.kalum.config.broker.port")));
		connectionFactory.setUsername(env.getRequiredProperty("edu.kalum.config.broker.user"));
		connectionFactory.setPassword(env.getRequiredProperty("edu.kalum.config.broker.password"));
		connectionFactory.setVirtualHost(env.getRequiredProperty("edu.kalum.config.broker.virtual-host"));
		connection = connectionFactory.newConnection();
		channel = connection.createChannel();
		OrderCandidateDTO order = new OrderCandidateDTO();
		order.setOrderId(UUID.randomUUID().toString());
		order.setOrderDate(new Date());
		order.setStatus("INPROGRESS");
		OrderDataCandidateDTO data = new OrderDataCandidateDTO();
		data.setApellidos(request.getApellidos());
		data.setNombres(request.getNombres());
		data.setDireccion(request.getDireccion());
		data.setTelefono(request.getTelefono());
		data.setEmail(request.getEmail());
		data.setCarreraId(request.getCarreraId());
		data.setExamenId(request.getExamenId());
		data.setJornadaId(request.getJornadaId());
		order.setData(data);
		String message = gson.toJson(order);
		channel.basicPublish(env.getRequiredProperty("edu.kalum.config.broker.exchange"),env.getRequiredProperty("edu.kalum.config.broker.router-key"), null, message.getBytes(StandardCharsets.UTF_8));
		result = order;
		if (channel != null && connection != null) {
			channel.close();
			connection.close();
		}
		return result;
	}

	public Map<String, Object> getConnectException(Map<String, Object> response, ConnectException e,
			HttpServletRequest request, Long initialTime) {
		this.log(503, e.getMessage(), "error", request,
				initialTime);
		ErrorResponse errorResponse = this.getErrorResponse("003", "Error al momento de conectarse al broker de Rabbit");
		response.put("error", errorResponse);
		return response;
	}
	
	public Map<String, Object> getAuthenticationFailureException(Map<String, Object> response, AuthenticationFailureException e,
			HttpServletRequest request, Long initialTime) {
		this.log(503, e.getMessage(), "error", request,
				initialTime);
		ErrorResponse errorResponse = this.getErrorResponse("003", "Error de autenticación con Rabbit");
		response.put("error", errorResponse);
		return response;
	}
	
	public Map<String, Object> getTransactionException(Map<String, Object> response, CannotCreateTransactionException e,
			HttpServletRequest request, Long initialTime) {
		this.log(503, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()), "error", request,
				initialTime);
		ErrorResponse errorResponse = this.getErrorResponse("003", "Error al momento de conectarse a la base de datos");
		response.put("error", errorResponse);
		return response;
	}
	
	

	public Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e,
			HttpServletRequest request, Long initialTime) {
		this.log(503, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()), "error", request,
				initialTime);
		ErrorResponse errorResponse = this.getErrorResponse("003",
				"Error al momento de ejecutar la consulta a la base de datos");
		response.put("error", errorResponse);
		return response;
	}
}
