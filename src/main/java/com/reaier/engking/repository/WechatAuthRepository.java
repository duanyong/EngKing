package com.reaier.engking.repository;

import com.reaier.engking.domain.dictionary.English;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WechatAuthRepository extends CrudRepository<English, Integer> {
    Page<English> findAll(Pageable pageable);
}
