package com.reaier.engking.repository;

import com.reaier.engking.domain.UserAgreement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAgreementRepository extends CrudRepository<UserAgreement, Integer> {
    UserAgreement findByAgreementIdAndUserId(Integer agreementId, Integer userId);
}
