package com.reaier.engking.action;

import com.reaier.core.controller.result.RestResult;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping("/en2cn")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SourceService sourceService;

    @Autowired
    UserService userService;

    //统一下单接口
    @PostMapping("/text.do")
    public RestResult unifiedOrder(
            @RequestParam(value="user_id") String userId,                       //微信主键
            @RequestParam(value="text") String text                             //物品名称
    ) {
        if (StringUtils.isBlank(text)) {
            return RestResult.fail("no text");
        }




         return new UnifiedOrderResult(
            unifiedOrder.getRequestAppid(),
            unifiedOrder.getPrepayId(),
            paySerivce.getWechatPayService().getService().getConfig().getMchKey(),
            unifiedOrder.getRequestTotalFee(),
            unifiedOrder.getTradeNo(),
            WxPayConstants.SignType.HMAC_SHA256
        );
    }

}

