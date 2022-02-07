package com.evopayments.turnkey.apiclient.exception;

/**
 * Base class for the SDK exceptions
 * 
 * @author erbalazs
 *
 */
abstract public class SDKException extends RuntimeException {

	public SDKException() {
		super();
	}

	public SDKException(String message, Throwable cause) {
		super(message, cause);
	}

	public SDKException(String message) {
		super(message);
	}

	public SDKException(Throwable cause) {
		super(cause);
	}
	
}
