package com.reaier.engking.repository;

import com.reaier.engking.domain.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {
    Login findByToken(String token);
}
