package com.datasol.cuponza.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.datasol.cuponza.dao.CuponDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.model.Coordinate;
import com.datasol.cuponza.model.Cupon;

public class CuponDaoImpl implements CuponDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(CuponDaoImpl.class);

	@Override
	public List<Cupon> getCuponsByLocation(Coordinate coordinate) throws DaoException {
		logger.debug("getting cupons near "+coordinate.getLatidude() +" : "+coordinate.getLatidude());
		Session session = sessionFactory.getCurrentSession();
		List<Cupon> cupons =  session.createQuery("from Cupon").list();
		logger.debug("obtained total of "+cupons.size() +" cupons");
		return cupons;
	}

}
