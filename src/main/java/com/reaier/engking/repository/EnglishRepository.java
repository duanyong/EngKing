package com.reaier.engking.repository;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.word.English;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnglishRepository extends CrudRepository<English, Integer> {
    English findFirstByHash(int hash);

    List<English> findAllByStatus(WordProcess status, Pageable page);
}
