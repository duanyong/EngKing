package com.reaier.engking.repository;

import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {
    Page<Word> findAll(Pageable pageable);
    Word findByNameAndOriginAndTarget(@NotNull String name, @NotNull Language origin, @NotNull Language target);
}
