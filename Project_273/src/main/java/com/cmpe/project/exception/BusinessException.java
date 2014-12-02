package com.cmpe.project.exception;


public class BusinessException extends Exception
{
	
	
	private static final long serialVersionUID = 1L;
	public BusinessException(String msg,Throwable t)
	{
		 super(msg, t);
	}
	public BusinessException(String msg)
	{
		 super(msg);
	}


}
