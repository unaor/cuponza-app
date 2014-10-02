package com.datasol.cuponza.service;

import java.util.List;

import com.datasol.cuponza.exception.CustomerExistsException;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Customer;

public interface CustomerService {
	
	public void addCustomer(Customer customer) throws ServiceException,CustomerExistsException;	
	public List<Customer> getAllCustomers() throws ServiceException;
	public Customer getCustomerByEmail(String email) throws ServiceException;
	public void addUserFromClient(Customer customer) throws ServiceException;

}
