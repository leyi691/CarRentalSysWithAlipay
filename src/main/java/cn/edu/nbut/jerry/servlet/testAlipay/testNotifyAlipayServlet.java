package cn.edu.nbut.jerry.servlet.testAlipay;

import cn.edu.nbut.jerry.Utils.PayUtils;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;
import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "测试支付宝异步通知", urlPatterns = "/testNotify")
public class testNotifyAlipayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 支付宝异步通知只能连接到公网ip，而本人并没有，所以这里采用自我查询的方式查询交易是否成功。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
            OrderService orderService = new OrderServiceImpl();
            String out_trade_no = request.getParameter("out_trade_no");
            out_trade_no = "12345678902";
            System.out.println("test: out_trade_no = " + out_trade_no);

            // 使用支付宝接口查询订单状态
            String tradeStatus = null;
            AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
            alipayRequest.setBizContent("{\"out_trade_no\":" + out_trade_no + "}");
            try {
                AlipayTradeQueryResponse alipayResponse = PayUtils.getDefaultAlipayClient().execute(alipayRequest);
                if(alipayResponse.isSuccess()){
                    System.out.println("调用成功");
                    tradeStatus = alipayResponse.getTradeStatus();
                    System.out.println("test: tradeStatus: " + tradeStatus);
                } else {
                    System.out.println("调用失败");
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
//            Order order = orderService.getOrderByOutTradeNo(out_trade_no);
//            order.setOrderStatus("已支付");
    }
}
