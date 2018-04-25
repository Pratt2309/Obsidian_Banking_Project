package com.pratt.fps.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.Employee;
import com.pratt.fps.pojo.TxnDetails;

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

	public Boolean usernameSearch(String userName) throws Exception {
		Boolean b = false;
		try {
			begin();
			Query q = getSession().createQuery("from Customer where username = :username");
			q.setString("username", userName);
			Customer cust = (Customer) q.uniqueResult();
			close();
			if (cust != null) {

				b = true;
			}
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not search Customer ", e);
		}
		return b;

	}

	public ArrayList<TxnDetails> getTxnHistory(int custId) throws Exception {
		ArrayList<TxnDetails> txnL = new ArrayList<TxnDetails>();
		try {
			begin();
			Query q = getSession().createQuery("from TxnDetails");
			ArrayList<TxnDetails> tL = (ArrayList<TxnDetails>) q.list();
			close();
			for (TxnDetails t : tL) {
				int facctId = t.getFromAccountId();
				int tacctId = t.getToAccountId();
				ArrayList<Accounts> faL = getAcctDetails(facctId);
				for (Accounts a : faL) {
					int cId = a.getCustomer().getCustId();
					if (cId == custId) {
						txnL.add(t);
					}
				}
				ArrayList<Accounts> taL = getAcctDetails(tacctId);
				for (Accounts a : taL) {
					int cId = a.getCustomer().getCustId();
					if (cId == custId) {
						txnL.add(t);
					}
				}

			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not search Customer ", e);
		}
		return txnL;
	}

	public ArrayList<Accounts> getAcctDetails(int acctId) throws Exception {
		ArrayList<Accounts> aL = new ArrayList<Accounts>();
		try {
			begin();
			Query q = getSession().createQuery("from Accounts where accountId = :acctId");
			q.setInteger("acctId", acctId);

			aL = (ArrayList<Accounts>) q.list();
			close();
			return aL;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}

	}
}
