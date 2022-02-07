package com.evopayments.turnkey.config;


public class TestConfig extends ApplicationConfig {
	
	private static TestConfig instance;

	/**
	 * Java singleton implementation
	 *
	 * @return the instance from this object
	 */
	public static TestConfig getInstance() {
		if (instance == null) {
			instance = new TestConfig();
		}
		return instance;
	}
	
	private TestConfig(){
		// use getInstance...
	}

	@Override
	protected String getFilename() {
		return "application-test.properties";
	}
	
}
