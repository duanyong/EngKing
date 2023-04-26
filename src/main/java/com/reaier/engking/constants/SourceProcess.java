package com.reaier.engking.constants;

import java.util.List;

public enum SourceProcess {
    START, URL, OCR, LEMMA, TRANSLATION, FINISH;

    public SourceProcess next(List<SourceProcess> circle) {
        int ordinal = circle.size();
        for (int i = 0; i < circle.size(); i ++) {
            if (circle.get(i).equals(this)) {
                ordinal = i;

                break;
            }
        }

        return ( ++ ordinal ) >= circle.size()
                ?  SourceProcess.FINISH
                : circle.get(ordinal);
    }
}