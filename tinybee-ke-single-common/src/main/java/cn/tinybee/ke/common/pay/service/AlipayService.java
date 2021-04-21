package cn.tinybee.ke.common.pay.service;

import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.pay.domain.AlipayConfig;
import cn.tinybee.ke.common.pay.domain.UniformOrder;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/9 14:04
 */
@Slf4j
@Component
public class AlipayService {

    @Autowired
    private AlipayConfig alipayConfig;

    private  Config config;

    @PostConstruct
    private void initOptions() {
        config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";
        config.appId = alipayConfig.getAppId();//"<-- 请填写您的AppId，例如：2019091767145019 -->";
        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = alipayConfig.getMerchantPrivateKey();//"<-- 请填写您的应用私钥，例如：MIIEvQIBADANB ... ... -->";
        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
//        config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
//        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
//        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";
        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = alipayConfig.getAlipayPublicKey(); //"<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->";
        //可设置异步通知接收服务地址（可选）
//        config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";
        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
//        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";
        Factory.setOptions(config);
    }

    /**
     *
     * @param uniformOrder
     * @return
     */
    public String uniformOrderForApp (UniformOrder uniformOrder) {
        AlipayTradeAppPayResponse pay = null;
        try {
            pay = Factory.Payment.App().pay(uniformOrder.getSubject(), uniformOrder.getOutTradeNo(), uniformOrder.getTotalAmount());
        } catch (Exception e) {
            throw new BusinessException("发起支付失败");
        }
        return pay.body;
    }

    /**
     *
     * @param uniformOrder
     * @return
     */
    public String uniformOrderForPage (UniformOrder uniformOrder) {
        AlipayTradePagePayResponse pay = null;
        try {
            pay = Factory.Payment.Page().pay(uniformOrder.getSubject(), uniformOrder.getOutTradeNo(), uniformOrder.getTotalAmount(), uniformOrder.getReturnUrl());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("支付宝支付失败: {}", e.getMessage());
            throw new BusinessException("发起支付失败");
        }
        log.info("页面统一支付下单: {}", pay);
        return pay.body;
    }

    /**
     *
     * @param uniformOrder
     * @return
     */
    public String uniformOrderForWap (UniformOrder uniformOrder) {
        AlipayTradeWapPayResponse pay = null;
        try {
            pay = Factory.Payment.Wap().pay(uniformOrder.getSubject(), uniformOrder.getOutTradeNo(), uniformOrder.getTotalAmount(), uniformOrder.getQuitUrl(), uniformOrder.getReturnUrl());
        } catch (Exception e) {
            throw new BusinessException("发起支付失败");
        }
        return pay.getBody();
    }

}
