package com.reaier.engking.action;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @RequestMapping("/alive")
    public String index() {
        return "ok";
    }
}
