package com.winchesters.devopsify.enums;

public enum ReadMeStatus {
    EMPTY("EMPTY"),
    SMALL("SMALL"),
    DOES_NOT_CONTAIN_TITLE("DOES NOT CONTAIN TITLE"),
    DOES_NOT_CONTAIN_HOW_TO_USE("DOES NOT CONTAIN HOW TO USE"),
    DOES_NOT_CONTAIN_TECHNOLOGIES("DOES NOT CONTAIN TECHNOLOGIES"),
    DOES_NOT_CONTAIN_VERSION("DOES NOT CONTAIN VERSION"),
    DOES_NOT_CONTAIN_STATUS("DOES NOT CONTAIN STATUS"),
    OKAY("OKAY");
    private final String value;
    ReadMeStatus(String value) {
        this.value = value;
    }
}
