package edu.kalum.kalummanagement.core.controllers;

import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rabbitmq.client.AuthenticationFailureException;

import edu.kalum.kalummanagement.core.models.dao.entities.Aspirante;
import edu.kalum.kalummanagement.core.models.dao.entities.CarreraTecnica;
import edu.kalum.kalummanagement.core.models.dao.entities.ExamenAdmision;
import edu.kalum.kalummanagement.core.models.dao.entities.Jornada;
import edu.kalum.kalummanagement.core.models.dao.services.IAspiranteService;
import edu.kalum.kalummanagement.core.models.dao.services.ICarreraTecnicaService;
import edu.kalum.kalummanagement.core.models.dao.services.IExamenAdmisionService;
import edu.kalum.kalummanagement.core.models.dao.services.IJornadaService;
import edu.kalum.kalummanagement.core.models.domains.ErrorResponse;
import edu.kalum.kalummanagement.core.models.dtos.CandidateProcessRequestDTO;
import edu.kalum.kalummanagement.core.models.dtos.OrderCandidateDTO;
import edu.kalum.kalummanagement.core.utilities.Utils;

@RestController
@RequestMapping("/kalum-management/v1")
public class AspiranteController {

	@Autowired
	private Utils utils;

	@Autowired
	private Gson gson;

	@Autowired
	private IAspiranteService aspiranteService;

	@Autowired
	private ICarreraTecnicaService carreraTecnicaService;

	@Autowired
	private IJornadaService jornadaService;

	@Autowired
	private IExamenAdmisionService examenAdmisionService;

	private Logger logger = LoggerFactory.getLogger(AspiranteController.class);

	private Long initialtime = 0L;

