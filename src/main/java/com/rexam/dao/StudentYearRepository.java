package com.rexam.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rexam.model.IdStudentYear;
import com.rexam.model.Student;
import com.rexam.model.StudentYear;

public interface StudentYearRepository extends CrudRepository<StudentYear, IdStudentYear>{

	public StudentYear findById_YearAndStudent(int year, Student student);
	
	public List<StudentYear> findByStudent(Student student);
	
}
