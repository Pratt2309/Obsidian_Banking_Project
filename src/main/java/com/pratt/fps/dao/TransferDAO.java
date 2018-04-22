package com.pratt.fps.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.mysql.jdbc.PreparedStatement;
import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.TrfBalDetails;
import com.pratt.fps.pojo.TxnDetails;

public class TransferDAO extends DAO {

	public ArrayList<TrfBalDetails> gettrfBalDetails(String firstname) throws Exception {
		ArrayList<TrfBalDetails> tfBDL = new ArrayList<TrfBalDetails>();
		try {
			Query q = getSession().createQuery("from Accounts");
			ArrayList<Accounts> aL = (ArrayList<Accounts>) q.list();
			close();
			if (aL != null) {

				for (Accounts a : aL) {
					String fName = a.getCustomer().getFirstName();

					if (fName.equals(firstname)) {
						TrfBalDetails trbd = new TrfBalDetails();
						trbd.setmName(a.getCustomer().getMiddleName());
						trbd.setlName(a.getCustomer().getLastName());
						trbd.setfName(fName);
						trbd.setAcctId(a.getAccountId());
						trbd.setCustId(a.getCustomer().getCustId());

						tfBDL.add(trbd);

					}
				}

			}
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}
		return tfBDL;

	}

	public Accounts transferBal(int acctId) throws Exception {
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

			close();

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not retireve Account Details ", e);
		}
		return b;

	}
}
