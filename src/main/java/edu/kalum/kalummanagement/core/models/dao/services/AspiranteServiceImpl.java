package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import edu.kalum.kalummanagement.core.models.dao.IAspiranteDao;
import edu.kalum.kalummanagement.core.models.dao.entities.Aspirante;

@Service
public class AspiranteServiceImpl implements IAspiranteService {
	
	@Autowired
	private IAspiranteDao aspiranteDao;

	@Override
	public List<Aspirante> findAll() {
		return aspiranteDao.findAll();
	}

	@Override
	public Page<Aspirante> findAll(Pageable pageable) {
		return aspiranteDao.findAll(pageable);
	}

	@Override
	public List<Aspirante> findAllAspiranteByFilter(String filter) {
		return aspiranteDao.findAllAspiranteByFilter(filter);
	}

	@Override
	public Aspirante findById(String noExpediente) {
		return aspiranteDao.findById(noExpediente).orElse(null);
	}

	@Override
	public Aspirante save(Aspirante aspirante) {
		return aspiranteDao.save(aspirante);
	}

	@Override
	public void delete(Aspirante aspirante) {
		aspiranteDao.delete(aspirante);
	}

}
