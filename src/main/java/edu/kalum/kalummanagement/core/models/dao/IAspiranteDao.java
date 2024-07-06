package edu.kalum.kalummanagement.core.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.kalum.kalummanagement.core.models.dao.entities.Aspirante;

public interface IAspiranteDao extends JpaRepository <Aspirante,String>{
	
	@Query("select a from Aspirante a where a.apellidos like %?1%")
	public List<Aspirante> findAllAspiranteByFilter(String filter);
}
