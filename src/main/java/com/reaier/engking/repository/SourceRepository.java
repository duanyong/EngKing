package com.reaier.engking.repository;

import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface SourceRepository extends CrudRepository<Source, Long> {
    Page<Source> findAll(Pageable pageable);
    Source findByToken(@NotNull String token);
    Page<Source> findAllByType(SourceType type, Pageable pageable);
}
