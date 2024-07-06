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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="carrera_tecnica")
public class CarreraTecnica implements Serializable {
	@Id
	@Column(name="carrera_id")
	private String carreraId;
	@Column(name="carrera_tecnica")
	private String carreraTecnica;
	@OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private List<Aspirante> aspirantes;
}