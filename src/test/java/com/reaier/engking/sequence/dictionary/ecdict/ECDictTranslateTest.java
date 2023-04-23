package com.reaier.engking.sequence.dictionary.ecdict;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Phrase;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.PhraseRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.utils.JsonUtils;
import com.tencentcloudapi.ocr.v20181119.models.Words;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


@Log4j2
class ECDictTranslateTest extends ApplicationTest {

    @Resource
    ECDictRepository repository;

    @Resource
    WordRepository wordRepository;

    @Resource
    PhraseRepository phraseRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void translateWords() {
        long max = 1;
        int page = 0, size = 25000;
        Page<ECDict> pageable;
        while ( ++ page <= max) {
            List<Word> list  = Collections.synchronizedList(new ArrayList<>(2500));
            pageable = repository.findAllBy(PageRequest.of(page - 1, size));
            pageable.getContent().parallelStream().forEach(item -> {
                boolean notWord = item.getWord().isEmpty()
                        || item.getWord().contains(".")
                        || item.getWord().contains("-")
                        || item.getWord().contains(" ")
                        || !StringUtils.hasLength(item.getPhonics())
                        ;

                if (notWord) {
                    log.info("略过 【{}】", item.getWord());
                    return;
                }

                boolean isUpdate = false;
                Word word  = wordRepository.findByName(item.getWord());

                if (Objects.isNull(word)) {
                    word = Word.builder()
                            .name(item.getWord())
                            .definition(item.definition)
                            .translation(item.translation)
                            .phonics(item.phonics)
                            .build();


                    isUpdate = true;
                }

                if (!Objects.equals(item.getPhonics(), word.getPhonics())) {
                    word.setPhonics(item.getPhonics());

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

                if (Objects.nonNull(word.getPhonics()) && word.getPhonics().length() > 512) {
                    word.setPhonics(word.getPhonics().substring(0, 512));
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

            max = pageable.getTotalPages();
        }
    }

    @Test
    void translatePhonetics() {
        long max = 30;
        int page = 0, size = 25000;
        Page<ECDict> pageable;
        while ( ++ page <= max) {
            List<Phrase> list  = Collections.synchronizedList(new ArrayList<>(10000));
            pageable = repository.findAllBy(PageRequest.of(page - 1, size));
            pageable.getContent().parallelStream().forEach(item -> {
                boolean notWord = item.getWord().isEmpty()
                        || !item.getWord().contains(" ")
                        || !StringUtils.hasLength(item.getTranslation())
                        ;

                if (notWord) {
//                    log.info("略过 【{}】", item.getWord());
                    return;
                }

                List<Word> names = Arrays.stream(item.getWord().split(" ")).parallel().map(wordRepository::findByName).collect(Collectors.toList());
                if (names.stream().noneMatch(Objects::nonNull)) {
//                    log.info("略过 【{}】", JsonUtils.obj2Json(item));

                    return;
                }

                String md5          = DigestUtils.md5DigestAsHex(item.getWord().getBytes(StandardCharsets.UTF_8));
                Phrase phrase       = phraseRepository.findByToken(md5);
                boolean isUpdate    = false;


                String ids = names.stream().map(word -> Objects.nonNull(word) ? word.getId() : 0).map(String::valueOf).collect(Collectors.joining(","));
                if (Objects.isNull(phrase)) {
                    phrase = Phrase.builder()
                            .token(md5)
                            .phrase(StringUtils.trimWhitespace(item.getWord()))
                            .phonics(StringUtils.trimWhitespace(item.getPhonics()))
                            .translation(StringUtils.trimWhitespace(item.translation))
                            .wordIds(ids)
                            .build();

                    isUpdate = true;
                }

                if (!Objects.equals(item.getTranslation(), phrase.getTranslation())) {
                    phrase.setTranslation(item.getTranslation());

                    isUpdate = true;
                }

                if (!Objects.equals(item.getPhonics(), phrase.getPhonics())) {
                    phrase.setPhonics(item.getPhonics());

                    isUpdate = true;
                }

                if (!isUpdate) {
                    return;
                }

                if (Objects.nonNull(phrase.getPhrase()) && phrase.getPhrase().length() > 512) {
                    phrase.setPhrase(phrase.getPhrase().substring(0, 512));
                }

                if (Objects.nonNull(phrase.getPhonics()) && phrase.getPhonics().length() > 512) {
                    phrase.setPhonics(phrase.getPhonics().substring(0, 512));
                }

                if (Objects.nonNull(phrase.getTranslation()) && phrase.getTranslation().length() > 512) {
                    phrase.setTranslation(phrase.getTranslation().substring(0, 512));
                }

                list.add(phrase);
            });

            log.info("Page: {}, Size: {}，Total: {}, List: {}", page, size, pageable.getTotalElements(), list.size());
            if (!list.isEmpty()) {
                phraseRepository.saveAll(list);
            }

            max = pageable.getTotalPages();
        }
    }
}