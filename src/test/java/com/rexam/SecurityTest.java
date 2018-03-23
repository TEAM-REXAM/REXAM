package com.rexam;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import javax.servlet.Filter;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import com.rexam.dao.AdminRepository;
import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.StudentRepository;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.dao.UserRepository;
import com.rexam.model.Student;
import com.rexam.model.TeachingUnit;
import com.rexam.model.User;
import com.rexam.service.RegistrationService;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class SecurityTest {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserRepository userRepository;

//	TeachingUnit teachingUnit = new TeachingUnit();
	User student = new Student();
	
	   @Autowired
	    private WebApplicationContext context;

	    @Autowired
	    private Filter springSecurityFilterChain;

	    private MockMvc mvc;

	@Before
	public void setUp() {
//		
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .addFilters(springSecurityFilterChain)
//                .build();
			mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}
    @Configuration
    @EnableWebSecurity
    @EnableWebMvc
    static class Config extends WebSecurityConfigurerAdapter {
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                .inMemoryAuthentication()
                    .withUser("user").password("password").roles("student");
        }
    }

    @Test
    public void demo() throws Exception {
//        mvc.perform(get("/demo/test").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//               .andExpect(status().isOk())
//               .andExpect(content().contentType("application/json;charset=UTF-8"))
//               .andExpect(content().json("{'foo':'bar'}"));
//    	mvc
  //  	.perform(get("*/rexam/*").with(user("user").password("password").roles("student")));
//    	mvc
//    	.perform(get("*/rexam/*").with(authentication(authentication)));
    
    }
    
    
    
}
