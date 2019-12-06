package com.inter.Exception;

import com.inter.model.ErrorModel;

public class InterException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private ErrorModel e;

	public InterException(ErrorModel e) {
		this.e = e;
	}
	
	public ErrorModel getErrorModel() {
		return this.e;
	}
}
