package com.evopayments.turnkey.apiclient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.evopayments.turnkey.apiclient.exception.ActionCallException;
import com.evopayments.turnkey.apiclient.exception.RequiredParamException;
import com.evopayments.turnkey.config.ApplicationConfig;
import com.evopayments.turnkey.config.TestConfig;

public class TokenizeCallTest extends BaseTest{

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

		final Map<String, String> inputParams = super.buildTokenizeParam();

		final TokenizeCall call = new TokenizeCall(config, inputParams, null);
		JSONObject result = call.execute();

		// note that any error will cause the throwing of some kind of SDKException (which extends RuntimeException)
		// still we make an assertNotNull

		Assert.assertNotNull(result);
	}

	/**
	 * RequiredParamException test (intentionally left out param)
	 */
	@Test(expected = RequiredParamException.class)
	public void reqParExExpTestCall() {

		try {

			final Map<String, String> inputParams = new HashMap<>();
			inputParams.put("number", "5454545454545454");
			inputParams.put("nameOnCard", "John Doe");
			inputParams.put("expiryMonth", "12");
			// inputParams.put("expiryYear", "2018"); // intentionally left out

			final TokenizeCall call = new TokenizeCall(config, inputParams, null);
			call.execute();

		} catch (RequiredParamException e) {

			Assert.assertEquals(new HashSet<>(Arrays.asList("expiryYear")), e.getMissingFields());
			throw e;

		}
	}

	/**
	 * ActionCallException test (via intentionally wrong expiryYear)
	 */
	@Test(expected = ActionCallException.class)
	public void actCallExExpTestCall() {

		final Map<String, String> inputParams = new HashMap<>();
		inputParams.put("number", "5454545454545454");
		inputParams.put("nameOnCard", "John Doe");
		inputParams.put("expiryMonth", "12");
		inputParams.put("expiryYear", "2010"); // past date

		final TokenizeCall call = new TokenizeCall(config, inputParams, null);
		call.execute();
	}
}
