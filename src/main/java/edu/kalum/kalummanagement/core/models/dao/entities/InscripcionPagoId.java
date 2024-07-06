package edu.kalum.kalummanagement.core.models.dao.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class InscripcionPagoId implements Serializable {
	@Column(name="boleta_pago")
	private String boletaPago;
	@Column(name="no_expediente")
	private String noExpediente;
	@Column(name="anio")
	private String anio;
}
