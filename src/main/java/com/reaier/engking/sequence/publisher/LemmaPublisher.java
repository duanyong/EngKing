package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.SourceWord;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.SourceWordRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;
import java.util.*;

public class LemmaPublisher extends AbstractProcessPublisher {
    @Resource
    WordRepository wordRepository;

    @Resource
    SourceWordRepository sourceWordRepository;

    @EventListener
    public void doEvent(OCREvent event) {
        Source source = (Source) event.getSource();

        Set<String> wordList = new HashSet<>();
        Properties properties = new Properties();

        //分詞、分句、詞性標註和次元信息。
        properties.put("annotators", "tokenize,pos,lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(source.getContent());
        pipeline.annotate(document);

        Word word;
        SourceWord sourceWord;
        for (CoreLabel tok : document.tokens()) {
            for (CoreLabel token: tok.get(CoreAnnotations.TokensAnnotation.class)) {
                String originalWord = token.get(CoreAnnotations.LemmaAnnotation.class);

                wordList.add(originalWord);

                word = wordRepository.findByNameAndLanguage(originalWord, source.getTarget());
                if (Objects.isNull(word)) {
                    word = wordRepository.save(Word.builder().name(originalWord).language(source.getTarget()).build());
                }

                sourceWord = sourceWordRepository.findBySourceIdAndWordId(source.getId(), word.getId());
                if (Objects.isNull(sourceWord)) {
                    sourceWord = SourceWord.builder().sourceId(source.getId()).wordId(word.getId()).build();

                    if (Objects.isNull(sourceWord)) {
                        sourceWordRepository.save(sourceWord);
                    }
                }
            }
        }

        source.setProcessStatus(SourceProcessStatus.DONE);
        sourceRepository.save(source);
    }
}