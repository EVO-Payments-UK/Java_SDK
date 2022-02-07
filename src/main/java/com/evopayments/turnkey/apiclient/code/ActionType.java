package com.evopayments.turnkey.apiclient.code;

/**
 * Type of actions
 */
public enum ActionType {
    TOKENIZE("TOKENIZE"),
    AUTH("AUTH"),
    CAPTURE("CAPTURE"),
    VOID("VOID"),
    PURCHASE("PURCHASE"),
    REFUND("REFUND"),
    GET_AVAILABLE_PAYSOLS("GET_AVAILABLE_PAYSOLS"),
    STATUS_CHECK("STATUS_CHECK"),
    VERIFY("VERIFY");

    private final String code;

    ActionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ActionType valueOfCode(String code) {
        for (ActionType actionType : values()) {
            if (actionType.code.equals(code)) {
                return actionType;
            }
        }
        throw new IllegalArgumentException(code);
    }

}
