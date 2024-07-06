package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.kalum.kalummanagement.core.models.dao.entities.ExamenAdmision;

public interface IExamenAdmisionService {
	public List<ExamenAdmision> findAll();
	public Page<ExamenAdmision> findAll(Pageable pageable);
	public List<ExamenAdmision> findAllByFilter(int anio);
	public ExamenAdmision findById(String id);
	public ExamenAdmision save(ExamenAdmision examenAdmision);
	public void delete(ExamenAdmision examenAdmision);
}
