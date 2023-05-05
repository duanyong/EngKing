package com.reaier.engking.service.impl;

import com.reaier.engking.domain.Agreement;
import com.reaier.engking.repository.AgreementRepository;
import com.reaier.engking.service.AgreementService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AgreementServiceImpl implements AgreementService {
    @Resource
    AgreementRepository repository;

    @Override
    public Agreement findByLast() {
        Page<Agreement> agreements = findAll(1, 1);

        return agreements.isEmpty() ? null : agreements.getContent().get(0);
    }

    @Override
    public Page<Agreement> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page -1, size, Sort.by(Sort.Direction.DESC, "id")));
    }
}
