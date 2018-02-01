package com.reaier.engking.domain;

import com.reaier.core.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Entity
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    ////////////////////////////////////////////////////////////////////////////////
    //请求部分
    //

    //小程序ID
    @Column(name = "username", nullable = false, updatable = false, length = 32)
    String username;

    //商户号
    @Column(name = "password", nullable = false, updatable = false, length = 32)
    String password;

    //设备号
    @Column(name = "device_info", nullable = false, updatable = false, length = 32)
    String deviceInfo;

    //随机字符串（存储时并不一定有。逻辑上）
    @Column(name = "nonce_str", updatable = false, length = 32)
    String nonceStr;

    //签名
    @Column(name = "sign", updatable = false, length = 64)
    String sign;

    //签名类型
    @Column(name = "sign_type", updatable = false, length = 32)
    String signType;

    //返回状态码
    @Column(name = "return_code", nullable = false, updatable = false, length = 16)
    String returnCode;

    //返回信息
    @Column(name = "return_msg", updatable = false, length = 128)
    String returnMsg;

    //错误代码
    @Column(name = "err_code", updatable = false, length = 32)
    String errCode;

    //错误代码描述
    @Column(name = "err_code_des", updatable = false, length = 128)
    String errCodeDes;

    //是否关注公众账号
    @Column(name = "is_subscribe", updatable = false)
    Boolean isSubscribe;

    //交易类型
    @Column(name = "trade_type", updatable = false, length = 16)
    String tradeType;

    //付款银行
    @Column(name = "bank_type", updatable = false, length = 32)
    String bankType;

    //结算金额
    @Column(name = "settlement_total_fee", updatable = false)
    Integer settlementTotalFee;

    //标价币种
    @Column(name = "fee_type", updatable = false, length = 8)
    String feeType;

    //现金支付金额
    @Column(name = "cash_fee", updatable = false)
    Integer cashFee;

    //现金支付币种
    @Column(name = "cash_fee_type", updatable = false, length = 16)
    String cashFeeType;

    //代金券金额
    @Column(name = "coupon_fee", updatable = false)
    Integer couponFee;

    //代金券使用数量
    @Column(name = "coupon_count", updatable = false)
    Integer couponCount;

    //微信支付订单号
    @Column(name = "transaction_id", nullable = false, updatable = false, length = 32)
    String transactionId;

    //商户订单号
    @Column(name = "out_trade_no", nullable = false, updatable = false, length = 32)
    String outTradeNo;

    //支付完成时间
    @Column(name = "time_end", updatable = false, length = 14)
    String timeEnd;

    //商品详情
    @Column(name = "attach", nullable = false, updatable = false, length = 127)
    String attach;



    //
    //请求部分
    ////////////////////////////////////////////////////////////////////////////////


    protected User(String openid, Boolean isUpdate) {
        super(openid, isUpdate);
    }

}
