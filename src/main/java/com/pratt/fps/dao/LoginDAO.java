package com.pratt.fps.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.Employee;



public class LoginDAO extends DAO {
	
	
	public Employee checkEmpLogin(String username, String password, int branchId) throws Exception {
		
		try {
			begin();
			Query q = getSession().createQuery("from Employee where username = :username and password = :password and branchId = :branchId");
			q.setString("username", username);
			q.setString("password", password);
			q.setInteger("branchId", branchId);
			Employee em = (Employee) q.uniqueResult();
			close();
			if(em!=null) {
				
				return em;
				
			}
			
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + username, e);
		}
		return null;
	}

	public Customer checkCustLogin(String username, String password) throws Exception {
		
		try {
			begin();
			Query q = getSession().createQuery("from Customer where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			
			Customer c = (Customer) q.uniqueResult();
			close();
			if(c != null) {
			return c;
			}
			
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + username, e);
		}
		return null;
		
	}
}
