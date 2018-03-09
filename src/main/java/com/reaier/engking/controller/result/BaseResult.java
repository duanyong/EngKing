package com.reaier.engking.controller.result;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public abstract class BaseResult {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final int      RootSuccessCode     = 0;
    protected static final String   RootSuccessMessage  = "success";

    protected static final int      RootErrorCode       = 100000;
    protected static final String   RootErrorMessage    = "Some things bad";

    public int      code;
    public String   message;
}
