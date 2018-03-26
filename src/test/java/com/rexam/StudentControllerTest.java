package com.rexam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rexam.controller.StudentController;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.TeachingUnit;
import com.rexam.service.AuthentificationFacade;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

	@Autowired
	private TeachingUnitRepository tuRep;
	@Autowired
	AuthentificationFacade authentificationFacade;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testShowAllUnits() throws Exception {
		Iterable<TeachingUnit> tuList = tuRep.findAll();
		 this.mockMvc.perform(get("/rexam/showTeachingUnits")).andDo(print()).andExpect(status().isOk())
		 .andExpect(view().name("teachingUnits")
		 
				 );
	}
	
	

}
