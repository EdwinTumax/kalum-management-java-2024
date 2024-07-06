package edu.kalum.kalummanagement.core.models.dao.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name="examen_admision")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamenAdmision implements Serializable {
	@Id
	@Column(name="examen_id")
	private String examenId;
	@Column(name="fecha_examen")
	private Date fechaExamen;
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(mappedBy="examenAdmision", fetch = FetchType.LAZY)
	private List<Aspirante> aspirantes;
}
