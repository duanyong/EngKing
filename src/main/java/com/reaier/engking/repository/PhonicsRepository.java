package com.reaier.engking.repository;

import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.Phonics;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonicsRepository extends CrudRepository<Phonics, Integer> {
    Slice<Phonics> findAllByLanguageAndWordId(Language language, Integer wordId);
}
