package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Car;
import cn.edu.nbut.jerry.POJO.CarStore;
import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.Utils.CancelTimoutOrder;
import cn.edu.nbut.jerry.Utils.CommonUtils;
import cn.edu.nbut.jerry.Utils.PayUtils;
import cn.edu.nbut.jerry.config.NamesConfig;
import cn.edu.nbut.jerry.service.CarService;
import cn.edu.nbut.jerry.service.CarStoreService;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.impl.CarServiceImpl;
import cn.edu.nbut.jerry.service.impl.CarStoreServiceImpl;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;
import com.alipay.api.AlipayApiException;
import org.quartz.SchedulerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用来显示商品信息，以及处理按照车辆类型展示车辆
 */
@WebServlet(name = "处理订单", urlPatterns = "/doGoods")
public class GoodsServlet extends HttpServlet {
    private final CarService        carService      = new CarServiceImpl();
    private final CarStoreService   carStoreService = new CarStoreServiceImpl();
    private final OrderService      orderService    = new OrderServiceImpl();
//    TODO 返回地址
    private final String aliPayReturnUrl = NamesConfig.ALIPAY_RETURN_URL;  // 同步跳转url
    private final String aliPayNotifyUrl = "http://localhost:8080/doGoods?op=notifyUrl";     // 异步通知url

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        System.out.println("GoodsServlet");
        if ("list".equals(action)) {
            // 显示相应类型的车辆
            System.out.println("list");
            showCars(request, response);
        } else if ("rentalCar".equals(action)) {
            // 显示选择的租赁车辆
            System.out.println("rentalCar");
            rentalCar(request, response);   // require int carId
        } else if ("doAlipay".equals(action)) {
            // 这里提交支付宝所需信息，并对用户是否支付作出反应
            System.out.println("rentalCar");
            doAlipay(request, response);
        } else if ("returnUrl".equals(action)) {
            // 支付宝支付成功同步跳转Url
            returnUrl(request, response);
        } else if ("notifyUrl".equals(action)) {
            // 支付宝异步通知Url，并用不上

        } else if ("toPay".equals(action)) {
            toPay(request, response);
        }
    }

    protected void showCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String carType = request.getParameter("carType");
        switch (carType) {
            case "SUV":
                carType = "越野车";
                break;
            case "car":
                carType = "小轿车";
                break;
            case "sportCar":
                carType = "跑车";
                break;
            case "superCar":
                carType = "超级跑车";
                break;
        }
        List<Car> cars;
        if (carType.equals("")) {
            // 如果选择全部车辆
            cars = (List<Car>) CommonUtils.getSpecificPage(0, carService.getAllCarRandom(), NamesConfig.ONE_PAGE_SIZE_LONG);
        } else {
            // 否则输出相对应的车辆类型。
            cars = (List<Car>) CommonUtils.getSpecificPage(0, carService.getCarsByCarType(carType), NamesConfig.ONE_PAGE_SIZE_MID);
        }
        request.setAttribute("cars", cars); // 正常传递数据
        request.getRequestDispatcher("/jsps/car/rentalCarIndex.jsp").forward(request, response);
