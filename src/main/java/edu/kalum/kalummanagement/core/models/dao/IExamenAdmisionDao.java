package edu.kalum.kalummanagement.core.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.kalum.kalummanagement.core.models.dao.entities.ExamenAdmision;

public interface IExamenAdmisionDao extends JpaRepository<ExamenAdmision,String> {
	@Query("select ea from ExamenAdmision ea where extract(YEAR,ea.fechaExamen) = ?1")
	public List<ExamenAdmision> findAllByAnio(int anio);
}
