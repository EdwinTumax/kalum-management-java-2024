package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.kalum.kalummanagement.core.models.dao.entities.Aspirante;

public interface IAspiranteService {
	public List<Aspirante> findAll();
	public Page<Aspirante> findAll(Pageable pageable);
	public List<Aspirante> findAllAspiranteByFilter(String filter);
	public Aspirante findById(String noExpediente);
	public Aspirante save(Aspirante aspirante);
	public void delete(Aspirante aspirante);
}
