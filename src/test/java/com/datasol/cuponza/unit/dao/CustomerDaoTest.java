package com.datasol.cuponza.unit.dao;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.dao.CustomerDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class CustomerDaoTest extends TestCase {
	
	@Autowired
	CustomerDao customerDao;
	
	@Before
	public void setUp(){
		try {
			List<Customer> customers = customerDao.getCustomers();
			if(customers==null || customers.isEmpty()){
				Customer customer = createCustomer();
				customerDao.saveCustomer(customer);
			}
		} catch (DaoException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void checkCustomer() throws DaoException{
		Customer uri  = customerDao.getCustomerByEmail("uri@test.com");
		assertEquals("Uri", uri.getFirstName());
	}
	
	
	private Customer createCustomer(){
		Customer customer = new Customer();
		customer.setContactEmail("uri@test.com");
		customer.setFirstName("Uri");
		customer.setJoinDate(new Date());
		customer.setLandPhone("8425627");
		customer.setLastName("Naor");
		customer.setMobilePhone("3202491232");
		return customer;
	}

}
