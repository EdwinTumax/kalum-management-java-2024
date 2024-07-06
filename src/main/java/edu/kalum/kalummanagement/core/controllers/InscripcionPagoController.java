package edu.kalum.kalummanagement.core.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPago;
import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPagoId;
import edu.kalum.kalummanagement.core.models.dao.services.IInscripcionPagoService;
import edu.kalum.kalummanagement.core.models.dtos.InscripcionPagoCreateDTO;
import edu.kalum.kalummanagement.core.utilities.Utils;

@RestController
@RequestMapping("/kalum-management/v1")
public class InscripcionPagoController {

	@Autowired
	private IInscripcionPagoService inscripcionPagoService;
	@Autowired
	private Utils utils;
	
	private Long initialTime = 0L;
	
	@PostMapping("/inscripcion-pago")
	public ResponseEntity<?> inscripcionPagoCreate(@Valid @RequestBody InscripcionPagoCreateDTO value, BindingResult result, HttpServletRequest request){
		this.initialTime = new Date().getTime();
		Map<String,Object> response = new HashMap();
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(error -> error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errores", errores);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try {
			InscripcionPagoId id = new InscripcionPagoId();
			id.setBoletaPago(value.getBoletaPago());
			id.setNoExpediente(value.getNoExpediente());
			id.setAnio(value.getAnio());
			InscripcionPago inscripcionPago = new InscripcionPago();
			inscripcionPago.setInscripcionPagoId(id);
			inscripcionPago.setFechaPago(value.getFechaPago());
			inscripcionPago.setMonto(value.getMonto());
			this.inscripcionPagoService.save(inscripcionPago);
			response.put("message","El registro del pago fue realiado exitosamente");
			response.put("data",inscripcionPago);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}catch(CannotCreateTransactionException e) {
			response = this.utils.getTransactionException(response,e, request, this.initialTime);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
		}catch(DataAccessException e) {
			response = this.utils.getDataAccessException(response,e, request, this.initialTime);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
		}		
	}
	
}
