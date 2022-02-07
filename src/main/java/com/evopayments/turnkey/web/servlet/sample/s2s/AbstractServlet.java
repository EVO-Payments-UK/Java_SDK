package com.evopayments.turnkey.web.servlet.sample.s2s;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evopayments.turnkey.config.ApplicationConfig;
import com.evopayments.turnkey.apiclient.exception.ActionCallException;
import com.evopayments.turnkey.apiclient.exception.GeneralException;
import com.evopayments.turnkey.apiclient.exception.PostToApiException;
import com.evopayments.turnkey.apiclient.exception.RequiredParamException;
import com.evopayments.turnkey.apiclient.exception.TokenAcquirationException;

/**
 * Sample servlet base class
 * 
 * @author erbalazs
 *
 */
public abstract class AbstractServlet extends HttpServlet {

	private final static Logger logger = Logger.getLogger(AbstractServlet.class.getName());

	public static Map<String, String> extractParams(final HttpServletRequest request) {

		final HashMap<String, String> requestMap = new HashMap<>();

		final Map<String, String[]> parameterMap = request.getParameterMap();

		final Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
		while (iterator.hasNext()) {
			final Map.Entry<String, String[]> entry = iterator.next();
			requestMap.put(entry.getKey(), entry.getValue()[0]);
		}

		return requestMap;
	}
	
	protected final ApplicationConfig config;
	
	public AbstractServlet() {
		super();
	
		config = ApplicationConfig.getInstanceBasedOnSysProp();
	}

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		try {
			process(req, resp);
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
		} catch (Exception e) {
			resp.setStatus(500);
			logger.log(Level.SEVERE, "other error", e);
		}
	}

	protected abstract void process(final HttpServletRequest req, final HttpServletResponse resp) throws RequiredParamException, ActionCallException, TokenAcquirationException, GeneralException, IOException;
}
