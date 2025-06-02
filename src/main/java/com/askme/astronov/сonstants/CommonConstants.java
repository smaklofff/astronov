package com.askme.astronov.сonstants;

import lombok.Getter;

@Getter
public enum CommonConstants {
    APPLICATION_JSON("application/json");

    private final String value;

    CommonConstants(String value) {
        this.value = value;
    }

}
