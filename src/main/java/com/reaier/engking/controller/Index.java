package com.reaier.engking.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class Index {
    private Logger logger = Logger.getLogger(Index.class);

    @RequestMapping("/index")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        logger.info("hello");
        model.addAttribute("name", name);
        return "hello";
    }

}
