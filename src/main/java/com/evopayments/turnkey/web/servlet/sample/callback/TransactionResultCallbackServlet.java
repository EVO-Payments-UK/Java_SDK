package com.evopayments.turnkey.web.servlet.sample.callback;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evopayments.turnkey.web.servlet.sample.s2s.AbstractServlet;

/**
 * When an operation is completed (successfully or not), a notification is sent to inform the
 * merchant about the result and the current status of the payment. This notification is sent to the
 * url provided as merchantNotificationUrl at the session token request by the merchant...
 * 
 * @author erbalazs
 */
@WebServlet(name = "TransactionResultCallback", description = "TransactionResultCallback servlet", urlPatterns = "/transactionresultcallback")
public class TransactionResultCallbackServlet extends HttpServlet {

	private final static Logger logger = Logger.getLogger(TransactionResultCallbackServlet.class.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		final Map<String, String> notificationParams = AbstractServlet.extractParams(req);

		logger.info("transaction notification callback servlet: " + notificationParams);
		resp.getWriter().println("notificationParams: " + notificationParams);
	}

}
