package com.evopayments.turnkey.apiclient.exception;

import java.util.Set;

/**
 * One or more required parameter were left out
 * 
 * @author erbalazs
 */
public class RequiredParamException extends SDKException {

	private final Set<String> missingFields;

	public RequiredParamException(Set<String> missingFields) {
		super(missingFields.toString());
		this.missingFields = missingFields;
	}

	public Set<String> getMissingFields() {
		return missingFields;
	}

}
