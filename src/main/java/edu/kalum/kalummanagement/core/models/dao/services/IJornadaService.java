package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.kalum.kalummanagement.core.models.dao.entities.Jornada;

public interface IJornadaService {
	public List<Jornada> findAll();
	public Page<Jornada> findAll(Pageable pageable);
	public List<Jornada> findAllByFilter(String filter);
	public Jornada findById(String id);
	public Jornada save(Jornada jornada);
	public void delete(Jornada jornada);
}
