package com.reaier.engking.controller;

import com.reaier.core.controller.result.RestResult;
import com.reaier.engking.constants.Language;
import com.reaier.engking.controller.result.SourceResult;
import com.reaier.engking.domain.Login;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.LoginService;
import com.reaier.engking.service.SourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/en2cn")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SourceService sourceService;

    @Autowired
    LoginService loginService;

    //英译汉文字翻译接口
    @PostMapping("/text.do")
    public RestResult unifiedOrder(
            @RequestParam(value="token") String token,                          //授权主键
            @RequestParam(value="text") String text                             //物品名称
    ) {
        if (StringUtils.isEmpty(text)) {
            return RestResult.fail("no text");
        }

        Login login = loginService.findByToken(token);

        if (login == null) {
            return RestResult.fail("no user");
        }

        if (loginService.isExpireTime(login)) {
            loginService.refreshToken(login);
        }

        Source source = Source.builder()
                .language(Language.ENGLISH)
                .userId(login.getUserId())
                .content(text).build();

        sourceService.insert(source);


        SourceResult result = new SourceResult();
        result.getSource().setId(source.getId());
        result.getSource().setTime(source.getCreateAt());

        return result;
    }

}

