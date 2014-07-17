package com.datasol.cuponza.dao;

import java.util.List;

import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Customer;

public interface CustomerDao {
	
	public List<Customer> getCustomers() throws DaoException;
	
	public Customer getCustomerByEmail(String email) throws DaoException;
	
	public void saveCustomer(Customer customer) throws DaoException;
	
	public void deleteCustomer(Customer customer) throws DaoException;

}
