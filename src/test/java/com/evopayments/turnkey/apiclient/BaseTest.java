package com.evopayments.turnkey.apiclient;

import com.evopayments.turnkey.apiclient.code.Channel;
import com.evopayments.turnkey.apiclient.code.CountryCode;
import com.evopayments.turnkey.apiclient.code.CurrencyCode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Base class for test cases. Provide common helper methods for subclasses.
 *
 */
public class BaseTest {

    protected Map<String, String> buildTokenizeParam(){
        Map<String, String> tokenizeParams = new HashMap<>();
        tokenizeParams.put("number", "5413330300002004");
        tokenizeParams.put("nameOnCard", "John Doe");
        tokenizeParams.put("expiryMonth", "12");
        tokenizeParams.put("expiryYear", "2028");

        return tokenizeParams;
    }

    protected  void addCommonParams(Map<String, String> authParams){
        authParams.put("channel", Channel.ECOM.getCode());
        authParams.put("country", CountryCode.PL.getCode());
        authParams.put("currency", CurrencyCode.PLN.getCode());
        authParams.put("paymentSolutionId", "500");
    }
}


