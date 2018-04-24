package com.pratt.fps.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pratt.fps.dao.RequestDAO;
import com.pratt.fps.dao.TransferDAO;
import com.pratt.fps.dao.WithdrawDAO;
import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Request;
import com.pratt.fps.pojo.TrfBalDetails;
import com.pratt.fps.pojo.TxnDetails;

@Controller
public class ManagerDashboard {

	public ManagerDashboard() {

		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/mgr/mr", method = RequestMethod.GET)
	public String mngRequests(ModelMap model, HttpServletRequest request, RequestDAO rDao) {
		HttpSession session = request.getSession();
		int empId = (Integer) session.getAttribute("empId");
		try {
			ArrayList<Request> rL = rDao.getReq(empId);
			if (rL != null) {
				request.setAttribute("rL", rL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "mngRequests";

	}

	@RequestMapping(value = "/mgr/mr", method = RequestMethod.POST)
	public String mngRequests(HttpServletRequest request, ModelMap model, RequestDAO rDao) {
		String outView = null;
		String action = request.getParameter("action");
		int reqID = Integer.parseInt(request.getParameter("reqId"));

		if (action.equals("Approve")) {
			try {
				Request req = rDao.getReqDetails(reqID);
				if (req != null) {
					int txnId = req.getTxn().getTxnId();
					String stat = "Success";
					Boolean b = rDao.updateTxnStat(txnId, stat);
					if (b) {
						String reqStat = "Done";
						Boolean c = rDao.updateReqStat(req.getReqID(), reqStat);
						if (c) {
							outView = "success";
						}
					}

				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (action.equals("Decline")) {
			// revert the txn by creating another credit link.
			// update the bal of acct
			// update req status
			try {
				Request reqD = rDao.getReqDetails(reqID);
				if (reqD != null) {
					int txnId = reqD.getTxn().getTxnId();
					String stat = "Declined";
					Boolean b = rDao.updateTxnStat(txnId, stat);
					if (b) {
						TxnDetails txn = reqD.getTxn();
						TxnDetails txnD = new TxnDetails();

						txnD.setAmount(txn.getAmount());
						txnD.setFromAccountId(txn.getFromAccountId());
						txnD.setMode(txn.getMode());
						txnD.setStatus("Revert Transaction for ID " + txn.getTxnId());
						txnD.setTxnType("Credit");
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						txnD.setDate(String.valueOf(dateFormat.format(date)));

						TxnDetails txnR = rDao.createRevertTxn(txnD);

						if (txnR != null) {
							int acctId = txnR.getFromAccountId();
							int amount = txnR.getAmount();
							Accounts a = rDao.getAcctDetails(acctId);
							if (a != null) {
								int currBal = a.getCurrentBalance() + amount;
								Boolean d = rDao.updateBal(acctId, currBal);
								if (d) {
									String rStat = "Done";
									Boolean e = rDao.updateReqStat(reqID, rStat);
									if (e) {
										outView = "success";
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return outView;

	}

}
