package com.pratt.fps.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.Employee;
import com.pratt.fps.pojo.IDdetails;
import com.pratt.fps.pojo.Request;
import com.pratt.fps.pojo.TxnDetails;

public class WithdrawDAO extends DAO{

	public IDdetails checkID(String idNum) throws Exception{
		
		try {
			Query q = getSession().createQuery("from IDdetails where idNum = :idNum");
			q.setString("idNum", idNum);

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
	
	public TxnDetails getTxnId(TxnDetails txnD) throws Exception {
		
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
	
	public ArrayList<Employee> getMgrs(int branchId) throws Exception {
		
		try {
			begin();
			Query q = getSession().createQuery("from Employee where branchId = :branchId");
			q.setInteger(":branchId", branchId);
			ArrayList<Employee> eL = (ArrayList<Employee>) q.list();
			close();
			if(eL != null) {
				return eL;
			}
			
			
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not search Customer ", e);
		}
		return null;
	}
	
	public Boolean sendWithdReq(Request req) throws Exception {
		Boolean b = false;
		try {
			begin();
			getSession().save(req);
			commit();
			close();
			b = true;

			close();

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}
		return b;

	}
}
