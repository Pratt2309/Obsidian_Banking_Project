package com.pratt.fps.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.Employee;

public class CustomerDAO extends DAO {

	public Boolean custCreate(Customer cust) throws Exception {
		Boolean b = false;
		try {
			begin();
			getSession().save(cust);
			commit();
			close();
			b = true;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not create Account ", e);
		}
		return b;
	}

	public ArrayList<Customer> custSearch(String firstName) throws Exception {
		ArrayList<Customer> cL = new ArrayList();
		try {
			begin();
			Query q = getSession().createQuery("from Customer");

			ArrayList<Customer> cuL = (ArrayList<Customer>) q.list();
			close();
			if (cuL != null) {

				for (Customer c : cuL) {
					String fName = c.getFirstName();

					if (fName.equals(firstName)) {
						
						cL.add(c);
					}

				}
			}
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not search Customer ", e);
		}
		return cL;

	}
}
