package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Car;
import cn.edu.nbut.jerry.Utils.CommonUtils;
import cn.edu.nbut.jerry.config.NamesConfig;
import cn.edu.nbut.jerry.service.CarService;
import cn.edu.nbut.jerry.service.CarStoreService;
import cn.edu.nbut.jerry.service.impl.CarServiceImpl;
import cn.edu.nbut.jerry.service.impl.CarStoreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RentalCarListServlet", urlPatterns = "/doCarRentalList")
public class RentalCarListServlet extends HttpServlet {
    private final CarService carService = new CarServiceImpl();
    private final CarStoreService carStoreService = new CarStoreServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        if ("allType".equals(action)) {
            System.out.println("/doCarRentalList?op=allType");
            showAllTypeCar(request, response);
        } else if ("SUV".equals(action)) {
            System.out.println("/doCarRentalList?op=SUV");
            showSUVTypeCar(request, response);
        } else if ("car".equals(action)) {
            System.out.println("/doCarRentalList?op=car");
            showCarTypeCar(request, response);
        } else if ("sportCar".equals(action)) {
            System.out.println("/doCarRentalList?op=car");
            showSportCarTypeCar(request, response);
        } else if ("superCar".equals(action)) {
            System.out.println("/doCarRentalList?op=car");
            showSuperCarTypeCar(request, response);
        }
    }

    protected void showAllTypeCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = carService.getAllCarAvailableRand();
        cars = (List<Car>) CommonUtils.getSpecificPage(0, cars, NamesConfig.ONE_PAGE_SIZE_MID);
        request.setAttribute("carType", "所有车辆");
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsps/car/rentalCarIndex.jsp").forward(request, response);
    }
    protected void showSUVTypeCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = carService.getCarsByCarType("越野车");
        cars = (List<Car>) CommonUtils.getSpecificPage(0, cars, NamesConfig.ONE_PAGE_SIZE_MID);
        request.setAttribute("carType", "越野车");
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsps/car/rentalCarIndex.jsp").forward(request, response);
    }
    protected void showCarTypeCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = carService.getCarsByCarType("小轿车");
        cars = (List<Car>) CommonUtils.getSpecificPage(0, cars, NamesConfig.ONE_PAGE_SIZE_MID);
        request.setAttribute("carType", "小轿车");
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsps/car/rentalCarIndex.jsp").forward(request, response);
    }
    protected void showSportCarTypeCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = carService.getCarsByCarType("跑车");
        cars = (List<Car>) CommonUtils.getSpecificPage(0, cars, NamesConfig.ONE_PAGE_SIZE_MID);
        request.setAttribute("carType", "跑车");
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsps/car/rentalCarIndex.jsp").forward(request, response);
    }
    protected void showSuperCarTypeCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> cars = carService.getCarsByCarType("超级跑车");
        cars = (List<Car>) CommonUtils.getSpecificPage(0, cars, NamesConfig.ONE_PAGE_SIZE_MID);
        request.setAttribute("carType", "超级跑车");
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/jsps/car/rentalCarIndex.jsp").forward(request, response);
    }
}
