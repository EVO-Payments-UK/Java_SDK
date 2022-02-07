package com.evopayments.turnkey.apiclient.exception;

/**
 * Error in (around) the outgoing (toward the API server) HTTP client call (most likely: failed HTTP request, failed response parsing, failed request body creation)
 * 
 * @author erbalazs
 */
public class PostToApiException extends SDKException {
		
	public PostToApiException() {
		super();
	}

	public PostToApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public PostToApiException(String message) {
		super(message);
	}

	public PostToApiException(Throwable cause) {
		super(cause);
	}

}
