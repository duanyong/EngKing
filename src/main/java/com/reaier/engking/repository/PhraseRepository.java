package com.reaier.engking.repository;

import com.reaier.engking.domain.Phrase;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhraseRepository extends CrudRepository<Phrase, String> {
    Page<Phrase> findAll(Pageable pageable);
    Phrase findByToken(@NotNull String token);
}
