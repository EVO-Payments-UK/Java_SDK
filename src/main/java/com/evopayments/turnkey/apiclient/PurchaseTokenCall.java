package com.evopayments.turnkey.apiclient;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.evopayments.turnkey.apiclient.code.ActionType;
import com.evopayments.turnkey.apiclient.exception.RequiredParamException;
import com.evopayments.turnkey.config.ApplicationConfig;

/**
 * Only token acquiration for purchase
 * 
 * @author erbalazs
 *
 * @see PurchaseCall
 */
public class PurchaseTokenCall extends ApiCall {

	public PurchaseTokenCall(ApplicationConfig config, Map<String, String> inputParams, PrintWriter outputWriter) {
		super(config, inputParams, outputWriter);
	}

	@Override
	protected ActionType getActionType() {
		return ActionType.PURCHASE;
	}

	@Override
	protected void preValidateParams(final Map<String, String> inputParams) throws RequiredParamException {

		final Set<String> requiredParams = new HashSet<>(Arrays.asList("amount", "channel", "country", "currency", "paymentSolutionId"));

		for (final Map.Entry<String, String> entry : inputParams.entrySet()) {

			if ((entry.getValue() != null) && !entry.getValue().trim().isEmpty()) {
				requiredParams.remove(entry.getKey());
			}

		}

		if (!requiredParams.isEmpty()) {
			throw new RequiredParamException(requiredParams);
		}

		
	}

	@Override
	protected Map<String, String> getTokenParams(final Map<String, String> inputParams) {

		final Map<String, String> tokenParams = new HashMap<>();

		tokenParams.put("merchantId", config.getProperty(MERCHANT_ID_PROP_KEY));
		tokenParams.put("password", config.getProperty(PASSWORD_PROP_KEY));
		tokenParams.put("action", getActionType().getCode());
		tokenParams.put("timestamp", String.valueOf(System.currentTimeMillis()));
		tokenParams.put("allowOriginUrl", config.getProperty(ALLOW_ORIGIN_URL_PROP_KEY));
		tokenParams.put("channel", inputParams.get("channel"));
		tokenParams.put("amount", inputParams.get("amount"));
		tokenParams.put("currency", inputParams.get("currency"));
		tokenParams.put("country", inputParams.get("country"));
		tokenParams.put("paymentSolutionId", inputParams.get("paymentSolutionId"));
		tokenParams.put("merchantNotificationUrl", config.getProperty(MERCHANT_NOTIFICATION_URL_PROP_KEY));
		tokenParams.put("merchantLandingPageUrl", config.getProperty(MERCHANT_LANDING_PAGE_URL_PROP_KEY));

		return tokenParams;
	}

	@Override
	protected Map<String, String> getActionParams(final Map<String, String> inputParams, final String token) {
		return null;
	}
}
