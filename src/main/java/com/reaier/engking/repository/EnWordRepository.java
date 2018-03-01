package com.reaier.engking.repository;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.EnWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnWordRepository extends CrudRepository<EnWord, Integer> {
    Page<EnWord> findAll(Pageable pageable);
    EnWord findFirstByHash(int hash);

    @Query("select * from `en_word` where `status`=?1 order by `id` desc limit ?2, ?3")
    List<EnWord> getTopByStatus(WordProcess status, int index, int size);
}
