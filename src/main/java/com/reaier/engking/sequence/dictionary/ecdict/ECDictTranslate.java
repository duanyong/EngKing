package com.reaier.engking.sequence.dictionary.ecdict;

import com.reaier.engking.Application;
import com.reaier.engking.domain.Word;
import com.reaier.engking.sequence.dictionary.TranslateService;
import com.reaier.engking.sequence.dictionary.exception.TranslateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

/**
 *
 * CURL POST
 *     curl https://fanyi-api.baidu.com/api/trans/vip/translate  -X POST -d 'appid=2015063000000001&q=apple&from=en&to=zh&salt=1435660288&tts=1&dict=1&sign=f89f9594663708c1605f3d736d01d2d4'
 * */
@Slf4j
public class ECDictTranslate implements TranslateService {
    @Override
    public void translate(Word word) throws TranslateException {
        ECDictRepository repository = Application.getContext().getBean(ECDictRepository.class);

        ECDict dict = repository.findByWord(word.getName());
        if (Objects.isNull(dict)) {
            return;
        }

        word.setTranslation(dict.getTranslation());
        word.setDefinition(dict.getDefinition());
        word.setPhonics(dict.getPhonics());
    }
}
