package com.reaier.engking.sequence.dictionary.ecdict;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ECDictRepository extends CrudRepository<ECDict, Integer> {
    ECDict findByWord(String word);
}
