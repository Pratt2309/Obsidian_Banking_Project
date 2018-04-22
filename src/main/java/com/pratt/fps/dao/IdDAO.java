package com.pratt.fps.dao;

import org.hibernate.HibernateException;

import com.pratt.fps.pojo.IDdetails;

public class IdDAO extends DAO {

	public boolean registerId(IDdetails idD) throws Exception {
		Boolean b = false;
		try {
			begin();
			getSession().save(idD);
			commit();
			close();
			b = true;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not create Account ", e);
		}

		return b;

	}
	
	public IDdetails checkID(String idNum) {
		return null;
		
	}
}
