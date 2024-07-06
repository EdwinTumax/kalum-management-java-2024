package edu.kalum.kalummanagement.core.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.kalum.kalummanagement.core.models.dao.entities.CarreraTecnica;
public interface ICarreraTecnicaDao extends JpaRepository<CarreraTecnica,String> {
	@Query("select ct from CarreraTecnica ct where ct.carreraTecnica like %?1%")
	public List<CarreraTecnica> findAllByFilter(String filter);
}