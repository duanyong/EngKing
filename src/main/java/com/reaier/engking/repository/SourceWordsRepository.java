package com.reaier.engking.repository;

import com.reaier.engking.domain.view.SourceWords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SourceWordsRepository extends CrudRepository<SourceWords, Integer> {
    Page<SourceWords> findAllBySourceId(Integer sourceId, Pageable pageable);
}
