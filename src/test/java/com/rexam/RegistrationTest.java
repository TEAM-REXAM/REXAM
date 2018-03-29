package com.rexam;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rexam.dao.CurrentYearRepository;
import com.rexam.dao.ExamRepository;
import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.ResultRepository;
import com.rexam.dao.StudentRepository;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.Result;
import com.rexam.model.Student;
import com.rexam.model.TeachingUnit;
import com.rexam.model.User;
import com.rexam.service.RegistrationService;
import com.rexam.service.ResultEditionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class RegistrationTest {

	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private TeachingUnitRepository teachingUnitRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ResultRepository rRepository;
	@Autowired
	private ExamRepository exRepository;
	@Autowired
	private ResultEditionService resService;
	@Autowired
	private CurrentYearRepository cYearRepo;
	@Autowired
	private RegistrationService registrationService;
	TeachingUnit teachingUnit = new TeachingUnit();
	User student = new Student();

	@Before
	public void setUp() {

	}

	@Test
	public void saveTest() {

		assertNotNull(teachingUnitRepository.findOne("ENSPHCU89"));
	}

	@Test
	public void findOneStudentTest() {
		assertNotNull(studentRepository.findOne("srowlands0@vimeo.com"));
	}

	@Test
	public void registrationWorkingTest() throws Exception {
		registrationService.registration("srowlands0@vimeo.com", "ENSPHCU89");
		registrationService.registration("rsemper2o@comsenz.com", "ENSPHCU89");
		assertNotNull(registrationRepository.findAll());
	}

	@Test(expected = Exception.class)
	public void registrationWrongTUCode() throws Exception {
		registrationService.registration("srowlands0@vimeo.com", "inexistant");
	}
	@Test(expected = Exception.class)
	public void registrationWrongMail() throws Exception {
		registrationService.registration("srowlandzfzefzfzfzefs0@vimeo.com", "ENSPHCU89");
	}
	@Test(expected = Exception.class)
	public void registrationAlreadyRegistered() throws Exception {
		registrationService.registration("srowlands0@vimeo.com", "ENSPHCU89");
		registrationService.registration("srowlands0@vimeo.com", "ENSPHCU89");
	}
	@Test(expected = Exception.class)
    public void registrationCapitalized() throws Exception {
	    registrationService.registration("srowlands0@vimeo.com", "ENSPHCU89");

        List<Result> results = new ArrayList<Result>();
        results.addAll(rRepository.findByExam(exRepository.findOne("989")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());


        results.clear();
        results.addAll(rRepository.findByExam(exRepository.findOne("1989")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());

        results.clear();
        results.addAll(rRepository.findByExam(exRepository.findOne("94")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());

        rRepository.save(results);
        resService.computeAvg(exRepository.findOne("94"));
        resService.updateStatus(exRepository.findOne("94"));
        
        
        cYearRepo.updateYear(2020);
        registrationService.registration("srowlands0@vimeo.com", "ENSPHCU89");
    }
	
	
}
