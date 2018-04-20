package com.reaier.engking.constants;

public enum RecitalPlan {
    NOPLAN("noplan"),
    STRANGE("strange"),
    ORDINARY("ordinary"),
    FAMILIAR("familiar"),
    OUTSIDE("outside");

    private String type;

    private RecitalPlan(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
