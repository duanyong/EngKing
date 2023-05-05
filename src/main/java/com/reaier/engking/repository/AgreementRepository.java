package com.reaier.engking.repository;

import com.reaier.engking.domain.Agreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends CrudRepository<Agreement, Integer> {
    Agreement findByVersion(String version);

    Page<Agreement> findAll(Pageable pageable);
}
