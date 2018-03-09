package com.reaier.engking.controller;

import com.reaier.core.controller.result.RestResult;
import com.reaier.engking.constants.Language;
import com.reaier.engking.constants.SourceType;
import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.controller.result.Response;
import com.reaier.engking.controller.result.SourceResult;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.view.SourceWords;
import com.reaier.engking.service.LoginService;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.service.SourceWordsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/source")
public class SourceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SourceService sourceService;

    @Autowired
    SourceWordsService sourceWordsService;

    @Autowired
    LoginService loginService;

    //英译汉文字翻译接口
    @PostMapping("/import.do")
    public Response unifiedOrder(
            @RequestParam(value="token", defaultValue = "test") String token,                          //授权主键
            @RequestParam(value="text") String text,                            //物品名称
            @RequestParam(value="type") String type                             //物品名称
    ) {
        type = "en2cn";
        if (StringUtils.isEmpty(text)) {
            return Response.fail("no text");
        }

        User user;
        if (( user = loginService.findUserByToken(token) ) == null) {
            return Response.noLogin();
        }

        Source source = Source.builder()
                .type(SourceType.TEXT)
                .language(Language.ENGLISH)
                .userId(user.getId())
                .status(WordProcess.WAIT)
                .content(text).build();

        source = sourceService.insert(source);

        SourceResult result = new SourceResult();
        result.getSource().setId(source.getId());
        result.getSource().setTime(source.getTime());
        result.getSource().setStatus(source.getStatus());

        return result;
    }

    //获取导入文章对应的单词列表
    @GetMapping("/list.do")
    public RestResult list(
            @RequestParam(value="token", defaultValue = "test") String token,
            @RequestParam(value="page", defaultValue = "1") Integer page,
            @RequestParam(value="size", defaultValue = "50") Integer size
    ) {
        User user;
        if (( user = loginService.findUserByToken(token) ) == null) {
            return RestResult.noLogin();
        }

        return SourceResult.list(sourceService.findAllByUser(user, page, size));
    }


    //获取导入文章对应的单词列表
    @GetMapping("/words.do")
    public RestResult list(
            @RequestParam(value="token", defaultValue = "test") String token,
            @RequestParam(value="source_id") Integer sourceId,
            @RequestParam(value="page", defaultValue = "1")  Integer page,
            @RequestParam(value="size", defaultValue = "50") Integer size
    ) {
        User user;
        if (( user = loginService.findUserByToken(token) ) == null) {
            return RestResult.noLogin();
        }

        Source source;
        if (( source = sourceService.getId(sourceId) ) == null) {
            return SourceResult.noSource();
        }

        if (source.getUserId() != user.getId()) {
            //来源内容不属于用户自己提供
            return SourceResult.noSource();
        }

        Page<SourceWords> pageable;
        if (( pageable = sourceWordsService.findWordsBySource(source, page, size)) == null ) {
            return SourceResult.noSource();
        }

        for ( SourceWords word : pageable.getContent()) {
            English
        }

        return SourceResult.list();
    }
}

