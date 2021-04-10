package cn.edu.nbut.jerry.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    /** 支付宝网关（沙箱） **/
    public static String ALIPAY_URL = "https://openapi.alipaydev.com/gateway.do";

    /** 应用id，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/190/201602493024 **/
    public static String APP_ID = "2021000117615842";

    /** 应用私钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602469554 **/
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1AFov5wSNsy32xIybkd2SpgyQmNekwbhlH4nn2mRVWCEdUtiU6vMsJSyQI8OwfmMBBapyT+fhE/cSoJ56+Wd4ZjuQalNpdZ+ojgduMpu5v+QTaAvUiDrlIWbxKNFDd9PG7KW6PGKpK0D1stjWR5cA5qvC8xPmk7UAl7/Zh1UvZVCyBX9nUPKPZPr7UmuBzTz2EmCdH9/vSRX4/vi4hmmQxg5ha+1IuEkULu1QLNZuPt4vF4eRTrS/SJj8jDVI6/3Id0/1ohi484iS0UdkvCi8otNotrG/p9tKeKdUSgFfN/vWq0coaqRnD2RhDax27vSZK4KREMSrGeZxCzm2tmC7AgMBAAECggEBAKHrgsK/o+8vj7rl8fNp9hGuwibI1IDrXA36vN/RG1jN0jHtXxs5Y/jl+IupsvI83kyXyPciTImp1qmvTpcsbkfjDP4QYDZDp2SeSj9mvrDY3SwPNCYZCLbZvsGWhj4ZADdAlLGaO5iivSYPrLNkr816jcWEtxTapl21Y0cyLh6KvwagDKvYQhxr8TFwiKoLNsDEFN+eT6nzgmeTNV8mSJls2zp1tUQasybnNTNLQeXvBrj6CS5s8jFl6EWNkqUEvHrbysPF8cvJO8yIxZuhqCJsoeTCPdYI1W6JCUT7rg+rBWPcTqai9hdDdo7fxZxFcJNeWUondh3+bvkCDIsINQECgYEA2ygZkYymYvnRj+JBunky8esfHGI5hc7Tec9zyP6dV5mm+OUzd2JcbdBkepN4YlyZKPyj6raAbFmGzHZIZRcKbLIA/4LeIkz7OY1p7zmyu/9LC8FHziG1DCTDU8D3A79qtAZc9p7oBx/pDulF3gfWbPUW25cW41RM1A90+MTTplsCgYEA024nlCqatetXutMNv4BwF3cxSAWCd4DpHIqzSMwTnlvXgVLbWaY5PfVgGM+Z5lgZ8QkolinHK+H1LUD8iI4juLzKTSqlIM3oCDk0nRZgr6tQh7Ro6//edqrBAxm9ItjkyQPXQUbD7yqjavVZ7OCKwRDYtLPdMyTLGArqd97y/SECgYEA0+Bw18ajFg1wDSsxYJbMKhamfUp5UmQjECEAT9HZTFfAleEBYOo3cfOHMPph6LadXILj9od6oUXsjL/OPe2Ipo1xaZWcLI1AVOPjBuVBZ3/pjfPO9/qejp78OHzWU0k8X8/HEKeQq1KufzwH0CDDrZNGBgaMmjtmC57l95xTAwECgYAs2/VX0YZLzOgrQnZrSEQAJpD1N/4LfvV20dLiQjJzagcacEsMBWGlzLcb7Ig5wD2Yz5ml6q1QmTuc42nxSz83Bg9iOgcZI7b0b7bDEaln/FA0yslsMTixMt+K2d4wU5aPM9prBimyR/iuS+pNgQLPndRs2rC3FjplGoPO9SXXIQKBgCW7iUR5ixcPyZvPA07BsDqUd6vCgM11Ss2tcjBHAKMAa5zCTpe0nz/pimC9crewnpp4NW+pVAUTJos2Wl4QaPV0aOzxTrPJWfCXAGlCzd3W39J0zHTKKc0Rq9GZ1b5kVoHBtfSpLDvBy+82WXc4hktYOVvjlzxlJkqzvpjJZQX9";

    /** 支付宝公钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602487431 **/
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoN0PDduPMU7KZz/MzPztxCVF1Arl1clArJyVDbZ9oonBBE5oDGSl0EJE2rdga8s3FWFlvAapKMOQHKURHecV+eKwMnf2CW9lSjidi0nuuHlioYE4Z6Lnoprf3JwSTlIq7zVQuqzig+fycPSavpTgPCjudz4YEwzrmFMowEB8DTqhpL8o8h0dt7f6aU8TU4i9RbWiFhLIDcFYE8olnZpQkZDuOIeH8N7rqOrJAUS3VyI2Zo+By1JVmpq+RwXh9A7TtYXkBj/+tzpFRSDgpox7gLlhSpgYyEQa4Ws3NZAcxelRWF0ceZZ9PeDs6bENumAl2mwoW/t0fvHdUibeLGeUtwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String RETURN_URL = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    /** 支付宝网关（沙箱） **/
    public static String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    // 日志目录
    public static String LOG_PATH = "/Users/charlieli/Desktop/CarRental/log";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
