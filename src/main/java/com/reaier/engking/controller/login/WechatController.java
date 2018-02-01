package com.reaier.engking.controller.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/wechat")
public class WechatController {
    
    @GetMapping("/qrcode.do")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
}
