package com.pratt.fps.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Customer;
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
			throw new Exception("Could not create Account ", e);
		}
		return b;

	}

	public Boolean usernameSearch(String userName) throws Exception {
		Boolean b = false;
		try {
			begin();
			Query q = getSession().createQuery("from Employee where username = :username");
			q.setString("username", userName);
			Employee em = (Employee) q.uniqueResult();
			close();
			if (em != null) {

				b = true;
			}
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not search Customer ", e);
		}
		return b;

	}

}
