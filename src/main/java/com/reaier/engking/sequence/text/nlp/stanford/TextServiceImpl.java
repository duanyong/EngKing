package com.reaier.engking.sequence.text.nlp.stanford;

import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.Word;
import com.reaier.engking.sequence.text.TextService;
import com.reaier.engking.sequence.text.exception.TextException;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 将文字类的内容，提取单词、单词还原并提取词性
 * */
@Service
public class TextServiceImpl implements TextService {

    @Override
    public void tokenize(Source source) throws TextException {
        Properties properties = new Properties();

        //分詞、分句、詞性標註和次元信息。
        properties.put("annotators", "tokenize,pos,lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(source.getContent());
        pipeline.annotate(document);

        List<CoreLabel> tokens = document.tokens();
        Set<Word> lemma = new HashSet<>(tokens.size());
        for (CoreLabel token : document.tokens()) {
//            if (token.isMWT()) {}
            lemma.add(Word.builder().target(source.getOrigin()).name(token.lemma()).build());
        }

        source.setLemma(lemma);
    }
}