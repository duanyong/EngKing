package com.reaier.engking.repository;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends CrudRepository<Source, Integer> {
    Source findFirstByStatusOrderById(WordProcess status);
    Source findFirstById(Integer id);


    Page<Source> findAllByUserId(Integer userId, Pageable pageable);
}
