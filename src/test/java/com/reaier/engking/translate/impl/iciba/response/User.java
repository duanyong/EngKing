package com.reaier.engking.translate.impl.iciba.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private Integer age;
    private Date birthday;
    private String email;
}
