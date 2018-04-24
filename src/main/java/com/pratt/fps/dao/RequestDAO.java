package com.pratt.fps.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.Request;
import com.pratt.fps.pojo.TxnDetails;

public class RequestDAO extends DAO {

	public ArrayList<Request> getReq(int empId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Request where employee = :empId and reqStatus = :reqStat");
			q.setInteger("empId", empId);
			q.setString("reqStat", "Pending");
			ArrayList<Request> rL = (ArrayList<Request>) q.list();
			close();
			if (rL != null) {
				return rL;

			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not search Customer ", e);
		}
		return null;
	}

	public Request getReqDetails(int reqId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Request where reqId = :reqId");
			q.setInteger("reqId", reqId);
			Request req = (Request) q.uniqueResult();
			close();
			if (req != null) {
				return req;

			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not search Customer ", e);
		}
		return null;
	}

	public Boolean updateTxnStat(int txnId, String stat) throws Exception {
		Boolean b = false;
		try {
			begin();
			Query q = getSession().createQuery("update TxnDetails set status = :stat where txnId = :txnId");
			q.setString("stat", stat);
			q.setInteger("txnId", txnId);

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

	public Boolean updateReqStat(int reqId, String stat) throws Exception {
		Boolean b = false;
		try {
			begin();
			Query q = getSession().createQuery("update Request set reqStatus = :stat where reqID = :reqId");
			q.setString("stat", stat);
			q.setInteger("reqId", reqId);

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


	public TxnDetails createRevertTxn(TxnDetails txnD) throws Exception {

		try {
			begin();
			getSession().save(txnD);
			commit();
			close();
			return txnD;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}

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
