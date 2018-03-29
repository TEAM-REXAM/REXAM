package com.rexam;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rexam.dao.CurrentYearRepository;
import com.rexam.dao.ExamRepository;
import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.ResultRepository;
import com.rexam.model.Registration;
import com.rexam.model.Result;
import com.rexam.service.RegistrationService;
import com.rexam.service.ResultEditionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ResultEditionServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RegistrationService regService;

    @Autowired
    ResultRepository rRepository;

    @Autowired
    ExamRepository exRepository;

    @Autowired
    RegistrationRepository regRepo;

    @Autowired
    ResultEditionService resService;

    @Autowired
    CurrentYearRepository cYearRepo;

    // non vide
    @Test
    @WithMockUser(authorities = { "student", "admin" })
    public void testShowAllUnits() throws Exception {
        regService.registration("srowlands0@vimeo.com", "ENSPHCU89");
        this.mockMvc.perform(get("/admin/showTU")).andDo(print())
                .andExpect(view().name("teachingUnits"))
                .andExpect(model().attributeExists("tuList"));
    }

    // vide
    @Test
    @WithMockUser(authorities = { "student", "admin" })
    public void testShowAllUnits2() throws Exception {
        this.mockMvc.perform(get("/admin/showTU")).andDo(print())
                .andExpect(view().name("teachingUnits"))
                .andExpect(model().attribute("tuList", hasSize(0)));
    }

    @Test
    public void oneRegTest() throws Exception {

        regService.registration("srowlands0@vimeo.com", "ENSPHCU89");

        List<Result> results = new ArrayList<Result>();
        results.addAll(rRepository.findByExam(exRepository.findOne("989")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());

        rRepository.save(results);

        resService.computeAvg(exRepository.findOne("989"));
        resService.updateStatus(exRepository.findOne("989"));

        for (Registration reg : regRepo.findAll()) {
            assert (reg.getAverageScore().equals(5.0));
            assertEquals(reg.getStatus(), "Partiellement calculable");
        }

        results.clear();
        results.addAll(rRepository.findByExam(exRepository.findOne("1989")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());
        rRepository.save(results);

        resService.computeAvg(exRepository.findOne("1989"));
        resService.updateStatus(exRepository.findOne("1989"));

        for (Registration reg : regRepo.findAll()) {
            assert (reg.getAverageScore().equals(12.5));
            assertEquals(reg.getStatus(), "Partiellement calculable");
        }

        results.clear();
        results.addAll(rRepository.findByExam(exRepository.findOne("94")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());

        rRepository.save(results);
        resService.computeAvg(exRepository.findOne("94"));
        resService.updateStatus(exRepository.findOne("94"));

        for (Registration reg : regRepo.findAll()) {
            assert (reg.getAverageScore().equals(15.0));
            assertEquals(reg.getStatus(), "Complétement calculable");
        }
    }

    @Test
    public void mutualRegTest() throws Exception {

        regService.registration("srowlands0@vimeo.com", "ENSPHCU18");
        regService.registration("srowlands0@vimeo.com", "ENSBBCU33");

        List<Result> results = new ArrayList<Result>();
        results.addAll(rRepository.findByExam(exRepository.findOne("100")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());

        rRepository.save(results);

        resService.computeAvg(exRepository.findOne("100"));
        resService.updateStatus(exRepository.findOne("100"));

        for (Registration reg : regRepo.findAll()) {
            assertEquals(reg.getStatus(), "Partiellement calculable");
            if (reg.getTeachingUnit().getCode().equals("ENSBBCU33"))
                assert (reg.getAverageScore().equals(9.0));
            else {
                System.out.println(reg.getAverageScore());
                assert (reg.getAverageScore().equals(3.75));

            }
        }
    }

    @Test
    public void annualRegTest() throws Exception {

        regService.registration("srowlands0@vimeo.com", "ENSPHCU18");

        List<Result> results = new ArrayList<Result>();
        results.addAll(rRepository.findByExam(exRepository.findOne("100")));
        results.get(0).setScore(15.0);
        results.get(0).setDateObtened(LocalDate.now().toString());

        rRepository.save(results);

        resService.computeAvg(exRepository.findOne("100"));
        resService.updateStatus(exRepository.findOne("100"));

        for (Registration reg : regRepo.findAll()) {
            assertEquals(reg.getStatus(), "Partiellement calculable");
            assert (reg.getAverageScore().equals(3.75));
        }
        cYearRepo.updateYear(2020);
        regService.registration("srowlands0@vimeo.com", "ENSPHCU18");

        for (Registration reg : regRepo.findAll()) {
            if (reg.getStudentYear().getId().getYear() == 2020) {
                assertEquals(reg.getStatus(), "Pas de Notes");
                assert (reg.getAverageScore()==null);
            }
        }

    }
}
