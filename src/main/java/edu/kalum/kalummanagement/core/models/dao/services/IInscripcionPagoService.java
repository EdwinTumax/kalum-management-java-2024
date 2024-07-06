package edu.kalum.kalummanagement.core.models.dao.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPago;
import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPagoId;

public interface IInscripcionPagoService {
	public List<InscripcionPago> findByExpediente(String noExpediente);
	public List<InscripcionPago> findAll();
	public Page<InscripcionPago> findAll(Pageable pageable);
	public InscripcionPago findById(InscripcionPagoId id);
	public InscripcionPago save(InscripcionPago inscripcionPago);
	public void delete(InscripcionPago inscripcionPago);
}
