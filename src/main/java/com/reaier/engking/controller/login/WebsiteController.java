package com.reaier.engking.controller.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class WebsiteController {
    
    @GetMapping("/qrcode.do")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
}
