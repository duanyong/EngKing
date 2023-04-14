package com.reaier.engking.sequence.dictionary.baidu;

import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.Word;
import com.reaier.engking.sequence.dictionary.TranslateService;
import com.reaier.engking.sequence.dictionary.baidu.response.ICIBAResponse;
import com.reaier.engking.sequence.dictionary.exception.TranslateException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BaiDuTranslate implements TranslateService {
    private static final String URI  = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private static final String ID   = "37103FAC497EFDA7CC0B3252A93CAB32";
    private static final String KEY  = "Lps89W7r_MCV_Ir1jlLP";

    @Override
    public void translate(Word word) throws TranslateException {
        Map<String, String> params = new HashMap<>();
        params.put("appid",     ID);
        params.put("q",         KEY);
        params.put("from",      getShort(word.getOrigin()));
        params.put("to",        getShort(word.getTarget()));
        params.put("tts",       "1");
        params.put("dict",      "1");
        params.put("salt",      RandomStringUtils.random(6));
        params.put("sign",      DigestUtils.md5DigestAsHex(String.format("%s%s%s%s", ID, word.getName(), params.get("salt"), KEY).getBytes(StandardCharsets.UTF_8)));

        ICIBAResponse response = curl(params);

    }

    private ICIBAResponse curl(Map params) {
        RequestEntity requestEntity = RequestEntity.method(HttpMethod.POST, java.net.URI.create(URI))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(params);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ICIBAResponse> responseEntity = restTemplate.exchange(requestEntity, ICIBAResponse.class);

        return responseEntity.getBody();
    }


    private String getShort(Language target) {
        switch (target) {
            case CHINESE: return "zh";
            case ENGLISH: return "en";
        }

        return "en";
    }
}
