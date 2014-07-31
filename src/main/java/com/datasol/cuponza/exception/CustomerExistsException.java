package com.datasol.cuponza.exception;

public class CustomerExistsException extends Exception {


	private static final long serialVersionUID = -1028007944107763347L;
	
	public CustomerExistsException(String msg){
		super(msg);
	}
	public CustomerExistsException(String msg ,Throwable cause){
		super(msg,cause);
	}
	public CustomerExistsException(){
		super();
	}


}
