package com.reaier.engking.sequence.publisher;


import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.events.LemmatizeEvent;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;
import java.util.*;

public class LemmaPublisher {

    @Resource
    ApplicationEventPublisher publisher;

    WordRepository wordRepository;

    @EventListener
    public void doEvent(LemmatizeEvent event) {
        lemmatize((Source) event.getSource());
    }

    /**
     * 根据提供的Source，对其内容进行分解为原始的单词
     * @param source
     *
     */
    public Set<String> lemmatize(Source source){
        Set<String> wordList = new HashSet<>();
        Properties properties = new Properties();

        //分詞、分句、詞性標註和次元信息。
        properties.put("annotators", "tokenize,pos,lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(source.getContent());
        pipeline.annotate(document);

        Word word;
        for (CoreLabel tok : document.tokens()) {
            for (CoreLabel token: tok.get(CoreAnnotations.TokensAnnotation.class)) {
                String originalWord = token.get(CoreAnnotations.LemmaAnnotation.class);

                wordList.add(originalWord);

                word = wordRepository.findByNameAndLanguage(originalWord, source.getTarget());
                if (Objects.isNull(word)) {

                }
            }
        }

        return wordList;
    }
}
