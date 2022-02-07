package com.evopayments.turnkey.web.servlet.sample.s2s;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evopayments.turnkey.apiclient.AuthCall;
import com.evopayments.turnkey.apiclient.exception.ActionCallException;
import com.evopayments.turnkey.apiclient.exception.GeneralException;
import com.evopayments.turnkey.apiclient.exception.RequiredParamException;
import com.evopayments.turnkey.apiclient.exception.TokenAcquirationException;

/**
 * Sample
 * 
 * @author erbalazs
 *
 */
@WebServlet(name = "Auth", description = "Auth operation", urlPatterns = "/auth")
public class AuthServlet extends AbstractServlet {

	@Override
	protected void process(final HttpServletRequest req, final HttpServletResponse resp) throws RequiredParamException, ActionCallException, TokenAcquirationException, GeneralException, IOException {

		final Map<String, String> inputParams = extractParams(req);
		new AuthCall(config, inputParams, resp.getWriter()).execute();

	}
}
