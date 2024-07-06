package edu.kalum.kalummanagement.core.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPago;
import edu.kalum.kalummanagement.core.models.dao.entities.InscripcionPagoId;

public interface IInscripcionPagoDao extends JpaRepository<InscripcionPago,InscripcionPagoId> {
	@Query("select ip from InscripcionPago ip where ip.inscripcionPagoId.noExpediente = ?1")
	public List<InscripcionPago> findByExpediente(String noExpediente);
}
