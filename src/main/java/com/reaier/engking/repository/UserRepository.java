package com.reaier.engking.repository;

import com.reaier.engking.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(Integer id);
}
