package com.reaier.engking.translate.impl.iciba;


import com.reaier.core.utils.HttpUtil;
import com.reaier.core.utils.JsonUtils;
import com.reaier.engking.domain.trsanslate.word.Mean;
import com.reaier.engking.domain.trsanslate.word.Phonetic;
import com.reaier.engking.domain.trsanslate.word.Word;
import com.reaier.engking.translate.EnToCnTranslateService;
import com.reaier.engking.domain.trsanslate.iciba.ResultJson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class IcibaTranslateServiceImpl implements EnToCnTranslateService {
    private static final String URI  = "http://dict-co.iciba.com/api/dictionary.php";
    private static final String KEY  = "37103FAC497EFDA7CC0B3252A93CAB32";
    private static final String TYPE = "json";


    @Override
    public Word translate(String spell) {
        Map<String, String> params = new HashMap<>();
        params.put("type",      TYPE);
        params.put("key",       KEY);
        params.put("w",         spell.toLowerCase());


        ResultJson result;
        try {
            String response  = HttpUtil.get(HttpUtil.appendQueryParams(URI, params));
            result = JsonUtils.getJsonWithJackson(response, ResultJson.class);
        } catch (IOException e) {
            return null;
        }

        if (result.getPhonetics() == null || result.getPhonetics().length == 0) {
            return null;
        }

        Word word = new Word();
        word.setMeans(new ArrayList<>());
        word.setSpell(spell);

        for (int i = 0, len = result.getPhonetics().length; i<len; ++i) {
            Phonetic phonetic = new Phonetic();
            phonetic.setAmMp3(result.getPhonetics()[0].getAmMp3());
            phonetic.setEnMp3(result.getPhonetics()[0].getEnMp3());
            phonetic.setAmPhonetic(result.getPhonetics()[0].getAmPhonetic());
            phonetic.setEnPhonetic(result.getPhonetics()[0].getEnPhonetic());

            word.setPhonetic(phonetic);

            for (com.reaier.engking.domain.trsanslate.iciba.Mean mean : result.getPhonetics()[0].getMeans()) {
                Mean m = new Mean();
                m.setPart(mean.getPart());
                m.setMeans(mean.getMeanString());

                word.getMeans().add(m);
            }
        }

        return word;
    }
}
