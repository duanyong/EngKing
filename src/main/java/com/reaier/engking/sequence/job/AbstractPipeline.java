package com.reaier.engking.sequence.job;

import com.reaier.engking.domain.Source;

public abstract class AbstractPipeline {
    abstract void pipeline(Source source) throws RuntimeException;
}
