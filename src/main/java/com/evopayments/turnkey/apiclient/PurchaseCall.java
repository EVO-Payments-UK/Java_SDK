package com.evopayments.turnkey.apiclient;

import java.io.PrintWriter;
import java.util.Map;

import com.evopayments.turnkey.apiclient.code.ActionType;
import com.evopayments.turnkey.config.ApplicationConfig;

/**
 * Does an authorize and capture operations at once (and cannot be voided)
 * 
 * @author erbalazs
 *
 */
public class PurchaseCall extends BaseApiCall {

	public PurchaseCall(ApplicationConfig config, Map<String, String> inputParams, PrintWriter outputWriter) {
		super(config, inputParams, outputWriter);
	}

	public PurchaseCall(ApplicationConfig config, Map<String, String> inputParams, PrintWriter outputWriter, String subActionType) {
		super(config, inputParams, outputWriter, subActionType);
	}

	@Override
	protected ActionType getActionType() {
		return ActionType.PURCHASE;
	}

}
