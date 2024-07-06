package edu.kalum.kalummanagement.core.models.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="aspirante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aspirante implements Serializable {
	@Id
	@Column(name="no_expediente")
	private String noExpediente;
	@Column(name="apellidos")
	private String apellidos;
	@Column(name="nombres")
	private String nombres;
	@Column(name="direccion")
	private String direccion;
	@Column(name="telefono")
	private String telefono;
	@Column(name="email")
	private String email;
	@Column(name="estatus")
	private String estatus;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="carrera_id", referencedColumnName="carrera_id")
	private CarreraTecnica carreraTecnica;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="jornada_id", referencedColumnName="jornada_id")
	private Jornada jornada;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="examen_id", referencedColumnName="examen_id")
	private ExamenAdmision examenAdmision;
	@OneToMany(mappedBy="aspirante", fetch = FetchType.EAGER)
	private List<InscripcionPago> inscripcionPagos;
}
