package com.datasol.cuponza.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.dao.UserDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Authority;
import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.UserService;
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true ,rollbackFor = Exception.class) 
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	SaltSource saltSource;
	
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public User getUserByEmail(String email) throws ServiceException {
		try {
			return userDao.getUserByEmail(email);
		} catch (DaoException e) {
			log.error("error getting user with email "+email +" stack: "+e);
			throw new ServiceException("error getting user with email "+email);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false ,rollbackFor = Exception.class) 
	public void insertUser(User user) throws ServiceException {
		log.debug("Starting user registration");
		//TODO:set as false until we send validation email
		user.setEnabled(true);
		user.setRegistrationDate(new Date());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			Authority authority = userDao.getAuthorityByName(Authority.NORMAL_USER_ROLE);
			user.setAuthority(authority);
			userDao.insertUser(user);
			log.debug("completed user registration");
		} catch (DaoException e) {
			log.error("error registering user with email "+user.getEmail());
			throw new ServiceException("error saving user with email "+user.getEmail());
		}

	}

}
