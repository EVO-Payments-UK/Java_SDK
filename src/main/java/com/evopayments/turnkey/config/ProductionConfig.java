package com.evopayments.turnkey.config;


public class ProductionConfig extends ApplicationConfig {
	
	private static ProductionConfig instance;

	/**
	 * Java singleton implementation
	 *
	 * @return the instance from this object
	 */
	public static ProductionConfig getInstance() {
		if (instance == null) {
			instance = new ProductionConfig();
		}
		return instance;
	}
	
	private ProductionConfig(){
		// use getInstance...
	}

	@Override
	protected String getFilename() {
		return "application-production.properties";
	}
	
}
