package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Car;
import cn.edu.nbut.jerry.service.CarService;
import cn.edu.nbut.jerry.service.impl.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/doIndex", name = "主页控制")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        System.out.println("debug");
        if ("list".equals(action)) {
            System.out.println("list");
            showList(request, response);
        }
    }

    protected void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarService carService = new CarServiceImpl();
        List<Car> cars = carService.getAllCarAvailableRand();
        System.out.println(cars);
//        System.out.println(cars);
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}