//        TODO NEXT 把值显示在jsp中
    }

    protected void rentalCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 展示用户选择的车辆
        int carId = Integer.parseInt(request.getParameter("carId"));
        // 需要门店信息：门店地址，所在区域，门店名等
        Car car = carService.getCarById(carId);
        CarStore carStore = carStoreService.getCarStoreAccountById(car.getStoreId());
        request.setAttribute("car",         car);
        request.setAttribute("carStore",    carStore);
        request.getRequestDispatcher("/jsps/car/rentalCarMain.jsp").forward(request, response);
    }

    protected void doAlipay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 这里提交支付宝所需信息，并对用户是否支付作出反应
        // 提交支付宝所需信息：
        String      outTradeNo       =       request.getParameter                        ("outTradeNo");          // 商户唯一订单编号
        String      orderSubject     =       request.getParameter                        ("orderSubject");        // 订单标题
        String      orderDetail      =       request.getParameter                        ("orderDetail");         // 订单描述
        Date        getCarDate       =       java.sql.Date.valueOf(request.getParameter  ("inputGetCarDate"));    // 取车日期
        Date        returnCarDate    =       java.sql.Date.valueOf(request.getParameter  ("inputReturnCarDate")); // 还车日期
        String      totalAmount      =       request.getParameter                        ("totalAmount");         // 总金额
        int         getCarStoreId    =       Integer.parseInt(request.getParameter       ("getCarStoreId"));      // 取车门店Id
        int         returnCarStoreId =       Integer.parseInt(request.getParameter       ("returnCarStoreId"));   // 还车门店Id
        int         carId            =       Integer.parseInt(request.getParameter       ("carId"));              // 租赁的汽车Id
        int         userId           =       (int) request.getSession().getAttribute     ("userId");

        Order order = new Order();
        order.setUserId(userId);
        System.out.println("GoodsServlet: userId = " + userId);
        order.setOutTradeNo(outTradeNo);
        order.setOrderSubject(orderSubject);
        order.setOrderInfo(orderDetail);
        order.setRentalDate(getCarDate);
        order.setReturnDate(returnCarDate);
        order.setTotalAmount(totalAmount);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(date);
        order.setOrderDate(today);        // 获取当前的时间
        order.setOrderStatus(NamesConfig.WAIT_BUYER_PAY);
        order.setStoreId(getCarStoreId);
        order.setReturnCarStoreId(returnCarStoreId);
        order.setCarId(carId);
        System.out.println("GoodServlet: order = " + order);

        /**
         * 设置订单轮询 5 分钟查询一次订单状态，直到30分钟自动结束
         */
        try {
            if (request.getSession().getAttribute("isCancelTimeoutOrderOn") == null) {
                CancelTimoutOrder.roundQuery(NamesConfig.ORDER_TIME_LIMIT_TEST, NamesConfig.ORDER_POLLING_COUNT_TEST);
                request.getSession().setAttribute("isCancelTimeoutOrderOn", true);
                System.out.println("轮询设置成功，轮询总时长为" + NamesConfig.ORDER_TIME_LIMIT_TEST * NamesConfig.ORDER_POLLING_COUNT_TEST
                        + "\n每" + NamesConfig.ORDER_TIME_LIMIT_TEST + "秒询问一次，共"
                        + NamesConfig.ORDER_TIME_LIMIT_TEST + "次");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        Car car = carService.getCarById(carId);
        // 设置车辆"已租出"
        car.setCarStatus(NamesConfig.CAR_AT_SERVICE);
        int carResult = carService.updateCarStatusById(car);
        int result = orderService.addOrder(order);

        if (result > 0 && carResult > 0) {
            PayUtils.alipay(response, outTradeNo, orderSubject, totalAmount, orderDetail, aliPayReturnUrl, aliPayNotifyUrl);
        } else {
            System.out.println("lijiayu.GoodsServlet: 新增订单失败");
        }
    }

    /**
     * 用户从订单页面发起的支付，只需要再调用起支付接口就行
     * @param request
     */
    protected void toPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取 orderId
        System.out.println("toPay");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.getOrderById(orderId);
        String tradeStatue = order.getOrderStatus();
        System.out.println("toPay: out_trade_no = " + order.getOutTradeNo());
        try {
            System.out.println("toPay: tryQueryStatus");
            tradeStatue = PayUtils.getAlipayTradeStatue(order.getOutTradeNo());
            System.out.println("toPay: tradeStatue = " + tradeStatue);
            if (tradeStatue != null) {
                if (tradeStatue.equals(NamesConfig.TRADE_SUCCESS)) {
                    order.setOrderStatus(tradeStatue);
                    orderService.updateOrderStatusByOutTradeNo(order);
                    response.sendRedirect("/jsps/user/userOrder.jsp");
                } else if (tradeStatue.equals(NamesConfig.ORDER_NOT_EXISTS)) {
                    // 订单创建不成功，重新调起支付接口进行支付。
                    PayUtils.alipay(response, order.getOutTradeNo(), order.getOrderSubject(), order.getTotalAmount(), order.getOrderInfo(), aliPayReturnUrl, aliPayNotifyUrl);
                } else if (tradeStatue.equals(NamesConfig.WAIT_BUYER_PAY)) {
                    // 订单未支付，重新调起支付接口进行支付。
                    PayUtils.alipay(response, order.getOutTradeNo(), order.getOrderSubject(), order.getTotalAmount(), order.getOrderInfo(), aliPayReturnUrl, aliPayNotifyUrl);
                }
            }
        } catch (AlipayApiException e) {
            System.out.println("支付宝接口查询失败！");
            e.printStackTrace();
            response.sendRedirect("/jsps/user/userOrder.jsp?err=alipay");
        }
    }

    protected void returnUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转到个人页面的订单页面，展示订单，如果订单已经支付成功，修改订单状态。
        String outTradeNo = request.getParameter("out_trade_no");
        System.out.println("returnURLServlet out_trade_no = " + outTradeNo);
        String tradeStatue = null;
        try {
            tradeStatue = PayUtils.getAlipayTradeStatue(outTradeNo);
            System.out.println();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        Order order = orderService.getOrderByOutTradeNoForUser(outTradeNo);
        if (tradeStatue != null) {
            System.out.println("AlipayReturn tradeStatue = " + tradeStatue);
//            if (tradeStatue.equals(NamesConfig.WAIT_BUYER_PAY)) {
//                // 订单未支付，跳转过去提示用户还未支付
//            }
            order.setOrderStatus(tradeStatue);
            orderService.updateOrderStatusByOutTradeNo(order);
            System.out.println("订单状态修改成功！= " + tradeStatue);
        }
        request.setAttribute("order", order);
        System.out.println("支付宝返回后 order = " + order);
        request.getRequestDispatcher("/jsps/user/userOrder.jsp").forward(request, response);
    }


}