package com.datasol.cuponza.unit.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.exception.UserAlreadyExistsException;
import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.UserService;
import com.datasol.cuponza.util.AuthProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	@Autowired
	SessionFactory sessionFactory;
		
	@Test
	public void sendConfirmationEmail() throws ServiceException{
		User user = new User();
		user.setEmail("u_naor@yahoo.com");
		user.setFirstName("Uri");
		user.setLastName("Naor");
		user.setPassword("123456");
		user.setAuthProvider(AuthProvider.CUPONZA.name());
		try {
			userService.insertUser(user);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@After
	public void tearDown() throws ServiceException{
		User user = userService.getUserByEmail("u_naor@yahoo.com");
		Session session = sessionFactory.getCurrentSession();
		session.delete("User",user);
	}
}
