package com.pratt.fps.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.IDdetails;
import com.pratt.fps.pojo.TxnDetails;

public class DepositDAO extends DAO {

	public IDdetails checkID(String idNum, String idType) throws Exception {

		try {
			Query q = getSession().createQuery("from IDdetails where idNum = :idNum and idType = :idType");
			q.setString("idNum", idNum);
			q.setString("idType", idType);

			IDdetails idd = (IDdetails) q.uniqueResult();
			close();
			if (idd != null) {

				return idd;

			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}
		return null;

	}

	public Boolean updateBal(int acctId, int currBal) throws Exception {
		Boolean b = false;
		try {
			begin();
			Query q = getSession()
					.createQuery("update Accounts set currentBalance = :currBal where accountId = :acctId");
			q.setInteger("acctId", acctId);
			q.setInteger("currBal", currBal);

			int result = q.executeUpdate();
			close();
			if (result > 0) {

				b = true;

			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}
		return b;

	}
	
	public Boolean updateTxnTbl(TxnDetails txnD) throws Exception {
		Boolean b = false;
		try {
			begin();
			getSession().save(txnD);
			commit();
			close();
			b = true;

			

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}
		return b;

	}

	public Accounts getAcctDetails(int acctId) throws Exception {
		try {
			Query q = getSession().createQuery("from Accounts where accountId = :acctId");
			q.setInteger("acctId", acctId);

			Accounts a = (Accounts) q.uniqueResult();
			close();
			if (a != null) {

				return a;

			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}
		return null;

	}
	
}
