package com.reaier.engking.service;

import com.reaier.engking.domain.Source;

import java.io.File;

public interface SourceService {
    Source insert(Source source);

    Source proccess(Source source);

    void proccessUrl(Source source);
    void proccessImage(Source source);
    void proccessText(Source source);
}
