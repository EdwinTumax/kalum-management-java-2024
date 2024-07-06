package edu.kalum.kalummanagement.core.models.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionPagoCreateDTO implements Serializable {
	@NotEmpty(message="El campo boleta de pago no debe ser vacio")
	private String boletaPago;
	@NotEmpty(message="El campo número de expediente no debe ser vacio")
	private String noExpediente;
	@NotEmpty(message="El campo año no debe ser vacio")
	private String anio;
	@NotNull(message="El campo fecha de pago no debe ser vacio")
	private Date fechaPago;
	@NotNull(message="El campo monto no debe ser vacio")
	private Double monto;
}
