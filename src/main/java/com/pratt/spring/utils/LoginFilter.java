package com.pratt.spring.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("inside login filter class");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String url = request.getRequestURI();
		System.out.println(url);
		System.out.println(request.getContextPath());
		if (session != null) {
			String sesUsername = (String) session.getAttribute("username");
			String sesPassword = (String) session.getAttribute("password");
			String url1 = request.getRequestURI();
			Cookie[] cks = request.getCookies();
			if (cks != null) {
				for (int i = 0; i < cks.length; i++) {
					String name = cks[i].getName();
					String value = cks[i].getValue();
					if (name.equals("loginDetails")) {
						String[] lD = value.split(":");
						String usernm = (String) lD[0];
						String pwd = (String) lD[1];
						if (usernm.equals(sesUsername) && pwd.equals(sesPassword)) {
							response.sendRedirect(request.getContextPath() + url); // here goto
																					// http://yourdoamin/login.html
							return;
						} else {
							response.sendRedirect(request.getContextPath());
							response.setHeader("message", "Session Timeout.");
							return;
						}

					}
				}
			}
		} else {

			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
