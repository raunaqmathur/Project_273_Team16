package com.cmpe.project.controller;


import java.io.IOException;

import com.cmpe.project.connection.DataManager;
import com.cmpe.project.to.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.security.MessageDigest;
import java.sql.*;
/**
 * Servlet implementation class UserSignUp
 */
public class UserSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RegistrationBean rb=new RegistrationBean();
		
		System.out.println("1");
		
		DataManager d1=new DataManager();
		Connection con=d1.getConnection();
		System.out.println("2");
		rb.setFirstname(request.getParameter("firstname"));
		rb.setLastname(request.getParameter("lastname"));
		rb.setUsername(request.getParameter("username"));
		System.out.println(rb.getUsername());
		rb.setPassword(request.getParameter("password"));
		rb.setEmail(request.getParameter("email"));

		try {
		
		String query = "insert into users(first_name,last_name,user_name,password,email_address) values(?,?,?,?,?)";
		
		con=d1.getConnection();
		ResultSet rset=null;
		PreparedStatement smt = con.prepareStatement(query);
		
		smt.setString(1, rb.getFirstname());
		smt.setString(2, rb.getLastname());
		smt.setString(3, rb.getUsername());
		System.out.println(rb.getUsername());
		String Password=rb.getPassword();
		String encryptedpassword="";
		try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(Password.getBytes());
            byte[] bytes=md.digest();
           StringBuilder sb=new StringBuilder();
           for (int i = 0; i < bytes.length; i++) {
                  sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                  
           }
			 
            encryptedpassword=sb.toString();
            smt.setString(4, encryptedpassword);
      } catch (Exception e) {
    	//  System.out.println("selectselect");
            e.printStackTrace();
     }
		
		
		smt.setString(5, rb.getEmail());
		System.out.println(rb.getEmail());
		
		int i = smt.executeUpdate();

        if(i!=0){  
        	out.println("<font size='4' color=blue>" + "Sign Up Successful" + "</font>");
        	out.print("\n");
        	out.println("\n <font size='6' color=black>" + "Welcome"+" "+rb.getFirstname());
        }  
        else{  
          out.println("<font size='6' color=blue>" + "Sign Up Failed" + "</font>");
         }       
        smt.close();
		}
		catch(SQLException e){
			if(e.getMessage().indexOf("Duplicate entry")!=-1) {
	            out.println("<font size='5' color=red>" + "User already exists. Please try another username.");
	        } 
			else {
	        	out.println("<font size='5' color=red>" + "SQLException caught: " + e.getMessage()+
	        			"\n Unable to register user. Please try again later");
	        }
		}
		catch (Exception e){
		out.println(e);
		}
		finally {
		try {
		if (con != null) 
			con.close();
		}
		catch (SQLException ex){
		out.println(ex);
		}
		}
		}
	}
