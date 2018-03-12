package com.rexam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rexam.model.TeachingUnit;

public interface TeachingUnitRepository extends CrudRepository<TeachingUnit, String>{

	@Query(value = "select distinct discipline from TeachingUnit")
	public List<String> findDisciplines();
	
	public List<TeachingUnit> findAllByOrderByDisciplineAsc(); 
}
