package com.reaier.engking.sequence.text.nlp.stanford;

import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.text.TextService;
import com.reaier.engking.sequence.text.exception.TextException;
import com.reaier.engking.utils.WordUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 将文字类的内容，提取单词、单词还原并提取词性
 * */
@Slf4j
@Service
public class TextServiceImpl implements TextService {

    @Override
    public void tokenize(Source source) throws TextException {
        Properties properties = new Properties();

        //分詞、分句、詞性標註和次元信息。
        properties.put("annotators", "tokenize,pos,lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(source.getTexts());
        pipeline.annotate(document);

        Map<String, String> lemmas = new HashMap<>();
        for (CoreLabel token : document.tokens()) {
            if (!WordUtils.isWord(token.word()) || token.originalText().equals(token.lemma()) || Objects.nonNull(lemmas.get(token.originalText()))) {
                continue;
            }

            lemmas.put(token.originalText(), token.lemma());
        }

        source.setLemmas(lemmas);
    }
}