package com.datasol.cuponza.service;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.exception.UserAlreadyExistsException;
import com.datasol.cuponza.model.User;

public interface UserService {
	
	public User getUserByEmail(String email) throws ServiceException;
	public void insertUser(User user) throws ServiceException,UserAlreadyExistsException;
	public void activateUser(String email,String uuid) throws ServiceException;
}
