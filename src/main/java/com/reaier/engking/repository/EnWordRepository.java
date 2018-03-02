package com.reaier.engking.repository;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.EnWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnWordRepository extends CrudRepository<EnWord, Integer> {
    EnWord findFirstByHash(int hash);

    List<EnWord> findAllByStatus(WordProcess status, Pageable page);
}
