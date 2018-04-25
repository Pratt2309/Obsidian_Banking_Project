package com.pratt.fps;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pratt.fps.dao.LoginDAO;
import com.pratt.fps.pojo.Customer;
import com.pratt.fps.pojo.Employee;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "home";
	}

	@RequestMapping(value = "/customer/login.htm", method = RequestMethod.GET)
	public String custLogin() {
		return "customerlogin";
	}

	@RequestMapping(value = "/customer/login.htm", method = RequestMethod.POST)
	public String custLoginCheck(HttpServletRequest request, HttpServletResponse response, LoginDAO loginDAO,
			ModelMap map) {
		String outView = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Customer c = loginDAO.checkCustLogin(username, password);
			if (c != null) {
				String loginDetails = username + ":" + password;
				Cookie cookie = new Cookie("loginDetails", loginDetails);
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);
				HttpSession session = request.getSession();
				session.setAttribute("username", c.getUsername());
				session.setAttribute("password", c.getPassword());
				session.setAttribute("custId", c.getCustId());
				outView = "custDashboard";
			} else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				outView = "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outView;
	}

	@RequestMapping(value = "/employee/login.htm", method = RequestMethod.GET)
	public String empLogin() {
		return "employeelogin";
	}

	@RequestMapping(value = "/employee/login.htm", method = RequestMethod.POST)
	public String empLoginCheck(HttpServletRequest request, HttpServletResponse response, LoginDAO loginDAO,
			ModelMap map) {
		String outView = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		HttpSession session = request.getSession();

		try {
			Employee e = loginDAO.checkEmpLogin(username, password, branchId);
			if (e != null) {
				session.setAttribute("username", e.getUsername());
				session.setAttribute("password", e.getPassword());
				String role = e.getRole();
				if (role.equals("Executive")) {
					String loginDetails = username + ":" + password;
					Cookie cookie = new Cookie("loginDetails", loginDetails);
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
					session.setAttribute("branchId", e.getBranchId());
					session.setAttribute("empId", e.getEmpId());
					outView = "empDashboard";
				} else if (role.equals("Admin")) {
					String loginDetails = username + ":" + password;
					Cookie cookie = new Cookie("loginDetails", loginDetails);
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
					session.setAttribute("branchId", e.getBranchId());
					session.setAttribute("empId", e.getEmpId());
					outView = "adminDashboard";
				} else if (role.equals("Manager")) {
					String loginDetails = username + ":" + password;
					Cookie cookie = new Cookie("loginDetails", loginDetails);
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
					session.setAttribute("branchId", e.getBranchId());
					session.setAttribute("empId", e.getEmpId());
					outView = "mngrDashboard";
				}
			} else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				outView = "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outView;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cookie ck = new Cookie("loginDetails", "loginDetails");
		ck.setMaxAge(0);
		response.addCookie(ck);
		session.invalidate();
		String outView = "home";

		return outView;
	}

}
