package com.reaier.engking.service;

import com.reaier.engking.domain.Source;

import java.io.File;

public interface SourceService {
    Source update(Source source);

    Source process(Source source);

    boolean processUrl(String uri);
    boolean processImage(File path);
    boolean processText(String text);
}
