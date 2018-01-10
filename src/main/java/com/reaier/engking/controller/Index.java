package com.reaier.engking.controller;

import com.reaier.engking.bean.User;
import com.reaier.engking.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Index {
    private Logger logger = Logger.getLogger(Index.class);

    @Autowired
    UserServiceImpl service;

    @Autowired
    User user;

    @RequestMapping("/index")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        logger.info("hello");
        model.addAttribute("name", name);

        user.setUsername(name);



        return "hello";
    }

}
