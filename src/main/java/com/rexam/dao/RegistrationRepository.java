package com.rexam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rexam.model.IdRegistration;
import com.rexam.model.Registration;
import com.rexam.model.Student;
import com.rexam.model.StudentYear;
import com.rexam.model.TeachingUnit;

public interface RegistrationRepository extends CrudRepository<Registration, IdRegistration>{


	public List<Registration> findByIdCodeTeachingUnit(String codeTeachingUnit);

	public List<Registration> findAllByOrderByIdAsc();
	
	public List<Registration> findByStudentYear (StudentYear studentYear);
	
	public List<Registration> findByStudentYear_Student (Student student);
	
	@Query(value="select DISTINCT tu from CurrentYear cy, Registration reg, TeachingUnit tu where reg.teachingUnit = tu and reg.studentYear.id.year = cy.year order by tu.discipline ASC")
	public List<TeachingUnit> findTeachingUnits();
	
	@Query(value="select DISTINCT tu.discipline from CurrentYear cy, Registration reg, TeachingUnit tu where reg.teachingUnit = tu and reg.studentYear.id.year = cy.year")
    public List<String> findDisciplines();
	
	@Query(value="select r from Registration r where teachingUnit =?1 and averageScore>=10")
	public List<Registration> findCapitalizedTu(TeachingUnit tu);
	
}