	@GetMapping("/aspirantes")
	public ResponseEntity<?> aspiranteList(HttpServletRequest request) {
		this.initialtime = new Date().getTime();
		Map<String, Object> response = new HashMap();
		logger.debug("Iniciando consulta de aspirante");
		try {
			List<Aspirante> aspirantes = aspiranteService.findAll();
			if (aspirantes != null && aspirantes.size() == 0) {
				logger.warn("No existen registros en la tabla aspirante");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				logger.info("Se proceso la consulta de forma exitosa");
				return new ResponseEntity<List<Aspirante>>(aspirantes, HttpStatus.OK);
			}
		} catch (CannotCreateTransactionException e) {
			response = this.utils.getTransactionException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (DataAccessException e) {
			response = this.utils.getDataAccessException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@GetMapping("/aspirantes/page/{page}")
	public ResponseEntity<?> aspiranteListByPage(@PathVariable Integer page, HttpServletRequest request) {
		this.initialtime = new Date().getTime();
		Map<String, Object> response = new HashMap();
		logger.debug("Iniciando consulta de aspirante");
		try {
			Pageable pageable = PageRequest.of(page, 5);
			Page<Aspirante> aspirantes = aspiranteService.findAll(pageable);
			if (aspirantes != null && aspirantes.getSize() == 0) {
				logger.warn("No existen registros en la tabla aspirante");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				logger.info("Se proceso la consulta de forma exitosa");
				return new ResponseEntity<Page<Aspirante>>(aspirantes, HttpStatus.OK);
			}
		} catch (CannotCreateTransactionException e) {
			response = this.utils.getTransactionException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (DataAccessException e) {
			response = this.utils.getDataAccessException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@GetMapping("/aspirantes/{noExpediente}")
	public ResponseEntity<?> aspiranteShow(@PathVariable String noExpediente, HttpServletRequest request) {
		this.initialtime = new Date().getTime();
		Map<String, Object> response = new HashMap<>();
		try {
			Aspirante aspirante = this.aspiranteService.findById(noExpediente);
			System.out.println(aspirante.getInscripcionPagos().size());
			if (aspirante == null) {
				logger.warn("El aspirante con el número de expediente ".concat(noExpediente).concat(" no existe"));
				return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
			} else {
				logger.info("Se realizo la consulta de forma correcta con el expediente ".concat(noExpediente));
				return new ResponseEntity<Aspirante>(aspirante, HttpStatus.OK);
			}
		} catch (CannotCreateTransactionException e) {
			response = this.utils.getTransactionException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (DataAccessException e) {
			response = this.utils.getDataAccessException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@DeleteMapping("/aspirantes/{noExpediente}")
	public ResponseEntity<?> aspiranteDelete(@PathVariable String noExpediente, HttpServletRequest request) {
		this.initialtime = new Date().getTime();
		Map<String, Object> response = new HashMap<>();
		try {
			Aspirante aspirante = this.aspiranteService.findById(noExpediente);
			if (aspirante == null) {
				logger.warn("mensaje",
						"El aspirante con el número de expediente ".concat(noExpediente).concat(" no existe"));
				response.put("mensaje",
						"El aspirante con el número de expediente ".concat(noExpediente).concat(" no existe"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				this.aspiranteService.delete(aspirante);
				response.put("mensaje",
						"El aspirante con número de expediente ".concat(noExpediente).concat(" fue eliminado"));
				response.put("aspirante", aspirante);
				logger.info("mensaje",
						"El aspirante con número de expediente ".concat(noExpediente).concat(" fue eliminado"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (CannotCreateTransactionException e) {
			response = this.utils.getTransactionException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (DataAccessException e) {
			response = this.utils.getDataAccessException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@PostMapping("/aspirantes/order-process")
	public ResponseEntity<?> candidateProcess(@Valid @RequestBody CandidateProcessRequestDTO aspirante,
			BindingResult result, HttpServletRequest request) {
		this.initialtime = new Date().getTime();
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			CarreraTecnica carreraTecnica = this.carreraTecnicaService.findById(aspirante.getCarreraId());
			if (carreraTecnica == null) {
				ErrorResponse errorResponse = this.utils.getErrorResponse("001",
						"No existe la carrera técnica con el id ".concat(aspirante.getCarreraId()));
				response.put("error", errorResponse);
				this.utils.log(400, gson.toJson(response), "warning", request, this.initialtime);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			ExamenAdmision examenAdmision = this.examenAdmisionService.findById(aspirante.getExamenId());
			if (examenAdmision == null) {
				ErrorResponse errorResponse = this.utils.getErrorResponse("001",
						"No existe la fecha de examen con el id ".concat(aspirante.getExamenId()));
				response.put("error", errorResponse);
				this.utils.log(400, gson.toJson(response), "warning", request, this.initialtime);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			Jornada jornada = this.jornadaService.findById(aspirante.getJornadaId());
			if (jornada == null) {
				ErrorResponse errorResponse = this.utils.getErrorResponse("001",
						"No existe la jornada con el id ".concat(aspirante.getJornadaId()));
				response.put("error", errorResponse);
				this.utils.log(400, gson.toJson(response), "warning", request, this.initialtime);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			OrderCandidateDTO resultSendOrder = utils.createOrderCandidate(aspirante);
			if (resultSendOrder != null) {
				response.put("Mensaje", "La creación de la orden fue exitosa");
				response.put("data", resultSendOrder);
				this.utils.log(201,gson.toJson(resultSendOrder),"info", request, initialtime);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			} else {
				response.put("Mensaje", "Error al momento de crear la solicitud del aspirante");
				this.logger.info("Info: " + aspirante.toString());
				return new ResponseEntity<String>("Sucess", HttpStatus.CONFLICT);
			}
		}catch(ConnectException e) {
			response = this.utils.getConnectException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}catch (CannotCreateTransactionException e) {
			response = this.utils.getTransactionException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (DataAccessException e) {
			response = this.utils.getDataAccessException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (AuthenticationFailureException e) {
			response = this.utils.getAuthenticationFailureException(response, e, request, this.initialtime);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (Exception e) {
			ErrorResponse errorResponse = this.utils.getErrorResponse("004","Error interno en la API");
			this.utils.log(500, e.getMessage(), "error", request, this.initialtime);
			response.put("error", errorResponse);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
