package com.rexam;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.StudentRepository;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.Student;
import com.rexam.model.TeachingUnit;
import com.rexam.model.User;
import com.rexam.service.RegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ResultEditionServiceTest {

	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private TeachingUnitRepository teachingUnitRepository;
	@Autowired
	private StudentRepository studentRepository;
	
//	@Autowired
//	private ResultEditionService resService;

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
}
