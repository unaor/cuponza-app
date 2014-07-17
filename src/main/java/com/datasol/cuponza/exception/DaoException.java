package com.datasol.cuponza.exception;

public class DaoException extends Exception {


	private static final long serialVersionUID = -4542526366867196643L;
	
	public DaoException(String msg){
		super(msg);
	}
	public DaoException(String msg ,Throwable cause){
		super(msg,cause);
	}
	public DaoException(){
		super();
	}

}
