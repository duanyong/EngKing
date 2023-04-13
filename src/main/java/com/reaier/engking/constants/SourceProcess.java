package com.reaier.engking.constants;

import java.util.List;

public enum SourceProcess {
    URL, OCR, TEXT, TRANSLATION, DONE;

    public SourceProcess next(List<SourceProcess> circle) {
        for (int i = 0; i < circle.size(); i++) {
            if (circle.get(i).equals(this)) {
                return circle.get(i + 1);
            }
        }

        return null;
    }
}