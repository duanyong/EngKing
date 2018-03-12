package com.reaier.engking.repository;

import com.reaier.engking.domain.dictionary.EnglishToChinese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnToCnRepository extends CrudRepository<EnglishToChinese, Integer> {
    List<EnglishToChinese> findAllByEnglishId(Integer englishId);
}
