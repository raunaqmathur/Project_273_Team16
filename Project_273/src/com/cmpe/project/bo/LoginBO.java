package com.cmpe.project.bo;

import com.cmpe.project.to.*;
import com.cmpe.project.dao.*;
import com.cmpe.project.exception.BusinessException;
public class LoginBO {
	
	public void validateLogin(LoginTO user) throws BusinessException
	{
		
		//log.info("into EventMgmtloginBO");
			checkUserName(user);
			LoginDAO dao=new LoginDAO();
			dao.login(user);
		
	}
	

	private void checkUserName(LoginTO user) throws BusinessException{
		String username=user.getUserName();
		String password=user.getPassword();
		if(username==null||username.length()==0||password==null||password.length()==0)
		{
			//System.out.println("cannot be blank");
			BusinessException bo=new BusinessException("All fields are mandatory");
			throw bo;
		}
		
	}
	
}
