package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import edu.kalum.kalummanagement.core.models.dao.IExamenAdmisionDao;
import edu.kalum.kalummanagement.core.models.dao.entities.ExamenAdmision;

@Service
public class ExamenAdmisionServiceImpl implements IExamenAdmisionService {
	
	@Autowired
	private IExamenAdmisionDao examenAdmisionDao;

	@Override
	public List<ExamenAdmision> findAll() {
		return this.examenAdmisionDao.findAll();
	}

	@Override
	public Page<ExamenAdmision> findAll(Pageable pageable) {
		return this.examenAdmisionDao.findAll(pageable);
	}

	@Override
	public List<ExamenAdmision> findAllByFilter(int anio) {
		return this.examenAdmisionDao.findAllByAnio(anio);
	}

	@Override
	public ExamenAdmision findById(String id) {
		return this.examenAdmisionDao.findById(id).orElse(null);
	}

	@Override
	public ExamenAdmision save(ExamenAdmision examenAdmision) {
		return this.examenAdmisionDao.save(examenAdmision);
	}

	@Override
	public void delete(ExamenAdmision examenAdmision) {
		this.examenAdmisionDao.delete(examenAdmision);
	}

}
