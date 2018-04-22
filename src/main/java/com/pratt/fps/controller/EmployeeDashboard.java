package com.pratt.fps.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pratt.fps.dao.AccountDAO;
import com.pratt.fps.dao.CustomerDAO;
import com.pratt.fps.dao.IdDAO;
import com.pratt.fps.dao.TransferDAO;
import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.IDdetails;
import com.pratt.fps.pojo.TrfBalDetails;
import com.pratt.fps.pojo.TxnDetails;

@Controller
public class EmployeeDashboard {

	public EmployeeDashboard() {

		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/emp/tb", method = RequestMethod.GET)
	public String trfBalance() {
		return "custDetailsTransfer";

	}

	@RequestMapping(value = "/emp/tb", method = RequestMethod.POST)
	public String searchCustAcctTransfer(HttpServletRequest request, ModelMap model, TransferDAO tDao) {
		String outView = null;
		String fromFName = request.getParameter("fromfname");
		String toFName = request.getParameter("tofname");

		try {
			ArrayList<TrfBalDetails> fromcL = tDao.gettrfBalDetails(fromFName);
			model.addAttribute("fromcL", fromcL);
			ArrayList<TrfBalDetails> tocL = tDao.gettrfBalDetails(toFName);
			model.addAttribute("tocL", tocL);

			outView = "custAccountSelectTransfer";

		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}

		return outView;

	}

	@RequestMapping(value = "/emp/tbal", method = RequestMethod.POST)
	public String transferBalance(HttpServletRequest request, ModelMap model, TransferDAO tDao) {
		String outView = null;
		int fromAcctId = Integer.parseInt(request.getParameter("facctIdSelect"));
		int toAcctId = Integer.parseInt(request.getParameter("tacctIdSelect"));
		int tranfAmount = Integer.parseInt(request.getParameter("tamount"));

		try {
			Accounts fAccnt = tDao.transferBal(fromAcctId);
			Accounts tAccnt = tDao.transferBal(toAcctId);

			if (fAccnt != null && tAccnt != null) {
				int toCurrBal = tAccnt.getCurrentBalance();
				int fromCurrBal = fAccnt.getCurrentBalance();
				if (tranfAmount > fromCurrBal) {
					outView = "error";
				} else {
					fromCurrBal = fromCurrBal - tranfAmount;
					toCurrBal = toCurrBal + tranfAmount;

					Boolean bF = tDao.updateBal(fromAcctId, fromCurrBal);
					Boolean bT = tDao.updateBal(toAcctId, toCurrBal);

					if (bF && bT) {
						TxnDetails txnD = new TxnDetails();

						txnD.setFromAccountId(fromAcctId);
						txnD.setToAccountId(toAcctId);
						txnD.setAmount(tranfAmount);
						txnD.setMode("Teller Transfer");
						txnD.setStatus("Success");
						txnD.setTxnType("Bank Transfer");
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						txnD.setDate(String.valueOf(dateFormat.format(date)));

						Boolean c = tDao.updateTxnTbl(txnD);
						if (c) {
							outView = "success";
						} else {
							outView = "error";
						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}

		return outView;

	}

	@RequestMapping(value = "/emp/db", method = RequestMethod.GET)
	public String depBalance() {
		return "depositBalance";

	}

	@RequestMapping(value = "/emp/db", method = RequestMethod.POST)
	public String depBalanceS() {
		return "success";

	}

	@RequestMapping(value = "/emp/wb", method = RequestMethod.POST)
	public String withdBalance(HttpServletRequest request) {
		
		System.out.println(request.getParameter("idtype"));
		return "withdrawBalance";

	}

	@RequestMapping(value = "/emp/wb", method = RequestMethod.GET)
	public String withdBalanceS(ModelMap model, HttpServletRequest request) {
		
		ArrayList<String> idList = new ArrayList<String>();

		idList.add("State driver’s license");
		idList.add("State-issued identification card");
		idList.add("Military-issued identification card");
		idList.add("Valid U.S. or foreign-issued passport");
		idList.add("Permanent Resident Alien Card");
		idList.add("Non-immigrant Visa");
		idList.add("Certificate of U.S. Naturalization");
		idList.add("Mexican Consular Card");

		int id = Integer.parseInt(request.getParameter("custId"));
		request.setAttribute("custId", id);
		request.setAttribute("idList", idList);

		return "withdrawBalEmp";

	}
	
	@RequestMapping(value = "/emp/csw", method = RequestMethod.GET)
	public String custSearchWithd() {

		return "custSearchWithd";

	}

	@RequestMapping(value = "/emp/csw", method = RequestMethod.POST)
	public String custSearchWithd(HttpServletRequest request, ModelMap model, CustomerDAO cDao) {
		String outView = null;
		String fName = request.getParameter("fname");
		String mName = request.getParameter("mname");
		String lName = request.getParameter("lname");

		if (fName == null) {
			fName = "";
		}
		if (mName == null) {
			mName = "";
		}
		if (lName == null) {
			lName = "";
		}

		try {
			ArrayList<Customer> cL = cDao.custSearch(fName);
			model.addAttribute("cL", cL);
			outView = "custSearchWithd";

		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}

		return outView;

	}

	@RequestMapping(value = "/emp/cs", method = RequestMethod.GET)
	public String custSearch() {

		return "custSearch";

	}

	@RequestMapping(value = "/emp/cs", method = RequestMethod.POST)
	public String custSearch(HttpServletRequest request, ModelMap model, CustomerDAO cDao) {
		String outView = null;
		String fName = request.getParameter("fname");
		String mName = request.getParameter("mname");
		String lName = request.getParameter("lname");

		if (fName == null) {
			fName = "";
		}
		if (mName == null) {
			mName = "";
		}
		if (lName == null) {
			lName = "";
		}

		try {
			ArrayList<Customer> cL = cDao.custSearch(fName);
			model.addAttribute("cL", cL);
			outView = "custSearch";

		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}

		return outView;

	}

	@RequestMapping(value = "/emp/idl", method = RequestMethod.GET)
	public String idLink(ModelMap model, HttpServletRequest request) {
		ArrayList<String> idList = new ArrayList<String>();

		idList.add("State driver’s license");
		idList.add("State-issued identification card");
		idList.add("Military-issued identification card");
		idList.add("Valid U.S. or foreign-issued passport");
		idList.add("Permanent Resident Alien Card");
		idList.add("Non-immigrant Visa");
		idList.add("Certificate of U.S. Naturalization");
		idList.add("Mexican Consular Card");

		int id = Integer.parseInt(request.getParameter("custIdL"));
		request.setAttribute("custId", id);
		request.setAttribute("idList", idList);
		model.addAttribute("idDetails", new IDdetails());
		return "idRegister";

	}

	@RequestMapping(value = "/emp/idl", method = RequestMethod.POST)
	public String idLink(@ModelAttribute("idDetails") IDdetails command, ModelMap model, IdDAO idDAO) throws Exception {
		String outView = null;
		try {
			Boolean b = idDAO.registerId(command);
			if (b) {
				outView = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}
		return outView;

	}

	@RequestMapping(value = "/emp/acc", method = RequestMethod.GET)
	public String acctCreate(ModelMap model, HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("custIdA"));
		request.setAttribute("custId", id);
		model.addAttribute("account", new Accounts());
		return "acctCreate";

	}

	@RequestMapping(value = "/emp/acc", method = RequestMethod.POST)
	public String acctCreate(@ModelAttribute("account") Accounts command, ModelMap model, AccountDAO accountDao)
			throws Exception {
		String outView = null;
		String accountType = command.getAccountType();
		if (accountType.equals("Savings")) {
			command.setMinBalance(100);
			command.setStatus("Active");
			try {
				Boolean b = accountDao.accountCreate(command);
				if (b) {
					outView = "success";
				}
			} catch (Exception e) {
				e.printStackTrace();
				outView = "error";
			}
		}
		return outView;

	}

	@RequestMapping(value = "/emp/cc", method = RequestMethod.GET)
	public String custCreate(ModelMap model) {
		model.addAttribute("customer", new Customer());
		return "custCreate";

	}

	@RequestMapping(value = "/emp/cc", method = RequestMethod.POST)
	public String custCreate(@ModelAttribute("customer") Customer command, ModelMap model, CustomerDAO customerDAO,
			HttpServletRequest request) throws Exception {
		String outView = null;
		command.setUsername(command.getFirstName());
		command.setPassword(command.getLastName());

		try {
			Boolean b = customerDAO.custCreate(command);
			if (b) {
				outView = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}

		return outView;

	}
}
