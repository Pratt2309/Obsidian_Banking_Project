package com.pratt.fps.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pratt.fps.dao.AccountDAO;
import com.pratt.fps.dao.CustomerDAO;
import com.pratt.fps.dao.DepositDAO;
import com.pratt.fps.dao.IdDAO;
import com.pratt.fps.dao.TransferDAO;
import com.pratt.fps.dao.WithdrawDAO;
import com.pratt.fps.pojo.Accounts;
import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.Employee;
import com.pratt.fps.pojo.IDdetails;
import com.pratt.fps.pojo.Request;
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
						txnD.setTxnType("Transfer");
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

	@RequestMapping(value = "/emp/csd", method = RequestMethod.GET)
	public String custSearchDep() {

		return "custSearchDep";

	}

	@RequestMapping(value = "/emp/csd", method = RequestMethod.POST)
	public String custSearchDep(HttpServletRequest request, ModelMap model, TransferDAO tDao) {
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
			ArrayList<TrfBalDetails> cL = tDao.gettrfBalDetails(fName);
			model.addAttribute("cL", cL);
			outView = "custSearchDep";

		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}

		return outView;

	}

	@RequestMapping(value = "/emp/db", method = RequestMethod.GET)
	public String depBalance(ModelMap model, HttpServletRequest request) {
		ArrayList<String> idList = new ArrayList<String>();

		idList.add("State driver’s license");
		idList.add("State-issued identification card");
		idList.add("Military-issued identification card");
		idList.add("Valid U.S. or foreign-issued passport");
		idList.add("Permanent Resident Alien Card");
		idList.add("Non-immigrant Visa");
		idList.add("Certificate of U.S. Naturalization");
		idList.add("Mexican Consular Card");

		int acctId = Integer.parseInt(request.getParameter("acctId"));
		request.setAttribute("acctId", acctId);
		request.setAttribute("idList", idList);

		return "depBalEmp";

	}

	@RequestMapping(value = "/emp/cdp", method = RequestMethod.GET)
	public String cdp(ModelMap model, HttpServletRequest request, CustomerDAO cDao) {

		try {
			Customer c = cDao.cuSe(10);
			request.setAttribute("cu", c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "custDetails";

	}

	@RequestMapping(value = "/emp/db", method = RequestMethod.POST)
	public String depBalance(HttpServletRequest request, DepositDAO dDao) {
		String outView = "error";
		int amount = Integer.parseInt(request.getParameter("amount"));
		String idNum = request.getParameter("idnum");
		String idType = request.getParameter("idtype");
		int acctId = Integer.parseInt(request.getParameter("acctid"));

		try {
			Accounts acd = dDao.getAcctDetails(acctId);
			if (acd != null) {
				IDdetails idD = dDao.checkID(idNum, idType);
				if (idD != null) {
					if (idD.getCustomer().getCustId() == acd.getCustomer().getCustId()) {
						int currBal = acd.getCurrentBalance() + amount;
						Boolean b = dDao.updateBal(acd.getAccountId(), currBal);
						if (b) {
							TxnDetails txnD = new TxnDetails();
							txnD.setFromAccountId(acd.getAccountId());
							txnD.setAmount(amount);
							txnD.setMode("Teller Deposit");
							txnD.setStatus("Success");
							txnD.setTxnType("Credit");
							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Date date = new Date();
							txnD.setDate(String.valueOf(dateFormat.format(date)));
							Boolean c = dDao.updateTxnTbl(txnD);
							if (c) {
								outView = "success";
							}
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outView;
	}

	@RequestMapping(value = "/emp/swr", method = RequestMethod.POST)
	public String sendWithReq(HttpServletRequest request, WithdrawDAO wDao) {
		int ramount = Integer.parseInt(request.getParameter("amount"));
		int raccountId = Integer.parseInt(request.getParameter("accountId").trim());
		int rempId = Integer.parseInt(request.getParameter("mngrId"));

		String outView = "error";
		try {
			Accounts a = wDao.getAcctDetails(raccountId);
			if (a != null) {
				int currBal = a.getCurrentBalance() - ramount;
				Boolean b = wDao.updateBal(a.getAccountId(), currBal);
				if (b) {
					TxnDetails txnD = new TxnDetails();
					txnD.setFromAccountId(raccountId);
					txnD.setAmount(ramount);
					txnD.setMode("Teller Withdraw");
					txnD.setStatus("Pending Manager Approval");
					txnD.setTxnType("Debit");
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					txnD.setDate(String.valueOf(dateFormat.format(date)));

					TxnDetails txn = wDao.getTxnId(txnD);

					if (txn != null) {
						Accounts acc = wDao.getAcctDetails(raccountId);
						Employee remp = wDao.getMngrDetails(rempId);
						Request req = new Request();
						req.setAccount(acc);
						req.setAmount(ramount);
						req.setEmployee(remp);
						req.setTxn(txn);
						req.setReqStatus("Pending");
						Boolean d = wDao.sendWithdReq(req);
						if (d) {
							outView = "success";
						}
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outView;

	}

	@RequestMapping(value = "/emp/wb", method = RequestMethod.POST)
	public String withdBalance(HttpServletRequest request, WithdrawDAO wDao) {
		String outView = "error";
		int amount = Integer.parseInt(request.getParameter("amount"));
		String idNum = request.getParameter("idnum");
		String idType = request.getParameter("idtype");
		int acctId = Integer.parseInt(request.getParameter("acctid"));

		if (amount <= 500) {
			try {
				Accounts ac = wDao.getAcctDetails(acctId);
				if (ac != null) {
					int custId = ac.getCustomer().getCustId();
					IDdetails idD = wDao.checkID(idNum);
					if (idD != null) {
						int cuId = idD.getCustomer().getCustId();
						if (cuId == custId) {
							if (idD.getIdNum().equals(idNum) && idD.getIdType().equals(idType)) {
								int currBal = ac.getCurrentBalance();
								if (amount > currBal) {
									outView = "error";
								} else {
									currBal = currBal - amount;
									Boolean b = wDao.updateBal(acctId, currBal);
									if (b) {
										TxnDetails txnD = new TxnDetails();
										txnD.setAmount(amount);
										txnD.setFromAccountId(acctId);
										txnD.setMode("Teller Withdraw");
										txnD.setStatus("Success");
										txnD.setTxnType("Debit");
										DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
										Date date = new Date();
										txnD.setDate(String.valueOf(dateFormat.format(date)));
										Boolean c = wDao.updateTxnTbl(txnD);
										if (c) {
											outView = "success";
										} else {
											outView = "error";
										}
									}
								}
							}
						}
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			request.setAttribute("amount", amount);
			request.setAttribute("acctId", acctId);
			HttpSession session = request.getSession();
			int branchId = (Integer) session.getAttribute("branchId");
			try {
				ArrayList<Employee> eL = wDao.getMgrs(branchId);
				if (eL != null) {
					request.setAttribute("eL", eL);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			outView = "sendWithdReq";
		}

		return outView;

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

		int acctId = Integer.parseInt(request.getParameter("acctId"));
		request.setAttribute("acctId", acctId);
		request.setAttribute("idList", idList);

		return "withdrawBalEmp";

	}

	@RequestMapping(value = "/emp/csw", method = RequestMethod.GET)
	public String custSearchWithd() {

		return "custSearchWithd";

	}

	@RequestMapping(value = "/emp/csw", method = RequestMethod.POST)
	public String custSearchWithd(HttpServletRequest request, ModelMap model, CustomerDAO cDao, TransferDAO tDao) {
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
			ArrayList<TrfBalDetails> cL = tDao.gettrfBalDetails(fName);
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

		command.setUsername(request.getParameter("username"));
		command.setPassword(command.getLastName());
		String s1 = command.getStreetAddr1();
		String s2 = "'" + s1 + "'";
		command.setStreetAddr1(s2);

		try {
			Boolean b = customerDAO.custCreate(command);
			if (b) {
				sendEmail(command.getEmail1(), "Your password is : " + command.getPassword());
				outView = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			outView = "error";
		}

		return outView;

	}

	public void sendEmail(String useremail, String message) {

		try {
			Email email = new SimpleEmail();

			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
			email.setFrom("no-reply@gmail.com");
			email.setSubject("Account Activation!");
			email.setMsg(message);
			email.addTo(useremail);
			email.send();
		} catch (Exception e) {
			System.out.println("Email can't be sent");
		}
	}
}
