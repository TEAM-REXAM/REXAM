package com.rexam.securityTest;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rexam.App;
import com.rexam.WebSecurityConfig;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.*;;






@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
//@SpringApplicationConfiguration(classes = {App.class, WebSecurityConfig.class})
public class SecurityTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private UserDetailsService userDetailsService;
    @Autowired
    private FilterChainProxy filterChainProxy;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .dispatchOptions(true)
                .addFilters(filterChainProxy)
                .build();
    }
    
    @Test
    public void testUserAccessForAccount() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isOk());
//        mockMvc.perform(get("*/rexam/*")).andExpect(status().isOk());
    }
    
	
}
