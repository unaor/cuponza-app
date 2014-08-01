package com.datasol.cuponza.dao;

import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Authority;
import com.datasol.cuponza.model.User;

public interface UserDao {
	
	public User getUserByEmail(String email) throws DaoException;
	public void insertUser(User user) throws DaoException;
	public Authority getAuthorityByName(String authorityName) throws DaoException;

}
