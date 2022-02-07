package com.evopayments.turnkey.apiclient.code;

/**
 * Type of user devices
 */
public enum UserDevice {
    MOBILE("MOBILE"),
    DESKTOP("DESKTOP"),
    UNKNOWN("UNKNOWN");

    private String code;

    UserDevice(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
