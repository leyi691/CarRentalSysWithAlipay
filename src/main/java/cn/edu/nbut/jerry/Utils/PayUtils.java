package cn.edu.nbut.jerry.Utils;

import cn.edu.nbut.jerry.config.AlipayConfig;
import cn.edu.nbut.jerry.config.NamesConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PayUtils {

    /**
     * 调用支付宝支付接口
     * @param outTradeNo  商户订单号，商户网站订单系统中唯一订单号，必填   对应缴费记录的orderNo
     * @param totalAmount  付款金额，必填
     * @param orderSubject 主题
     * @param orderDetail 商品描述，可空
     * @return
     */
    public static void alipay(HttpServletResponse response, String outTradeNo, String orderSubject, String totalAmount, String orderDetail, String returnUrl, String notifyUrl) throws IOException {
        /** 支付宝网关（沙箱） **/
        String URL = AlipayConfig.ALIPAY_URL;

        /** 应用id，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/190/201602493024 **/
        String APP_ID = AlipayConfig.APP_ID;

        /** 应用私钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602469554 **/
        String APP_PRIVATE_KEY = AlipayConfig.APP_PRIVATE_KEY;

        /** 支付宝公钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602487431 **/
        String ALIPAY_PUBLIC_KEY = AlipayConfig.ALIPAY_PUBLIC_KEY;

        /** 初始化 **/
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");

        /** 实例化具体API对应的request类，类名称和接口名称对应,当前调用接口名称：alipay.trade.page.pay（电脑网站支付） **/
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();

        /** 设置业务参数  **/
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        /** 商户订单号，商户自定义，需保证在商户端不重复，如：20200612000001 **/
        model.setOutTradeNo(outTradeNo);

        /** 销售产品码，固定值：FAST_INSTANT_TRADE_PAY **/
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        /** 订单标题 **/
        model.setSubject(orderSubject);

        /** 订单金额，精确到小数点后两位 **/
        model.setTotalAmount(totalAmount);

        /** 订单描述 **/
        model.setBody(orderDetail);

        /** 业务扩展参数 **/
        //ExtendParams extendParams = new ExtendParams();

        /** 花呗分期参数传值前提：必须有该接口花呗收款准入条件，且需签约花呗分期 **/
        /** 指定可选期数，只支持3/6/12期，还款期数越长手续费越高 **/
        // extendParams.setHbFqNum("3");

        /** 指定花呗分期手续费承担方式，手续费可以由用户全承担（该值为0），也可以商户全承担（该值为100），但不可以共同承担，即不可取0和100外的其他值。 **/
        // extendParams.setHbFqSellerPercent("0");

        // model.setExtendParams(extendParams);

        /** 将业务参数传至request中 **/
        alipayRequest.setBizModel(model);

        /** 注：支付结果以异步通知为准，不能以同步返回为准，因为如果实际支付成功，但因为外力因素，如断网、断电等导致页面没有跳转，则无法接收到同步通知；**/
        /** 同步通知地址，以http或者https开头，支付完成后跳转的地址，用于用户视觉感知支付已成功，传值外网可以访问的地址，如果同步未跳转可参考该文档进行确认：https://opensupport.alipay.com/support/helpcenter/193/201602474937 **/
        alipayRequest.setReturnUrl(returnUrl);

        /** 异步通知地址，以http或者https开头，商户外网可以post访问的异步地址，用于接收支付宝返回的支付结果，如果未收到该通知可参考该文档进行确认：https://opensupport.alipay.com/support/helpcenter/193/201602475759 **/
        alipayRequest.setNotifyUrl(notifyUrl);

        /** 第三方调用（服务商模式），传值app_auth_token后，会收款至授权app_auth_token对应商家账号，如何获传值app_auth_token请参考文档：https://opensupport.alipay.com/support/helpcenter/79/201602494631 **/
        // request.putOtherTextParam("app_auth_token", "传入获取到的app_auth_token值");

        String form = null;
        try {

            /** 调用SDK生成表单form表单 **/
            form = alipayClient.pageExecute(alipayRequest).getBody();

            /** 调用SDK生成支付链接，可在浏览器打开链接进入支付页面 **/
            //form = alipayClient.pageExecute(alipayRequest,"GET").getBody();

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        /** 获取接口调用结果，如果调用失败，可根据返回错误信息到该文档寻找排查方案：https://opensupport.alipay.com/support/helpcenter/97 **/
//        System.out.println(form);
        response.setContentType("text/html;charset=" + "utf-8");

        /** 直接将完整的表单html输出到页面 **/
        response.getWriter().write(form);
        response.getWriter().flush();
    }

    /**
     * 获取基础支付接口
     * @return
     */
    public static AlipayClient getDefaultAlipayClient(){
        /** 支付宝网关（沙箱） **/
        String ALIPAY_URL = AlipayConfig.ALIPAY_URL;

        /** 应用id，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/190/201602493024 **/
        String APP_ID = AlipayConfig.APP_ID;

        /** 应用私钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602469554 **/
        String APP_PRIVATE_KEY = AlipayConfig.APP_PRIVATE_KEY;

        /** 支付宝公钥，如何获取请参考：https://opensupport.alipay.com/support/helpcenter/207/201602487431 **/
        String ALIPAY_PUBLIC_KEY = AlipayConfig.ALIPAY_PUBLIC_KEY;
        AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");

        return alipayClient;
    }

    /**
     * 支付宝订单状态查询
     * @param out_trade_no  商户订单编号
     * @return  返回 null 就是调用失败 或者返回是以下交易状态中的一种。
     * 交易状态： WAIT_BUYER_PAY      （待支付）、
     *          TRADE_CLOSED        （超时关闭）、
     *          TRADE_SUCCESS       （已支付）、
     *          TRADE_FINISHED      （已完成）
     *
     * @throws AlipayApiException 抛出异常
     */
    public static String getAlipayTradeStatue(String out_trade_no) throws AlipayApiException {
        String status = null;
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":" + out_trade_no + "}");
        AlipayTradeQueryResponse response = getDefaultAlipayClient().execute(request);
        if (response.isSuccess()) {
            // 订单状态查询接口调用成功
            System.out.println("支付宝订单状态查询接口调用成功");
            status = response.getTradeStatus();
            switch (status) {
                case "WAIT_BUYER_PAY":
                    status = NamesConfig.WAIT_BUYER_PAY;
                    break;
                case "TRADE_CLOSED":
                    status = NamesConfig.TRADE_CLOSED;
                    break;
                case "TRADE_SUCCESS":
                    status = NamesConfig.TRADE_SUCCESS;
                    break;
                case "TRADE_FINISHED":
                    status = NamesConfig.TRADE_FINISHED;
                    break;
            }
        } else {
            System.out.println("支付宝订单状态查询接口调用失败: " + response.getSubMsg());
            return NamesConfig.ORDER_NOT_EXISTS;
        }
        return status;
    }

    /**
     * 支付宝退款接口
     * @param outTradeNo
     * @param tradeNo
     * @param refundAmount
     * @param refundReason
     * @param out_request_no  标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return
     */
    public static AlipayTradeRefundResponse aliRefund(String outTradeNo, String tradeNo,String refundAmount,String refundReason,String out_request_no) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setReturnUrl(NamesConfig.RETURN_URL_STORE);
        alipayRequest.setNotifyUrl(NamesConfig.NOTIFY_URL_STORE);
        try {
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                    + "\"trade_no\":\""+ tradeNo +"\","
                    + "\"refund_amount\":\""+ refundAmount +"\","
                    + "\"refund_reason\":\""+ refundReason +"\","
                    + "\"out_request_no\":\""+ out_request_no +"\"}");

            //请求
            String result;

            //请求
            AlipayTradeRefundResponse response = alipayClient.execute(alipayRequest);
            System.out.println("*********************\n返回结果为："+response.getMsg());
            return response;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 支付宝的验签方法
     * @param request
     * @return
     */
    public static boolean checkSign(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //调用SDK验证签名
        try {
            return  AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("*********************验签失败********************");
            return false;
        }
    }
}
