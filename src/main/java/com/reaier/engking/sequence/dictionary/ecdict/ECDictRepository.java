package com.reaier.engking.sequence.dictionary.ecdict;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ECDictRepository extends CrudRepository<ECDict, Integer> {
    ECDict findByWord(String word);

    Page<ECDict> findAllBy(Pageable pageable);
}
