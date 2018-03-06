package com.reaier.engking.repository;

import com.reaier.engking.domain.UserWord;
import com.reaier.engking.domain.word.English;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWordsRepository extends CrudRepository<UserWord, Integer> {
    UserWord findFirstByUserIdAndWordId(Integer userId, Integer wordId);
    Page<English> findAllBySourceId(Integer sourceId, Pageable pageable);
}
