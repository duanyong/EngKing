package com.reaier.engking.action;

import com.reaier.core.controller.result.RestResult;
import com.reaier.engking.service.UserService;
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
@RequestMapping("/wechat")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService paySerivce;

    //统一下单接口
    @PostMapping("/unified.do")
    public RestResult unifiedOrder(
            @RequestParam(value="openid") String openid,                        //微信主键
            @RequestParam(value="name", required = false) String name,          //物品名称
            @RequestParam(value="package", required = false) String packname,   //支付目录
            @RequestParam(value="num") int num,                                 //物件数量
            @RequestParam(value="price") int price                              //精确到分
    ) {
        if (num < 1) {
            return RestResult.fail("您至少要发一个红包");
        }

        if (price < 100) {
            return RestResult.fail("您的红包要多于一块钱");
        } else if (price > 500000) {
            return RestResult.fail("为了保证您的资金安全，红包金额请小于五千块");
        }

        price = 1;

        if (name == null) {
            name = "新年红包";
        }

        if (packname == null) {
            packname = "冒菜-新年红包";
        }

        Calendar stopPay =Calendar.getInstance();
        stopPay.setTime(new Date());
        stopPay.add(Calendar.HOUR, 2);

        WechatUnifiedOrder unifiedOrder = paySerivce.generateWechatUnifiedOrderFromWeb(name, 1, price, new Date(), stopPay.getTime(), packname, openid);

        if (unifiedOrder == null) {
            return RestResult.fail("no unifiedOrder");
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



    @PostMapping("/query.do")
    public RestResult payback(@RequestParam(value="openid") String openid,
                              @RequestParam(value="trade_no") String tradeNo) {
        WechatUnifiedOrder unifiedOrder = paySerivce.getWechatPayService().getOrderByOpenIdAndTradeNo(openid, tradeNo);
        if (unifiedOrder == null) {
            return RestResult.fail("no trade");
        }

        unifiedOrder = paySerivce.getWechatPayService().query(tradeNo);
        if (unifiedOrder == null) {
            return RestResult.fail("query error");
        }


        QueryOrderResult result = new QueryOrderResult();
        result.getUnifiedOrder().setResultMessage(unifiedOrder.getTradeStateDesc());
        result.getUnifiedOrder().setResultCode(unifiedOrder.getResultCode());
        result.getUnifiedOrder().setPaidOpenid(unifiedOrder.getOpenid());
        result.getUnifiedOrder().setFeeTotal(unifiedOrder.getTotalFee());
        result.getUnifiedOrder().setPaidTime(unifiedOrder.getTimeEnd());
        result.getUnifiedOrder().setTradeNo(unifiedOrder.getTradeNo());

        return result;
    }
}

