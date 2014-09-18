package com.datasol.cuponza.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.exception.UserAlreadyExistsException;
import com.datasol.cuponza.model.User;

public interface UserService {
	
	public User getUserByEmail(String email) throws ServiceException;
	public void insertUser(User user) throws ServiceException,UserAlreadyExistsException;
	public void activateUser(String email,String uuid) throws ServiceException;
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
