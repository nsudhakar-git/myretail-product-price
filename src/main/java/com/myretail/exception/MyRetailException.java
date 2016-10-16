package com.myretail.exception;

public class MyRetailException extends Exception {
	int errorCode;

	public MyRetailException(Exception e) {
		super();
	}

	public MyRetailException(Exception e,int errCode) {
		super(e);
		this.errorCode=errCode;
		
	}
	public MyRetailException(String string) {
		super(string);
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
