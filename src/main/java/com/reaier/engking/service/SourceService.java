package com.reaier.engking.service;

import com.reaier.engking.domain.Source;

import java.io.File;

public interface SourceService {
    Source insert(Source source);

    Source proccess(Source source);

    boolean proccessUrl(String uri);
    boolean proccessImage(File path);
    boolean proccessText(String text);
}
