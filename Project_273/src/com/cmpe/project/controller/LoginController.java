package com.cmpe.project.controller;
import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmpe.project.bo.LoginBO;
import com.cmpe.project.exception.BusinessException;
import com.cmpe.project.to.LoginTO;

public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//System.out.println("in login controller");
		try{
			//System.out.println("2");
		LoginTO user=new LoginTO();
		user.setUserName(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		LoginBO bo=new LoginBO();
		bo.validateLogin(user);
		RequestDispatcher dispatch=req.getRequestDispatcher("Success.jsp");
		dispatch.forward(req, resp);
		}
		catch(BusinessException e)
		{
			//System.out.println("33");
			req.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatch=req.getRequestDispatcher("Login.jsp");
			dispatch.forward(req, resp);
		}
	}
}
