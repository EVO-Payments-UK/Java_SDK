package com.evopayments.turnkey.apiclient.exception;

/**
 * Other, not specified error in the SDK code
 * 
 * @author erbalazs
 */
public class GeneralException extends SDKException {

	public GeneralException() {
		super();
	}

	public GeneralException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneralException(String message) {
		super(message);
	}

	public GeneralException(Throwable cause) {
		super(cause);
	}	 
	
}
