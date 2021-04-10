package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/doOrder")
public class OrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderServiceImpl();
    private HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        System.out.println("CarStoreServlet");
        if ("list".equals(action)) {
            // 显示用户所有的订单
//            showOrderList(request, response);
        }
    }

    private void showOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 显示 "当前登录用户" 所有的订单
        session = request.getSession();
        int userId = (int) session.getAttribute("userid");
        List<Order> orders = orderService.getAllOrderByUserId(userId);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/jsps/user/userOrder.jsp").forward(request, response);
    }
}
