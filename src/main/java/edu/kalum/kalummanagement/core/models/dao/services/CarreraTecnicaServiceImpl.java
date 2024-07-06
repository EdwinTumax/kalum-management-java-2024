package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.kalum.kalummanagement.core.models.dao.ICarreraTecnicaDao;
import edu.kalum.kalummanagement.core.models.dao.entities.CarreraTecnica;

@Service
public class CarreraTecnicaServiceImpl implements ICarreraTecnicaService {
	
	@Autowired
	private ICarreraTecnicaDao carreraTecnicaDao;

	@Override
	public List<CarreraTecnica> findAll() {
			return this.carreraTecnicaDao.findAll();
	}

	@Override
	public Page<CarreraTecnica> findAll(Pageable pageable) {
		return this.carreraTecnicaDao.findAll(pageable);
	}

	@Override
	public List<CarreraTecnica> findAllByFilter(String filter) {
		return this.carreraTecnicaDao.findAllByFilter(filter);
	}

	@Override
	public CarreraTecnica findById(String id) {
		return this.carreraTecnicaDao.findById(id).orElse(null);
	}

	@Override
	public CarreraTecnica save(CarreraTecnica carreraTecnica) {
		return this.carreraTecnicaDao.save(carreraTecnica);
	}

	@Override
	public void delete(CarreraTecnica carreraTecnica) {
		this.carreraTecnicaDao.delete(carreraTecnica);
	}

}
