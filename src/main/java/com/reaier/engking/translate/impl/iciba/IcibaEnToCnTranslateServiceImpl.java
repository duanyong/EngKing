package com.reaier.engking.translate.impl.iciba;


import com.reaier.core.utils.HttpUtil;
import com.reaier.core.utils.JsonUtils;
import com.reaier.engking.constants.PartOfSpeech;
import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.translate.EnToCnTranslateService;
import com.reaier.engking.translate.domain.iciba.WordMean;
import com.reaier.engking.translate.domain.iciba.WordDesc;
import com.reaier.engking.translate.impl.iciba.response.IcibaResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IcibaEnToCnTranslateServiceImpl implements EnToCnTranslateService {
    private static final String URI  = "http://dict-co.iciba.com/api/dictionary.php";
    private static final String KEY  = "37103FAC497EFDA7CC0B3252A93CAB32";
    private static final String TYPE = "json";


    @Override
    public EnToCn[] translate(String word) {
        Map<String, String> params = new HashMap<>();
        params.put("type",      TYPE);
        params.put("key",       KEY);
        params.put("w",         word);


        IcibaResult result;
        try {
            String response  = HttpUtil.get(HttpUtil.appendQueryParams(URI, params));
            result = JsonUtils.getJsonWithJackson(response, IcibaResult.class);
        } catch (IOException e) {
            return null;
        }

        if (result.getDescs() == null || result.getDescs().length == 0) {
            return null;
        }

        List<EnToCn> list = new ArrayList<>();
        for (int i=0, len=result.getDescs().length; i<len; ++i) {
            EnWord source = new EnWord();

            WordDesc english = result.getDescs()[0];
            source.setAmMp3(english.getAmMp3());
            source.setAmPhonetic(english.getAmPhonetic());
            source.setEnMp3(english.getEnMp3());
            source.setEnPhonetic(english.getEnPhonetic());

            WordDesc desc = result.getDescs()[i];
            for (WordMean mean : desc.getMeans()) {
                EnToCn tocn = new EnToCn();
                tocn.setPart(PartOfSpeech.valueOf(mean.getPart()));
                tocn.setMeans(mean.getMeanString());

                tocn.setWord(source);

                list.add(tocn);
            }
        }

        return list.toArray(new EnToCn[list.size()]);
    }
}
