package com.datasol.cuponza.unit.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class AuthServiceTest {
	
	@Autowired
	UserService userService;
	
	
	@Test
	public void authenticateUser(){
		User userDetails =(User)userService.loadUserByUsername("uri@test.com");
		assertEquals("Uri",userDetails.getFirstName());
		Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());
		assertNull(SecurityContextHolder.getContext());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
