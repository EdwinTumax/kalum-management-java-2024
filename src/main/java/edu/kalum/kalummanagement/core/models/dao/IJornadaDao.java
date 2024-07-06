package edu.kalum.kalummanagement.core.models.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.kalum.kalummanagement.core.models.dao.entities.Jornada;

public interface IJornadaDao extends JpaRepository<Jornada,String> {
	@Query("select j from Jornada j where j.descripcion like %?1%")
	public List<Jornada> findAllByFilter(String filter);
}

