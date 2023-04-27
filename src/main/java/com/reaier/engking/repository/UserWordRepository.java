package com.reaier.engking.repository;

import com.reaier.engking.domain.UserWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWordRepository extends CrudRepository<UserWord, Integer> {
    UserWord findBySourceIdAndWordId(Integer sourceId, Integer wordId);
}
