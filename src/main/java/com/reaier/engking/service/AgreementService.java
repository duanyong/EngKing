package com.reaier.engking.service;

import com.reaier.engking.domain.Agreement;
import org.springframework.data.domain.Page;

public interface AgreementService {
    Agreement findByLast();

    Page<Agreement> findAll(int page, int size);
}
