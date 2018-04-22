package com.pratt.fps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pratt.fps.dao.EmployeeDAO;
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

}
