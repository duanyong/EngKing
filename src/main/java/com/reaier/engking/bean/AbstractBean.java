package com.reaier.engking.bean;

import java.io.Serializable;

/**
 * Created by PP on 29/11/2017.
 */
public abstract class AbstractBean implements Serializable {

    public abstract Object getId();

    public abstract void setId(Object id);
}
