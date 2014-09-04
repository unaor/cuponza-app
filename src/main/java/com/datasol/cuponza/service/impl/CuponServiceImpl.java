package com.datasol.cuponza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.dao.CuponDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.Cupon;
import com.datasol.cuponza.service.CuponService;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true ,rollbackFor = Exception.class)
public class CuponServiceImpl implements CuponService {
	
	@Autowired
	CuponDao cuponDao;

	@Override
	public List<Cupon> getCuponsByLocation(Float longitude, Float latitude)
			throws ServiceException {
		List<Cupon> cupons=null;
		try {
			cupons= cuponDao.getCuponsByLocation(longitude, latitude);
		} catch (DaoException e) {
			throw new ServiceException("Error getting cupons");
		}
		return cupons;
	}

}
