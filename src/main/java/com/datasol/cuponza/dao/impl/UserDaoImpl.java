package com.datasol.cuponza.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datasol.cuponza.dao.UserDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Authority;
import com.datasol.cuponza.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Override
	public User getUserByEmail(String email) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getting user by email: "+email);
		User user   = (User) session
				.createQuery("from User u where u.email= :email")
				.setParameter("email", email).uniqueResult();
		return user;
	}

	@Override
	public void insertUser(User user) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("new user has joined with email:  "+user.getEmail());
		session.saveOrUpdate(user);	

	}

	@Override
	public Authority getAuthorityByName(String authorityName)
			throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		Authority authority =  (Authority) session
				.createQuery("from Authority a where a.authorityName= :authorityName")
				.setParameter("authorityName", authorityName).uniqueResult();
		return authority;
	}

}
