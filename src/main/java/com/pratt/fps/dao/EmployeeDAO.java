package com.pratt.fps.dao;

import org.hibernate.HibernateException;

import com.pratt.fps.pojo.Employee;

public class EmployeeDAO extends DAO {

	public Boolean empCreate(Employee employee) throws Exception {
		Boolean b = false;
		try {
			begin();
			getSession().save(employee);
			commit();
			b = true;
			close();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not create Account " ,e);
		}
		return b;
		
	}
		
	
}
