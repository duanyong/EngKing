package com.reaier.engking.dictionary.publisher;


import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LemmatizePublisher {

    /**
     * 根據string獲取對應原始詞性詞彙列表
     * @param text
     *
     * @return
     */
    public static List<String> getOriginalText(String text){
        List<String> wordList = new ArrayList<>();
        Properties properties = new Properties();

        //分詞、分句、詞性標註和次元信息。
        properties.put("annotators", "tokenize,pos,lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        for(CoreLabel tok : document.tokens()) {
//            for (CoreLabel token: wordTemp.get(CoreAnnotations.TokensAnnotation.class)) {
//                String originalWord = token.get(CoreAnnotations.LemmaAnnotation.class);
//
//                wordList.add(originalWord);
//            }
        }

        return wordList;
    }
}
