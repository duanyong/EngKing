package com.reaier.engking.action.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class WebsiteController {
    
    @GetMapping("/qrcode.do")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
}
