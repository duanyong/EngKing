package com.reaier.engking.constants;

//艾宾浩斯 记忆曲线
public enum Ebbinghaus {
    FIVEMIN("five min"),
    THIRTYMIN("30 min"),
    TWELVEHOUR("12 hour"),
    ONEDAY("1 day"),
    TWODAY("2 day"),
    FOURDAY("4 day"),
    SEVENDAY("7 day"),
    FAMILIAR("15 day");

    private String type;

    private Ebbinghaus(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
