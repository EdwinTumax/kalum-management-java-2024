package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.kalum.kalummanagement.core.models.dao.IInscripcionPagoDao;
import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPago;
import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPagoId;

@Service
public class InscripcionPagoServiceImpl implements IInscripcionPagoService {

	@Autowired
	private IInscripcionPagoDao inscripcionPagoDao;
	
	@Override
	public List<InscripcionPago> findByExpediente(String noExpediente) {		
		return this.inscripcionPagoDao.findByExpediente(noExpediente);
	}

	@Override
	public List<InscripcionPago> findAll() {
		return this.inscripcionPagoDao.findAll();
	}

	@Override
	public Page<InscripcionPago> findAll(Pageable pageable) {
		return this.inscripcionPagoDao.findAll(pageable);
	}

	@Override
	public InscripcionPago findById(InscripcionPagoId id) {
		return this.inscripcionPagoDao.findById(id).orElse(null);
	}

	@Override
	public InscripcionPago save(InscripcionPago inscripcionPago) {
		return this.inscripcionPagoDao.save(inscripcionPago);
	}

	@Override
	public void delete(InscripcionPago inscripcionPago) {
		this.inscripcionPagoDao.delete(inscripcionPago);
	}

}
