package com.reaier.engking.constants;

public enum Dialect {
    Chinese("cn"),

    English("en"),
    American("us"),
    ;

    private String dialect;

    Dialect(String dialect) {
        this.dialect = dialect;
    }
}
