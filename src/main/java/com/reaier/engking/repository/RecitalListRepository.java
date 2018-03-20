package com.reaier.engking.repository;

import com.reaier.engking.domain.recital.RecitalList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecitalListRepository extends CrudRepository<RecitalList, Integer> {
    Page<RecitalList> findAllByUserId(Integer sourceId, Pageable pageable);
}
