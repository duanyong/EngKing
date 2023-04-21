package com.reaier.engking.repository;

import com.reaier.engking.domain.SourceWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceWordRepository extends CrudRepository<SourceWord, Integer> {
    SourceWord findBySourceIdAndWordId(Long sourceId, Integer Integer);
}
