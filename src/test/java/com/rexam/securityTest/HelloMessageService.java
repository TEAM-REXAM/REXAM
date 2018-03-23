package com.rexam.securityTest;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class HelloMessageService {

	@PreAuthorize("authenticated")
	public String getMessage() {
		Authentication authentication = SecurityContextHolder.getContext()
															.getAuthentication();
		
		return "Hello " + authentication;
	}
}