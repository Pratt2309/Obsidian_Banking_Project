package com.pratt.fps.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pratt.fps.dao.CustomerDAO;
import com.pratt.fps.dao.EmployeeDAO;

@Controller
public class AjaxController {

	@RequestMapping(value = "/ajaxService.htm", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String ajaxService(HttpServletRequest request, CustomerDAO cDao) {
		String queryString = request.getParameter("username");
		String result = "";

		try {
			Boolean b = cDao.usernameSearch(queryString);
			if (b) {
				result = "Username Already Exists!";
			} else {
				result = "Unique Username!";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/ajaxServiceE.htm", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String ajaxServiceE(HttpServletRequest request, EmployeeDAO eDao) {
		String queryString = request.getParameter("username");
		String result = "";

		try {
			Boolean b = eDao.usernameSearch(queryString);
			if (b) {
				result = "Username Already Exists!";
			} else {
				result = "Unique Username!";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
