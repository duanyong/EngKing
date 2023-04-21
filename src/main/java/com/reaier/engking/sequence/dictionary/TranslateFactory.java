package com.reaier.engking.sequence.dictionary;

import com.reaier.engking.domain.Word;
import com.reaier.engking.sequence.dictionary.baidu.BaiDuTranslate;
import com.reaier.engking.sequence.dictionary.exception.TranslateException;

public final class TranslateFactory {
    public void translate(Word word, TranslateApi api) throws TranslateException {
        TranslateService service;

        switch (api) {
            case
            case BAIDU:
                service = new BaiDuTranslate();

            default:
                service = new BaiDuTranslate();
        }

        service.translate(word);
    }
}
