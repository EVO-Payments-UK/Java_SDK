package com.evopayments.turnkey.apiclient;

import com.evopayments.turnkey.apiclient.code.ActionType;
import com.evopayments.turnkey.config.ApplicationConfig;

import java.io.PrintWriter;
import java.util.Map;

/**
 * Does an authorize and capture operations at once (and cannot be voided)
 *
 * @author erbalazs
 *
 */
public class VerifyCall extends BaseApiCall {

    public VerifyCall(ApplicationConfig config, Map<String, String> inputParams, PrintWriter outputWriter) {
        super(config, inputParams, outputWriter);
    }

    public VerifyCall(ApplicationConfig config, Map<String, String> inputParams, PrintWriter outputWriter, String subActionType) {
        super(config, inputParams, outputWriter, subActionType);
    }

    @Override
    protected ActionType getActionType() {
        return ActionType.VERIFY;
    }

}
