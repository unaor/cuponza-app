package com.datasol.cuponza.dao;

import java.util.List;

import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.BusinessCategory;

public interface BusinessCategoryDao {
	
	public final int FOOD =1;
	public final int CLOTHS =2;
	public final int BEAUTY =3;
	
	public List<BusinessCategory> getAllBusinessCategories() throws DaoException;
	public BusinessCategory getCategoryById(Integer categoryId) throws DaoException;
	public BusinessCategory getCategoryByName(String categoryName) throws DaoException;
	public void addBusinessCategory(BusinessCategory category) throws DaoException;

}
