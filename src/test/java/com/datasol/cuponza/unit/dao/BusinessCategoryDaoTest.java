package com.datasol.cuponza.unit.dao;

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

import com.datasol.cuponza.dao.BusinessCategoryDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.BusinessCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class BusinessCategoryDaoTest extends TestCase{
	
	@Autowired
	BusinessCategoryDao businessCategoryDao;
	
	@Before
	public void setUp(){
		try {
			List<BusinessCategory> categories =businessCategoryDao.getAllBusinessCategories();
			if(categories==null || categories.isEmpty()){
				BusinessCategory category = new BusinessCategory();
				category.setDescription("Food");
				businessCategoryDao.addBusinessCategory(category);
				category = new BusinessCategory();
				category.setDescription("Cloths");
				businessCategoryDao.addBusinessCategory(category);
				category = new BusinessCategory();
				category.setDescription("Beauty");
				businessCategoryDao.addBusinessCategory(category);
			}
		} catch (DaoException e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void getBusinessCategories() throws DaoException{
		List<BusinessCategory> categories =businessCategoryDao.getAllBusinessCategories();
		assertEquals(3,categories.size());
		BusinessCategory category = businessCategoryDao.getCategoryById(BusinessCategoryDao.CLOTHS);
		assertEquals("Cloths",category.getDescription());
		BusinessCategory foodCategory = businessCategoryDao.getCategoryByName("Fo");
		assertNotNull(foodCategory);
		
	}

}
