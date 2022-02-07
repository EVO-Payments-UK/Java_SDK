package com.evopayments.turnkey.apiclient.code;

/**
 * Type of sex
 */
public enum Sex {
    MALE("M"),
    FEMALE("F");

    Sex(String code) {
        this.code = code;
    }

    protected String code;

    public String getCode() {
        return code;
    }
}
