package com.evopayments.turnkey.apiclient.code;

/**
 * Type of channels
 */
public enum Channel {
    ECOM("ECOM"),
    MOTO("MOTO");

    private final String code;

    Channel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
