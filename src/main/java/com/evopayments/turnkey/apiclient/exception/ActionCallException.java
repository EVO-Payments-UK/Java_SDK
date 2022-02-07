package com.evopayments.turnkey.apiclient.exception;

/**
 * Failure during the main (the action) call
 * 
 * @author erbalazs
 */
public class ActionCallException extends SDKException {

	public ActionCallException() {
		super();
	}
	public ActionCallException(String msg) {
		super(msg);
	}
}
