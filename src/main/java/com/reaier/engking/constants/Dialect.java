package com.reaier.engking.constants;

public enum Dialect {
    Chinese("cn"),

    English("en"),
    American("am"),
    ;

    private String dialect;

    Dialect(String dialect) {
        this.dialect = dialect;
    }

    public String toString() {
        return dialect;
    }
}
