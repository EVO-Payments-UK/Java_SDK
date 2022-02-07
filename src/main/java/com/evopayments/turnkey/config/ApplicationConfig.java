package com.evopayments.turnkey.config;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application properties (request URLs etc.)
 */
abstract public class ApplicationConfig {

	private final static Logger logger = Logger.getLogger(ApplicationConfig.class.getName());

	private final Properties properties = new Properties();

	static {

		// detailed logging for the API HTTP requests/responses (optional)
		
		// USE
		// -Devopayments-turnkey-sdk-http-log=none
		// OR
		// -Devopayments-turnkey-sdk-http-log=verbose
		// (none is the default)

		if (System.getProperty("evopayments-turnkey-sdk-http-log", "none").equals("verbose")) {
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
			System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
			System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");
		}

	}

	public static ApplicationConfig getInstanceBasedOnSysProp() {

		// USE
		// -Devopayments-turnkey-sdk-config=test
		// OR
		// -Devopayments-turnkey-sdk-config=production
		// (test is the default)

		final String configParamStr = System.getProperty("evopayments-turnkey-sdk-config", "test");

		ApplicationConfig config;

		if (configParamStr.equals("production")) {
			config = ProductionConfig.getInstance();
		} else {
			config = TestConfig.getInstance();
		}

		logger.log(Level.INFO, "active config: " + config.getFilename());

		return config;

	}

	public ApplicationConfig() {

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		try (InputStream input = classLoader.getResourceAsStream(getFilename())) {
			properties.load(input);
		} catch (final Exception e) {
			logger.log(Level.SEVERE, "could not load config", e);
			throw new RuntimeException("could not load config", e);
		}
	}

	/**
	 * Read values from properties
	 *
	 * @param key
	 * @return a value from properties
	 */
	public String getProperty(final String key) {
		return properties.getProperty(key);
	}

	protected abstract String getFilename();
}
