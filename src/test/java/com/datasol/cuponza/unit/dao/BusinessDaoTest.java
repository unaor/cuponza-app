package com.datasol.cuponza.unit.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.dao.BusinessCategoryDao;
import com.datasol.cuponza.dao.BusinessDao;
import com.datasol.cuponza.dao.CustomerDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Business;
import com.datasol.cuponza.model.Coordinate;
import com.datasol.cuponza.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class BusinessDaoTest extends TestCase {
	
	@Autowired
	BusinessDao businessDao;
	@Autowired
	BusinessCategoryDao categoryDao;
	@Autowired
	CustomerDao customerDao;
	
	@Before
	public void setUp(){
		try {
			List<Business> businesses  = businessDao.getBusinesses();
			if(businesses==null || businesses.isEmpty()){
				Business business = createBusiness();
				businessDao.addBusiness(business);
			}
		} catch (DaoException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void checkBussiness() throws DaoException{
		Business business = businessDao.getBusinessByEmail("burgers@test.com");
		assertEquals("Uri", business.getCustomer().getFirstName());
		Set<Coordinate> locations = business.getBusinessLocations();
		for(Coordinate location :locations){
			assertEquals(54.3213f,location.getLatidude());
		}
	}
	
	private Business createBusiness() throws DaoException{
		Business business = new Business();
		business.setBusinessCategory(categoryDao.getCategoryById(categoryDao.FOOD));
		business.setBusinessEmail("burgers@test.com");
		Coordinate location = new Coordinate();
		location.setBusiness(business);
		location.setLatidude(54.3213f);
		location.setLongitude(12.54321f);
		Set<Coordinate> locations = new HashSet<Coordinate>();
		locations.add(location);
		business.setBusinessLocations(locations);
		business.setBusinessName("Uri burgers");
		business.setBusinessUrl("www.cuponza.com");
		Customer customer = customerDao.getCustomers().get(0);
		business.setCustomer(customer);
		business.setLandPhone(customer.getLandPhone());
		business.setMobilePhone("3195431232");
		business.setVirtualBusiness(false);
		return business;
	}
	
	@Test
	public void testDeleteBusiness() throws DaoException{
		Business business = businessDao.getBusinessByEmail("burgers@test.com");
		businessDao.removeBusiness(business);
	}

}
