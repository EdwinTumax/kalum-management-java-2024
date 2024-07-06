package edu.kalum.kalummanagement.core.models.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jornada")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jornada implements Serializable {
	@Id
	@Column(name="jornada_id")
	private String jornadaId;
	@Column(name="jornada")
	private String jornada;
	@Column(name="descripcion")
	private String descripcion;
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(mappedBy = "jornada", fetch = FetchType.LAZY)
	private List<Aspirante> aspirantes;
}
