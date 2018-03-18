package com.rexam.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.StudentRepository;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.IdRegistration;
import com.rexam.model.IdStudentYear;
import com.rexam.model.Registration;
import com.rexam.model.Student;
import com.rexam.model.StudentYear;
import com.rexam.model.TeachingUnit;

public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private TeachingUnitRepository teachingUnitRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	public void registration(String email, String codeUe) {
	
		TeachingUnit teachingUnit = teachingUnitRepository.findOne(codeUe);
		Student student = studentRepository.findOne(email); 
		
		IdStudentYear ids = new IdStudentYear();
		ids.setId(0);
		ids.setYear(1994);
		StudentYear sYear = new StudentYear();
		sYear.setStudent(student);
		sYear.setId(ids);
		IdRegistration idr = new  IdRegistration();
		idr.setCodeTeachingUnit(codeUe);
		idr.setIdStudentYear(ids);
		Registration reg = new Registration();
		reg.setId(idr);
		reg.setStudentYear(sYear);
		reg.setTeachingUnit(teachingUnit);
		
		registrationRepository.save(reg);
	}
}
