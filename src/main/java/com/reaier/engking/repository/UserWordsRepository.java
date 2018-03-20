package com.reaier.engking.repository;

import com.reaier.engking.constants.RecitalPlan;
import com.reaier.engking.domain.UserEnglish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWordsRepository extends CrudRepository<UserEnglish, Integer> {
    UserEnglish findFirstByUserIdAndWordId(Integer userId, Integer wordId);
    Page<UserEnglish> findAllBySourceId(Integer sourceId, Pageable pageable);
    Page<UserEnglish> findAllByUserIdAndSourceIdAndPlan(Integer userId, Integer sourceId, RecitalPlan plan, Pageable pageable);
}
