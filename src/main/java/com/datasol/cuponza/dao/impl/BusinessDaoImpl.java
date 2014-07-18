package com.datasol.cuponza.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datasol.cuponza.dao.BusinessDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Business;

@Repository
public class BusinessDaoImpl implements BusinessDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(BusinessDaoImpl.class);

	@Override
	public List<Business> getBusinesses() throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Business").list();
	}

	@Override
	public Business getBusinessByEmail(String email) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getting business by email: "+email);
		Business business   = (Business) session
				.createQuery("from Business b where b.businessEmail= :email")
				.setParameter("email", email).uniqueResult();
		return business;
	}

	@Override
	public void addBusiness(Business business) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("new business has joined with email:  "+business.getBusinessEmail());
		session.saveOrUpdate(business);	
		
	}

	@Override
	public void removeBusiness(Business business) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(business);
		
	}

}
