package com.reaier.engking.constants;

public enum WordProcess {
    WAIT("wait"), DOING("doing"), DONE("done"), FAIL("fail");

    private String type;

    private WordProcess(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
