package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.config.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test_1")
public class testServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        alipay(response, "22620210311192401", "黄宇扬本体", "888", "黄宇扬本体", "http://localhost:8080/jsps/AlipayTEST_notify_atonce.jsp", "http://localhost:8080/jsps/AlipayTEST_return_later.jsp");
    }

    private void alipay(HttpServletResponse response, String outTradeNo, String orderSubject,String totalAmount, String orderDetail, String returnUrl, String notifyUrl) throws IOException {
        /** 支付宝网关（沙箱） **/
        String URL = "https://openapi.alipaydev.com/gateway.do";

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
            AlipayTradePagePayResponse alipayTradePagePayResponse = alipayClient.pageExecute(alipayRequest);
            /** 调用SDK生成支付链接，可在浏览器打开链接进入支付页面 **/
            //form = alipayClient.pageExecute(alipayRequest,"GET").getBody();
            System.out.println("调用结果为：" + alipayTradePagePayResponse.getTradeNo());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        /** 获取接口调用结果，如果调用失败，可根据返回错误信息到该文档寻找排查方案：https://opensupport.alipay.com/support/helpcenter/97 **/
        System.out.println(form);
        response.setContentType("text/html;charset=" + "utf-8");

        /** 直接将完整的表单html输出到页面 **/
        response.getWriter().write(form);
        response.getWriter().flush();
    }
}
