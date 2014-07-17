package com.datasol.cuponza.exception;

public class ServiceException extends Exception {


	private static final long serialVersionUID = -1028007944107763347L;
	
	public ServiceException(String msg){
		super(msg);
	}
	public ServiceException(String msg ,Throwable cause){
		super(msg,cause);
	}
	public ServiceException(){
		super();
	}


}
