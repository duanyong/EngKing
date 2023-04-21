package com.reaier.engking.sequence.dictionary.ecdict;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.WordRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


@Log4j2
class ECDictTranslateTest extends ApplicationTest {

    @Resource
    ECDictRepository repository;

    @Resource
    WordRepository wordRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void translateWords() {
        long max = 9;
        int page = 8, size = 25000;
        Page<ECDict> pageable;
        while ( ++ page <= max) {
            List<Word> list  = Collections.synchronizedList(new ArrayList<>(2500));
            pageable = repository.findAllBy(PageRequest.of(page - 1, size));
            pageable.getContent().parallelStream().forEach(item -> {
                boolean notWord = item.getWord().isEmpty()
                        || item.getWord().contains(".")
                        || item.getWord().contains("-")
                        || item.getWord().contains(" ")
                        || !StringUtils.hasLength(item.getPhonetic())
                        ;

                if (notWord) {
//                    log.info("略过 【{}】", item.getWord());
                    return;
                }

                boolean isUpdate = false;
                long start = new Date().getTime();
                Word word  = wordRepository.findByName(item.getWord());
                long end   = new Date().getTime();

//                log.info("speed ms: {}", end - start);
                if (Objects.isNull(word)) {
                    word = Word.builder()
                            .name(item.getWord())
                            .definition(item.definition)
                            .translation(item.translation)
                            .phonics(item.phonetic)
                            .build();


                    isUpdate = true;
                }

                if (!Objects.equals(item.getPhonetic(), word.getPhonics())) {
                    word.setPhonics(item.getPhonetic());

                    isUpdate = true;
                }

                if (!Objects.equals(item.getDefinition(), word.getDefinition())) {
                    word.setDefinition(item.getDefinition());

                    isUpdate = true;
                }

                if (!Objects.equals(item.getTranslation(), word.getTranslation())) {
                    word.setTranslation(item.getTranslation());

                    isUpdate = true;
                }

                if (!isUpdate) {
                    return;
                }

                if (Objects.nonNull(word.getTranslation()) && word.getTranslation().length() > 2048) {
                    word.setTranslation(word.getTranslation().substring(0, 2048));
                }

                if (Objects.nonNull(word.getDefinition()) && word.getDefinition().length() > 2048) {
                    word.setDefinition(word.getDefinition().substring(0, 2048));
                }

                list.add(word);
            });

            log.info("Page: {}, Size: {}，Total: {}, List: {}", page, size, pageable.getTotalElements(), list.size());
            if (!list.isEmpty()) {
                wordRepository.saveAll(list);
            }

            max = pageable.getTotalElements();
        }
    }
}