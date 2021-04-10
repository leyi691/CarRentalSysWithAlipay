package cn.edu.nbut.jerry.servlet.testAlipay;

import cn.edu.nbut.jerry.Utils.PayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "测试创建Alipay订单", urlPatterns = "/testAlipayOrder")
public class testCreateAlipayOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        PayUtils.alipay(response,
                "12345678903", "iPhone 12", "5799.00", "",
                "http://localhost:8080/testReturn", "http://localhost:8080/testNotify");
    }
}
