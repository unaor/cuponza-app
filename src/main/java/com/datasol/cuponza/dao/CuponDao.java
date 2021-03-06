package com.datasol.cuponza.dao;

import java.util.List;

import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Cupon;

public interface CuponDao {
	
	public List<Cupon> getCuponsByLocation(Float longitude, Float latitude) throws DaoException;

}
