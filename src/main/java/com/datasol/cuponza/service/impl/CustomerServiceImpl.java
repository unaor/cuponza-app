package com.datasol.cuponza.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.dao.CustomerDao;
import com.datasol.cuponza.exception.CustomerExistsException;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Customer;
import com.datasol.cuponza.service.CustomerService;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true ,rollbackFor = Exception.class) 
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	private static final Logger log = Logger.getLogger(CustomerServiceImpl.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false ,rollbackFor = Exception.class) 
	public void addCustomer(Customer customer) throws ServiceException, CustomerExistsException {
		log.debug("Starting customer registration");
		customer.setJoinDate(new Date());
		try {
			if(customerDao.getCustomerByEmail(customer.getContactEmail())==null){
				customerDao.saveCustomer(customer);
				log.debug("Completed customer registration");
			}
			else{
				log.debug("the Customer with email "+customer.getContactEmail() +" already exists");
				throw new CustomerExistsException("customer already exists");
			}
		} catch (DaoException e) {
			log.error("Error saving customer to data base "+e);
			throw new ServiceException("Error saving customer");
		}

	}

	@Override
	public List<Customer> getAllCustomers() throws ServiceException {
		log.debug("getting all customers");
		try {
			return customerDao.getCustomers();
		} catch (DaoException e) {
			log.error("error getting customers "+e);
			throw new ServiceException("error getting customers");
		}
	}

	@Override
	public Customer getCustomerByEmail(String email) throws ServiceException {
		try {
			return customerDao.getCustomerByEmail(email);
		} catch (DaoException e) {
			log.error("error getting customers "+e);
			throw new ServiceException("error getting customer with email "+email);
		}
	}

}
