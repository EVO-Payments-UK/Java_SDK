package com.evopayments.turnkey.web.servlet.sample.b2s;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evopayments.turnkey.apiclient.PurchaseTokenCall;
import com.evopayments.turnkey.config.ApplicationConfig;
import com.evopayments.turnkey.web.servlet.sample.s2s.AbstractServlet;
import org.json.JSONObject;

import com.evopayments.turnkey.apiclient.exception.ActionCallException;
import com.evopayments.turnkey.apiclient.exception.GeneralException;
import com.evopayments.turnkey.apiclient.exception.PostToApiException;
import com.evopayments.turnkey.apiclient.exception.RequiredParamException;
import com.evopayments.turnkey.apiclient.exception.TokenAcquirationException;

/**
 * Token acquiration used in Browser-to-Server mode
 * 
 * @author erbalazs
 *
 */
@WebServlet(name = "PurchaseToken", description = "Token acquiration used in Browser-to-Server mode", urlPatterns = "/tokenforpurchase")
public class PurchaseTokenServlet extends HttpServlet {

	private final static Logger logger = Logger.getLogger(PurchaseTokenServlet.class.getName());
	
	protected static final String MERCHANT_ID_PROP_KEY = "application.merchantId";

	protected final ApplicationConfig config;

	public PurchaseTokenServlet() {
		super();
		config = ApplicationConfig.getInstanceBasedOnSysProp();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			final Map<String, String> inputParams = AbstractServlet.extractParams(req);
			
			JSONObject jsonObject = new PurchaseTokenCall(config, inputParams, new PrintWriter(System.out, true)).execute();
			jsonObject.put("merchantId", config.getProperty(MERCHANT_ID_PROP_KEY));
			
			resp.getWriter().print(jsonObject.toString());
			
		} catch (RequiredParamException e) {
			resp.setStatus(422);
			logger.log(Level.INFO, "missing parameters", e);
		} catch (TokenAcquirationException e) {
			resp.setStatus(500);
			logger.log(Level.WARNING, "could not acquire token", e);
		} catch (ActionCallException e) {
			resp.setStatus(500);
			logger.log(Level.WARNING, "error during the action call", e);
		} catch (PostToApiException e) {
			resp.setStatus(500);
			logger.log(Level.SEVERE, "outgoing POST failed", e);
		} catch (GeneralException e) {
			resp.setStatus(500);
			logger.log(Level.SEVERE, "general SDK error", e);
		}
	}

}
