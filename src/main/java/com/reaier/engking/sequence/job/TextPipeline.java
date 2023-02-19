package com.reaier.engking.sequence.job;

import com.reaier.engking.domain.Source;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

/**
 * 将文字类的内容，提取单词、单词还原并提取词性
 * */
public class TextPipeline extends AbstractPipeline {
    @Override
    void pipeline(Source source) throws RuntimeException {
        Properties properties = new Properties();

        //分詞、分句、詞性標註和次元信息。
        properties.put("annotators", "tokenize,pos,lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(source.getContent());
        pipeline.annotate(document);
    }
}