package com.reaier.engking.constants;

public enum SourceProcess {
    WAIT("wait"), DOING("doing"), DONE("done"), FAIL("fail");

    private String type;

    private SourceProcess(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
