package com.reaier.engking.action;

import com.reaier.engking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/source")
public class SourceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService paySerivce;

}

