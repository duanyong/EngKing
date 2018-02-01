package com.reaier.engking.constants;

public enum SourceType {
    URL("url"), TEXT("text"), IMAGE("image");

    private String type;

    private SourceType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
