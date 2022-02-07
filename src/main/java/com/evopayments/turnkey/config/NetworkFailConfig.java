package com.evopayments.turnkey.config;

/**
 * Intentionally not valid / wrong URLs, can be used for network exception unit tests
 * 
 * @author erbalazs
 *
 */
public class NetworkFailConfig extends ApplicationConfig {
	
	private static NetworkFailConfig instance;

	/**
	 * Java singleton implementation
	 *
	 * @return the instance from this object
	 */
	public static NetworkFailConfig getInstance() {
		if (instance == null) {
			instance = new NetworkFailConfig();
		}
		return instance;
	}
	
	private NetworkFailConfig(){
		// use getInstance...
	}

	@Override
	protected String getFilename() {
		return "application-network-fail.properties";
	}
	
}
