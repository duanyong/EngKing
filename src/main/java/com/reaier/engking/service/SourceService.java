package com.reaier.engking.service;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;

import java.io.File;

public interface SourceService {
    Source insert(Source source);
    Source update(Source source);

    Source proccess(Source source);

    Source getOneByStatus(WordProcess status);

    void proccessUrl(Source source);
    void proccessImage(Source source);
    void proccessText(Source source);
}
