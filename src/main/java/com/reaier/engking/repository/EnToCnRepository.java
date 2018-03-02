package com.reaier.engking.repository;

import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnToCnRepository extends CrudRepository<EnToCn, Integer> {

}
