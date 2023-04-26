package com.reaier.engking.repository;

import com.reaier.engking.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word, Integer> {
    Page<Word> findAll(Pageable pageable);
    Word findByName(String name);
}
