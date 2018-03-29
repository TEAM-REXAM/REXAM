package com.rexam;

//import org.springframework.boot.test.*;;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class SecurityTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	@Autowired
	private FilterChainProxy filterChainProxy;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).dispatchOptions(true)
				.addFilters(filterChainProxy)

				.build();
	}

	@Test
	public void testStudentValide() throws Exception {

		mockMvc.perform(formLogin("/login").user("srowlands0@vimeo.com").password("toto"))
				.andExpect(redirectedUrl("/rexam/showTeachingUnits"));

	}

	@Test
	public void testStudentInvalide() throws Exception {

		// Le cas avec l'adresse d'email qui est pas bon
		mockMvc.perform(formLogin("/login").user("xxxxxxxxxsdsdsd.com").password("toto"))
				.andExpect(status().is3xxRedirection());
		// Le cas avec le mot de passe qui est pas bon
		mockMvc.perform(formLogin("/login").user("srowlands0@vimeo.com").password("qsdqsd"))
				.andExpect(status().is3xxRedirection());

	}

	@Test
	public void testAdminValide() throws Exception {

		mockMvc.perform(formLogin("/login").user("arthur@noob.com").password("toto"))
				.andExpect(redirectedUrl("/admin/showTU"));

	}

	@Test
	public void testAdminInvalide() throws Exception {

		// Le cas avec l'adresse d'email qui est pas bon
		mockMvc.perform(formLogin("/login").user("xxxxxxxxxsdsdsd.com").password("toto"))
				.andExpect(status().is3xxRedirection());
		// Le cas avec le mot de passe qui est pas bon
		mockMvc.perform(formLogin("/login").user("arthur@noob.com").password("qsdqsd"))
				.andExpect(status().is3xxRedirection());

	}

}
