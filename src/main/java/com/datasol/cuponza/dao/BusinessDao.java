package com.datasol.cuponza.dao;

import java.util.List;

import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Business;

public interface BusinessDao {
	
	public List<Business> getBusinesses() throws DaoException;
	public Business getBusinessByEmail(String email) throws DaoException;
	public void addBusiness(Business business) throws DaoException;
	public void removeBusiness(Business business) throws DaoException;

}
