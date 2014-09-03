package com.datasol.cuponza.exception;

public class UserAlreadyExistsException extends Exception {


	private static final long serialVersionUID = -920886641496469882L;
	
	public UserAlreadyExistsException(String msg){
		super(msg);
	}
	public UserAlreadyExistsException(String msg ,Throwable cause){
		super(msg,cause);
	}
	public UserAlreadyExistsException(){
		super();
	}

}
