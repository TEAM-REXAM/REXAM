package com.rexam;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import com.rexam.model.Result;
import com.rexam.service.RegistrationService;
import com.rexam.service.ResultEditionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class StudentControllerTest {


	@Autowired private MockMvc mockMvc;
    @Autowired RegistrationService regService;
    @Autowired ResultRepository rRepository;
    @Autowired ExamRepository exRepository;
    @Autowired RegistrationRepository regRepo;
    @Autowired ResultEditionService resService;
    @Autowired CurrentYearRepository cYearRepo;

	@Test
	@WithMockUser(authorities = { "student"})
	public void testShowAllUnits() throws Exception {

		this.mockMvc.perform(get("/rexam/showTeachingUnits")).andDo(print()).andExpect(status().isOk())
		.andExpect(view().name("teachingUnits"))
		.andExpect(model().attributeExists("tuList")
				);

	}


	/*
	 * Si on n'est pas connecté on ne peut pas acceder à la page 
	 */
	@Test
	public void testShowAllUnitsWithoutUser() throws Exception {
		this.mockMvc.perform(get("/rexam/showTeachingUnits")).andDo(print()).andExpect(status().is3xxRedirection()
				);
	}

	@Test
	@WithMockUser(authorities = { "student"})
	public void testShowExams() throws Exception {
		String code = "ENSPHCU89";
		this.mockMvc.perform(get("/rexam/showExams").param("code", code)).andDo(print()).andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/view/exams.jsp"))
		.andExpect(view().name("exams"))
		.andExpect(model().attribute("teachingUnit", hasProperty("code", is(code)))
				);
	}

	@Test
	public void testShowExamWithoutUser() throws Exception {
		this.mockMvc.perform(get("/rexam/showExam")).andDo(print()).andExpect(status().is3xxRedirection()
				);
	}

	@Test
	@WithMockUser(authorities = { "student"})
	public void testListRegistration() throws Exception {
		this.mockMvc.perform(get("/rexam/regs")).andDo(print()).andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/view/regslist.jsp"))
		.andExpect(view().name("regslist"))
		.andExpect(model().attributeExists("regs")
				);
	}

	@Test
	@WithMockUser(authorities = { "student"})
	public void testListResults() throws Exception {
		this.mockMvc.perform(get("/rexam/results")).andDo(print()).andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/view/reslist.jsp"))
		.andExpect(view().name("reslist"))
		.andExpect(model().attributeExists("results"))
		.andExpect(model().attributeExists("studOccurences")
				);
	}
	
		@Test
		@WithMockUser(username="srowlands0@vimeo.com", authorities = { "student"})
		public void testDetailResults() throws Exception {
			String year = "2018";
			String codeTU = "ENSPHCU89";
	        regService.registration("srowlands0@vimeo.com", "ENSPHCU89");

	        List<Result> results = new ArrayList<Result>();
	        results.addAll(rRepository.findByExam(exRepository.findOne("989")));
	        results.get(0).setScore(15.0);
	        results.get(0).setDateObtened(LocalDate.now().toString());
	        rRepository.save(results);
	        resService.computeAvg(exRepository.findOne("989"));
	        resService.updateStatus(exRepository.findOne("989"));
	        
			this.mockMvc.perform(get("/rexam/results/{codeTU}" , codeTU).param("year", year)).andDo(print()).andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/view/detailRes.jsp"))
			.andExpect(view().name("detailRes"))
			.andExpect(model().attributeExists("detailRes"))
			.andExpect(model().attributeExists("tuname"))
			.andExpect(model().attributeExists("studyear")
					);
	}

}
