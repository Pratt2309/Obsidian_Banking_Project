package com.pratt.fps.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Employee;

public class AccountDAO extends DAO{

	public Boolean accountCreate(Accounts account) throws Exception {
		Boolean b = false;
		try {
			begin();
			getSession().save(account);
			commit();
			b = true;
			close();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not create Account " , e);
		}
		return b;
	}
}
