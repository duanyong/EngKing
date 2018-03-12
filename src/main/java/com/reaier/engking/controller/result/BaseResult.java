package com.reaier.engking.controller.result;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@AllArgsConstructor
public abstract class BaseResult {
    private transient final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected transient static final int      RootSuccessCode     = 0;
    protected transient static final String   RootSuccessMessage  = "success";

    protected transient static final int      RootErrorCode       = 100000;
    protected transient static final String   RootErrorMessage    = "Some things bad";


    public int      code;
    public String   message;
}
