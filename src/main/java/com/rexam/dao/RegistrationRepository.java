package com.rexam.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rexam.model.IdRegistration;
import com.rexam.model.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, IdRegistration>{

	public List<Registration> findByIdCodeTeachingUnit(String codeTeachingUnit);
}
