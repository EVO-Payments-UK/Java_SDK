package com.evopayments.turnkey.apiclient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.evopayments.turnkey.apiclient.exception.RequiredParamException;
import com.evopayments.turnkey.config.ApplicationConfig;
import com.evopayments.turnkey.config.TestConfig;

public class GetAvailablePaymentSolutionsCallTest {

	private static ApplicationConfig config;

	@BeforeClass
	public static void setUp() {
		config = TestConfig.getInstance();
	}

	/**
	 * successful case
	 */
	@Test
	public void noExTestCall() {

		final Map<String, String> inputParams = new HashMap<>();
		inputParams.put("country", "PL");
		inputParams.put("currency", "PLN");

		final GetAvailablePaymentSolutionsCall call = new GetAvailablePaymentSolutionsCall(config, inputParams, null);
		JSONObject result = call.execute();


		// note that any error will cause the throwing of some kind of SDKException (which extends RuntimeException)
		// still we make an assertNotNull

		Assert.assertNotNull(result);
		Assert.assertEquals(result.get("result"), "success");

	}

	/**
	 * RequiredParamException test (intentionally left out param)
	 */
	@Test(expected = RequiredParamException.class)
	public void reqParExExpTestCall() {

		try {

			final Map<String, String> inputParams = new HashMap<>();
			inputParams.put("country", "FR");
			// inputParams.put("currency", "EUR"); // intentionally left out

			final GetAvailablePaymentSolutionsCall call = new GetAvailablePaymentSolutionsCall(config, inputParams, null);
			call.execute();

		} catch (RequiredParamException e) {

			Assert.assertEquals(new HashSet<>(Arrays.asList("currency")), e.getMissingFields());
			throw e;

		}
	}

}
