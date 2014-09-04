package com.datasol.cuponza.service;

import java.util.List;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Cupon;

public interface CuponService {
	
	public List<Cupon> getCuponsByLocation(Float longitude ,Float latitude) throws ServiceException;

}
