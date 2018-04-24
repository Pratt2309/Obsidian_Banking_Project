package com.pratt.fps.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.captcha.botdetect.web.servlet.Captcha;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.captcha.botdetect.web.servlet.Captcha;
import com.pratt.fps.dao.EmployeeDAO;
import com.pratt.fps.dao.LoginDAO;
import com.pratt.fps.pojo.Employee;

@Controller
public class AdminDashboard {

	@RequestMapping(value = "/admin/empc", method = RequestMethod.GET)
	public String emplCreate(ModelMap model) {

		model.addAttribute("employee", new Employee());
		return "empCreate";
	}

	@RequestMapping(value = "/admin/empc", method = RequestMethod.POST)
	public String emplCreate(@ModelAttribute("employee") Employee command, ModelMap model, EmployeeDAO employeeDAO,
			HttpServletRequest request) {
		String outView = null;
		HttpSession session = request.getSession();
		int branchId = (Integer) session.getAttribute("branchId");
		if(session.getAttribute("branchId") != null) {
			command.setBranchId(branchId);
			command.setUsername(command.getFirstName());
			command.setPassword(command.getLastName());
			try {
				Boolean b = employeeDAO.empCreate(command);
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
	
	@RequestMapping(value = "/employee/forgotpassword", method = RequestMethod.GET)
	public String getForgotPasswordForm(HttpServletRequest request) {
		String v = "hello!";
		System.out.println(v);
		return "forgot-password";
	}
	
	@RequestMapping(value = "/employee/forgotpassword", method = RequestMethod.POST)
	public String handleForgotPasswordForm(HttpServletRequest request, LoginDAO lDao) {
		String useremail = request.getParameter("useremail");
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");

		if (captcha.validate(captchaCode)) {
			Employee emp = lDao.getEmp(useremail);
			sendEmail(useremail, "Your password is : " + emp.getPassword());
			return "success";
		} else {
			request.setAttribute("captchamsg", "Captcha not valid");
			return "forgot-password";
		
		
	}
		
	}
	
	@RequestMapping(value = "user/resendemail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request) {
		

		HttpSession session = request.getSession();
		
			String email = request.getParameter("username");
			

			try {
				
				Random random = new Random();
				int key1 = random.nextInt(50000);
				int key2 = random.nextInt(50000);
				String emailMessage = "http://localhost:8080/lab10/user/validateemail.htm?email=" + email
						+ "&key1=" + key1 + "&key2=" + key2;
				sendEmail(email, emailMessage);
				session.setAttribute("key1", key1);
				session.setAttribute("key2", key2);
				return "user-created";
			} catch (Exception e) {
				System.out.println("Error registering user");
			}
		
		return "user-created";
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

	/*@RequestMapping(value = "user/validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, UserDAO userDao, ModelMap map) {

		// The user will be sent the following link when the use registers
		// This is the format of the email
		// http://hostname:8080/lab10/user/validateemail.htm?email=useremail&key1=<random_number>&key2=<body
		// of the email that when user registers>

		String email = request.getParameter("email");
		HttpSession session = request.getSession();

		int key1 = (Integer) session.getAttribute("key1");
		int key2 = (Integer) session.getAttribute("key2");

		int kr1 = Integer.parseInt(request.getParameter("key1"));
		int kr2 = Integer.parseInt(request.getParameter("key2"));

		if (key1 == kr1 && key2 == kr2) {
			try {
				Boolean b = userDao.updateUser(email);
				if (b) {
					return "user-login";
				} else {
					return "error";
				}

			} catch (Exception e) {
				System.out.println("Error updating user status");
			}
		} else {
			map.addAttribute("errorMessage", "link expired. Generate new link");
			map.addAttribute("resendlink", true);
			return "error";
		}
		return null;
	}
	*/
	

}
