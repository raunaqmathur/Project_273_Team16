package com.cmpe.project.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cmpe.project.connection.*;
import com.cmpe.project.exception.BusinessException;

import javax.servlet.http.HttpServlet;




import com.cmpe.project.to.*;

public class LoginDAO extends HttpServlet {
		Connection conn=null;
		public void login(LoginTO user) throws BusinessException
		{
			try{
				DataManager dm=new DataManager();
				String dbpassword="";
				String dbusername="";
				String Username=user.getUsername();
				String Password=user.getPassword();
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
          } catch (Exception e) {
        	//  System.out.println("selectselect");
                e.printStackTrace();
         }
			
				String validateLogin="select user_name,password from users where user_name='"+user.getUsername()+"'";
			//	System.out.println("select select");
			conn=dm.getConnection();
			ResultSet rset=null;
			Statement stmt=conn.createStatement();
			 
			 rset=stmt.executeQuery(validateLogin);
			
			rset.next();
			   try{
			      dbusername=rset.getString("user_name");
			      dbpassword=rset.getString("password");
			     }
			   catch (Exception e)
			   {
				//  System.out.println("UN is nt corrent"); 
				   throw new BusinessException("username does not exist ! ");
			    }
			   if(!encryptedpassword.equals(dbpassword))
					   {
				 //  System.out.println("password is incorrect");
				   throw new BusinessException("password is incorrect ");
					   }
		}
		catch(SQLException e)
		{
		//	System.out.println("username or password is incorrect");
			throw new BusinessException("username or password is incorrect ");
		}
			   
		
		
	}
}
