package com.reaier.engking.sequence.dictionary.baidu;

import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.Word;
import com.reaier.engking.sequence.dictionary.TranslateService;
import com.reaier.engking.sequence.dictionary.baidu.response.ICIBAResponse;
import com.reaier.engking.sequence.dictionary.exception.TranslateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 * CURL POST
 *     curl https://fanyi-api.baidu.com/api/trans/vip/translate  -X POST -d 'appid=2015063000000001&q=apple&from=en&to=zh&salt=1435660288&tts=1&dict=1&sign=f89f9594663708c1605f3d736d01d2d4'
 * */
@Slf4j
@Service
public class BaiDuTranslate implements TranslateService {
    private static final String URI  = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    private static final String ID   = "20200327000406635";
    private static final String KEY  = "Lps89W7r_MCV_Ir1jlLP";

//    private static final String ID   = "2015063000000001";
//    private static final String KEY  = "12345678";

    @Override
    public void translate(Word word) throws TranslateException {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("appid",     ID);
        params.add("q",         word.getName());
        params.add("from",      getShort(word.getOrigin()));
        params.add("to",        getShort(word.getTarget()));
//        params.add("tts",       "0");
//        params.add("dict",      "0");

        String salt = RandomStringUtils.randomAlphabetic(6);
        String sign = String.format("%s%s%s%s", ID, word.getName(), salt, KEY);

        params.add("salt",      salt);
        params.add("sign",      DigestUtils.md5DigestAsHex(sign.getBytes(StandardCharsets.UTF_8)));

        ICIBAResponse response = curl(params);

    }

    private ICIBAResponse curl(Map params) {
        RequestEntity requestEntity = RequestEntity.method(HttpMethod.POST, java.net.URI.create(URI))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(params);

        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<ICIBAResponse> responseEntity = restTemplate.exchange(requestEntity, ICIBAResponse.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        log.debug(responseEntity.getBody());

        return null;
    }

     private String getShort(Language target) {
        switch (target) {
            case CHINESE: return "zh";
            case ENGLISH: return "en";
        }

        return "en";
    }
}
