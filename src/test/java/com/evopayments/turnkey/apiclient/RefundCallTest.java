package com.evopayments.turnkey.apiclient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.evopayments.turnkey.apiclient.exception.RequiredParamException;
import com.evopayments.turnkey.config.ApplicationConfig;
import com.evopayments.turnkey.config.TestConfig;

public class RefundCallTest extends BaseTest {

	private static ApplicationConfig config;

	@BeforeClass
	public static void setUp() {
		config = TestConfig.getInstance();
	}

	// refund cannot be tested this way (a background job will change the status of transaction, it can take a long time...)

	// @Test
	// public void noExTestCall() {
	//
	// // TOKENIZE
	// final Map<String, String> tokenizeParams = new HashMap<>();
	// tokenizeParams.put("number", "5454545454545454");
	// tokenizeParams.put("nameOnCard", "John Doe");
	// tokenizeParams.put("expiryMonth", "12");
	// tokenizeParams.put("expiryYear", "2018");
	//
	// final TokenizeCall tokenize = new TokenizeCall(config, tokenizeParams, null);
	// final JSONObject tokenizeCall = tokenize.execute();
	//
	// // AUTH
	// final Map<String, String> authParams = new HashMap<>();
	// authParams.put("amount", "20.0");
	// authParams.put("channel", Channel.ECOM.getCode());
	// authParams.put("country", CountryCode.GB.getCode());
	// authParams.put("currency", CurrencyCode.EUR.getCode());
	// authParams.put("paymentSolutionId", "500");
	// authParams.put("customerId", tokenizeCall.getString("customerId"));
	// authParams.put("specinCreditCardToken", tokenizeCall.getString("cardToken"));
	// authParams.put("specinCreditCardCVV", "111");
	//
	// final AuthCall auth = new AuthCall(config, authParams, null);
	// final JSONObject authCall = auth.execute();
	//
	// // CAPTURE
	// final Map<String, String> captureParams = new HashMap<>();
	// captureParams.put("originalMerchantTxId", authCall.getString("merchantTxId"));
	// captureParams.put("amount", "20.0");
	//
	// final CaptureCall captureCall = new CaptureCall(config, captureParams, null);
	// final JSONObject capture = captureCall.execute();
	//
	// // REFUND
	// final Map<String, String> inputParams = new HashMap<>();
	// inputParams.put("originalMerchantTxId", capture.getString("originalMerchantTxId"));
	// inputParams.put("amount", "20.0");
	// inputParams.put("country", "FR");
	// inputParams.put("currency", "EUR");
	//
	// final RefundCall call = new RefundCall(config, inputParams, null);
	// JSONObject result = call.execute();
	//
	// // note that any error will cause the throwing of some kind of SDKException (which extends RuntimeException)
	// // still we make an assertNotNull
	//
	// Assert.assertNotNull(result);
	//
	// }

	/**
	 * RequiredParamException test (intentionally left out param)
	 */
	@Test(expected = RequiredParamException.class)
	public void reqParExExpTestCall() {

		try {

			final Map<String, String> inputParams = new HashMap<>();
			// inputParams.put("originalMerchantTxId", capture.getString("originalMerchantTxId")); // left out field
			inputParams.put("amount", "20.0");
			inputParams.put("country", "FR");
			// inputParams.put("currency", "EUR"); // left out field

			final RefundCall call = new RefundCall(config, inputParams, null);
			call.execute();

		} catch (RequiredParamException e) {

			Assert.assertEquals(new HashSet<>(Arrays.asList("originalMerchantTxId", "currency")), e.getMissingFields());
			throw e;

		}

	}
}
