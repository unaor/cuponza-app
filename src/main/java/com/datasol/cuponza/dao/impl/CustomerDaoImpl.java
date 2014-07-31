package com.datasol.cuponza.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datasol.cuponza.dao.CustomerDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Customer;


@Repository
public class CustomerDaoImpl  implements CustomerDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers() throws DaoException {
		//TODO: implement cache for this method
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Customer").list();
	}

	@Override
	public Customer getCustomerByEmail(String email) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getting customer by email: "+email);
		Customer customer   = (Customer) session
				.createQuery("from Customer c where c.contactEmail= :email")
				.setParameter("email", email).uniqueResult();
		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("new customer has joined with email:  "+customer.getContactEmail());
			session.saveOrUpdate(customer);				
	}

	@Override
	public void deleteCustomer(Customer customer) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(customer);		
	}

}
