package com.reaier.engking.repository;

import com.reaier.engking.domain.EnWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnWordRepository extends CrudRepository<EnWord, Integer> {
    Page<EnWord> findAll(Pageable pageable);
}
