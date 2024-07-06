package edu.kalum.kalummanagement.core.models.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="inscripcion_pago")
public class InscripcionPago implements Serializable {
	@EmbeddedId
	private InscripcionPagoId inscripcionPagoId;
	@Column(name="fecha_pago")
	private Date fechaPago;
	@Column(name="monto")
	private Double monto;
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name="no_expediente", referencedColumnName="no_expediente", insertable=false, updatable=false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Aspirante aspirante; 	
}