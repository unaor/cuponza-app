package com.datasol.cuponza.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datasol.cuponza.dao.BusinessCategoryDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.BusinessCategory;

@Repository 
public class BusinessCategoryDaoImpl implements BusinessCategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(BusinessCategoryDaoImpl.class);

	@Override
	public List<BusinessCategory> getAllBusinessCategories()
			throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from BusinessCategory").list();
	}

	@Override
	public BusinessCategory getCategoryById(Integer categoryId) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		return (BusinessCategory)session.get(BusinessCategory.class, categoryId);
	}

	@Override
	public BusinessCategory getCategoryByName(String categoryName) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("getting business category by name: "+categoryName);
		BusinessCategory bc   = (BusinessCategory) session
				.createQuery("from BusinessCategory b where b.description like :categoryName")
				.setParameter("categoryName", categoryName+"%").uniqueResult();
		return bc;
	}

	@Override
	public void addBusinessCategory(BusinessCategory category) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);

	}

}
