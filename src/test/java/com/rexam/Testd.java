package com.rexam;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.StudentRepository;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.Component;
import com.rexam.model.Exam;
import com.rexam.model.IdRegistration;
import com.rexam.model.IdStudentYear;
import com.rexam.model.Registration;
import com.rexam.model.Student;
import com.rexam.model.StudentYear;
import com.rexam.model.TeachingUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class Testd {

	 @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private TeachingUnitRepository teachingUnitRepository;

	TeachingUnit teachingUnit = new TeachingUnit();
	Student student = new Student();

	@Before
	public void setUp() {

		student.setConnected(true);
		student.setEmail("mail");
		student.setPassword("pass");
		student.setRole("Etu");
		student.setFirstname("toto");
		student.setLastname("jean");

		teachingUnit.setCode("toto");
		Exam e = new Exam();
		e.setCode("tp 1");
		Component c = new Component();
		c.setWeight(0);
		c.setExam(e);
		List<Component> list = new ArrayList<Component>();
		list.add(c);
		teachingUnit.setComponents(list);

	}

	@Test
	public void saveTest() {

		studentRepository.save(student);
		assertNotNull(studentRepository.findOne("mail"));
	}

	@Test
	public void saveTU() {
		System.out.println(bCryptPasswordEncoder.encode("toto"));
	}

	@Test
	public void saveStudentYear() {
		teachingUnitRepository.save(teachingUnit);
		assertNotNull(teachingUnitRepository.findOne("toto"));
		IdStudentYear idStudentYear = new IdStudentYear();
		idStudentYear.setYear(2003);
		idStudentYear.setId(1);
		StudentYear studentYear = new StudentYear();
		studentYear.setStudent(studentRepository.findOne("mail"));
		studentYear.setId(idStudentYear);
		List<Registration> list = new ArrayList<Registration>();

		IdRegistration idRegistration = new IdRegistration();
		idRegistration.setIdStudentYear(studentYear.getId());
		idRegistration.setCodeTeachingUnit(teachingUnitRepository.findOne("toto").getCode());
		Registration registration = new Registration();
		registration.setStudentYear(studentYear);
		registration.setStatus("calculable");
		registration.setId(idRegistration);
		registration.setTeachingUnit(teachingUnitRepository.findOne("toto"));
		list.add(registration);
		studentYear.setRegistration(list);
		registrationRepository.save(registration);

		assertNotNull(registrationRepository.findAll());
	}

}
