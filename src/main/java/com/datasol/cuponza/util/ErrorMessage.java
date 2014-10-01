package com.datasol.cuponza.util;


public class ErrorMessage implements Comparable<ErrorMessage> {
	
	private String errorHeader;
	private String errorDescription;
	public String getErrorHeader() {
		return errorHeader;
	}
	public void setErrorHeader(String errorHeader) {
		this.errorHeader = errorHeader;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	@Override
	public int compareTo(ErrorMessage o) {
		return (this.errorHeader).compareTo(o.getErrorHeader());
	}
	
	

}
