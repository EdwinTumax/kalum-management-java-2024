package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.kalum.kalummanagement.core.models.dao.entities.CarreraTecnica;

public interface ICarreraTecnicaService {
	public List<CarreraTecnica> findAll();
	public Page<CarreraTecnica> findAll(Pageable pageable);
	public List<CarreraTecnica> findAllByFilter(String filter);
	public CarreraTecnica findById(String id);
	public CarreraTecnica save(CarreraTecnica carreraTecnica);
	public void delete(CarreraTecnica carreraTecnica);
}
