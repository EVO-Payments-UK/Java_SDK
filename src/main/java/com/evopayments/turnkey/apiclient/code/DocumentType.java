package com.evopayments.turnkey.apiclient.code;

/**
 * Type of documents
 */
public enum DocumentType {
    PASSPORT ("PASSPORT"),
    NATIONAL_ID("NATIONAL_ID"),
    DRIVING_LICENSE("DRIVING_LICENSE"),
    UNIQUE_TAXPAYER_REFERENCE("UNIQUE_TAXPAYER_REFERENCE"),
    OTHER("OTHER");

    DocumentType(String code) {
        this.code = code;
    }

    protected String code;

    public String getCode() {
        return code;
    }
}
