package com.reaier.engking.constants;

public enum Dialect {
    Chinese("cn"),

    English("en"),
    American("us"),
    ;

    private String symbol;

    Dialect(String symbol) {
        this.symbol = symbol;
    }
}
