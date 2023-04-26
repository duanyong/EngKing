package com.reaier.engking.repository;

import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SourceRepository extends CrudRepository<Source, Integer> {
    Page<Source> findAll(Pageable pageable);
    Source findByToken(@NotNull String token);
    Page<Source> findAllByType(SourceType type, Pageable pageable);
}
