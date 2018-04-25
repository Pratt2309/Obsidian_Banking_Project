package com.pratt.fps.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pratt.fps.dao.CustomerDAO;
import com.pratt.fps.pojo.TxnDetails;

@Controller
public class CustomerController {

	@RequestMapping(value = "/cust/acs", method = RequestMethod.GET)
	public String trfBalance(HttpServletRequest request, CustomerDAO cDao) {
		String outView = "error";
		HttpSession session = request.getSession();
		if (session != null) {
			int custId = (Integer) session.getAttribute("custId");
			try {
				ArrayList<TxnDetails> txnL = cDao.getTxnHistory(custId);
				request.setAttribute("txnL", txnL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outView = "custAccStatement";
		}
		return outView;
	}

	@RequestMapping(value = "/csv/export", method = RequestMethod.POST)
	protected ModelAndView pageExport(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;

		int cou = Integer.parseInt(request.getParameter("ct"));
		ArrayList<TxnDetails> txL = new ArrayList<TxnDetails>();
		for (int i = 0; i < cou; i++) {
			TxnDetails txnD = new TxnDetails();
			String tId = request.getParameter("txnId" + i);
			int txId = Integer.parseInt(tId);
			txnD.setTxnId(txId);
			txnD.setDate(request.getParameter("date" + i));
			txnD.setFromAccountId(Integer.parseInt(request.getParameter("fromAccountId" + i)));
			txnD.setToAccountId(Integer.parseInt(request.getParameter("toAccountId" + i)));
			txnD.setAmount(Integer.parseInt(request.getParameter("amount" + i)));
			txnD.setMode(request.getParameter("mode" + i));
			txnD.setStatus(request.getParameter("status" + i));
			txnD.setTxnType(request.getParameter("txnType" + i));

			txL.add(txnD);

		}
		return mv = new ModelAndView("excelView", "exceldata", txL);

	}
}
