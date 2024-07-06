package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import edu.kalum.kalummanagement.core.models.dao.IJornadaDao;
import edu.kalum.kalummanagement.core.models.dao.entities.Jornada;

@Service
public class JornadaServiceImpl implements IJornadaService {

	@Autowired
	private IJornadaDao jornadaDao;
	
	@Override
	public List<Jornada> findAll() {
		return this.jornadaDao.findAll();
	}

	@Override
	public Page<Jornada> findAll(Pageable pageable) {
		return this.jornadaDao.findAll(pageable);
	}

	@Override
	public List<Jornada> findAllByFilter(String filter) {
		return this.jornadaDao.findAllByFilter(filter);
	}

	@Override
	public Jornada findById(String id) {
		return this.jornadaDao.findById(id).orElse(null);
	}

	@Override
	public Jornada save(Jornada jornada) {
		return this.jornadaDao.save(jornada);
	}

	@Override
	public void delete(Jornada jornada) {
		this.jornadaDao.delete(jornada);
	}

}
