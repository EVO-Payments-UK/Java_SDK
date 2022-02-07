package com.evopayments.turnkey.apiclient;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.evopayments.turnkey.apiclient.exception.PostToApiException;
import com.evopayments.turnkey.config.ApplicationConfig;
import com.evopayments.turnkey.config.NetworkFailConfig;

public class NetworkingFailTest extends  BaseTest{

	private static ApplicationConfig config;

	@BeforeClass
	public static void setUp() {
		config = NetworkFailConfig.getInstance(); // intentional network error (wrong/malformed URLs)
	}

	/**
	 * PostToApiException test (intentional network error (wrong URLs))
	 */
	@Test(expected = PostToApiException.class)
	public void networkingExExpTestCall() {

		final Map<String, String> inputParams = new HashMap<>();
		inputParams.put("country", "FR");
		inputParams.put("currency", "EUR");

		final GetAvailablePaymentSolutionsCall call = new GetAvailablePaymentSolutionsCall(config, inputParams, null);
		call.execute();
		
	}

}
