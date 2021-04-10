package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Admin;
import cn.edu.nbut.jerry.POJO.CarStore;
import cn.edu.nbut.jerry.POJO.User;
import cn.edu.nbut.jerry.service.AdminService;
import cn.edu.nbut.jerry.service.CarStoreService;
import cn.edu.nbut.jerry.service.UserService;
import cn.edu.nbut.jerry.service.impl.AdminServiceImpl;
import cn.edu.nbut.jerry.service.impl.CarStoreServiceImpl;
import cn.edu.nbut.jerry.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/sign/doSign")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        System.out.println("debug");
        if ("doSignIn".equals(action)) {
            // 登录操作
            System.out.println("doSignIn");
            doSignIn(request, response);
        } else if ("doSignUp".equals(action)) {
            // 注册操作
            System.out.println("doSignUp");
            doSignUp(request, response);
        }
    }

    protected void doSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String verificationCode = request.getParameter("verificationCode");
        String sessionVerificationCode = (String) request.getSession().getAttribute("CHECKCODE");
        System.out.println("这是session里的验证码" + sessionVerificationCode);
//        暂时不要验证码
        if (verificationCode.equals(sessionVerificationCode)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 小写的mm表示的是分钟
            System.out.println("验证码正确");


            String username      =       request.getParameter("inputUsername");
            String password      =       request.getParameter("inputPassword");
            String nickname      =       request.getParameter("inputNickname");
            String sex           =       request.getParameter("inputSex");
            Date registerDate    =       Date.valueOf(request.getParameter("inputDateToday"));
            String phoneNumber   =       request.getParameter("inputPhoneNumber");
            String idType        =       request.getParameter("inputIdType");
            String idNumber      =       request.getParameter("inputIdNumber");
            String province      =       request.getParameter("Province");
            String city          =       request.getParameter("City");
            String area          =       request.getParameter("Area");
            String address       =       request.getParameter("inputAddress");

            User user = new User();
            user.setUsername        (username);
            user.setPassword        (password);
            user.setNickname        (nickname);
            user.setSex             (sex);
            user.setRegisterDate    (registerDate);
            user.setPhoneNumber     (phoneNumber);
            user.setIdType          (idType);
            user.setIdNumber        (idNumber);
            user.setProvince        (province);
            user.setCity            (city);
            user.setArea            (area);
            user.setAddress         (address);
            UserService userService = new UserServiceImpl();
            int result = userService.insertUser(user);

            if (result > 0) {
                // 注册成功，进入个人信息页面
                int userId = userService.getUserByName(username).getUserId();
                session.setAttribute("userId", userId);
                session.setAttribute("isAlreadyLoginUser", userId);
                request.getRequestDispatcher("/doUser?op=user").forward(request, response);
            } else if (result == 0) {
                // 注册失败。
                response.getWriter().print("" +
                        "<script>" +
                        "alert('注册失败');" +
                        "history.go(-1);" +
                        "</script>");
            } else if (result == -1) {
                // 注册失败，用户名重复。
                response.getWriter().print("" +
                        "<script>" +
                        "alert('注册失败，用户名已存在');" +
                        "history.go(-1);" +
                        "</script>");
            }            //        用户名不能含有中文字符
//            if (JudgeHelper.isChinese(username) || user.getUsername().contains(" ")) {
//                response.getWriter().print("<script>" +
//                        "alert('用户名中不允许有空格、中文。');" +
//                        "history.go(-1);" +
//                        "</script>");
//            }
//            //        密码长度为8~20个字符;不能有中文、空格
//            if (user.getPassword().length() > 20 || user.getPassword().length() < 8 || user.getPassword().contains(" ") || JudgeHelper.isChinese(password)) {
//                response.getWriter().print("<script>" +
//                        "alert('密码不符合要求：密码长度为8~20个字符且不允许含有空格、中文。');" +
//                        "history.go(-1);" +
//                        "</script>");
//            }
//            if (user.getIdType().equals("居民身份证") && (user.getIdNumber().length() != 18 || user.getIdNumber().length() != 15)){
//                response.getWriter().print("<script>" +
//                        "alert('居民身份证长度输入有误');" +
//                        "history.go(-1);" +
//                        "</script>");
//            }
        } else {
            System.out.println("验证码不正确");
            response.getWriter().print("<script>alert('验证码不正确，请重新输入。');window.location.replace(\"/jsps/sign/sign-up.jsp\");</script>");
        }
        // 验证码结束

    }

    protected void doSignIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String verificationCode = request.getParameter("verificationCode");
        String sessionVerificationCode = (String) session.getAttribute("CHECKCODE");
        System.out.println("这是session里的验证码"+sessionVerificationCode);
        String userIdentity = request.getParameter("userIdentity");
        System.out.println("用户身份是 = " + userIdentity);
        // 暂时不要验证码
        if (verificationCode.equals(sessionVerificationCode)) {
            System.out.println("验证码正确");
            String username = request.getParameter("inputUsername");
            String password = request.getParameter("inputPassword");
            switch (userIdentity) {
                case "user":
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    UserService userService = new UserServiceImpl();
                    int result = userService.checkUserLegitimate(user);
                    if (result > 0) {
                        // 登录成功
//                        response.getWriter().print("" +
//                                "<script>" +
//                                "alert('登录成功～');" +
//                                "</script>");
//                        request.setAttribute("user", user);
                        // 使用 session 表示用户是否已经登录
//                        session.setAttribute("isAlreadyLogin", user.getUsername());
                        User userReal = userService.getUserByName(username);
                        session.setAttribute("userId", userReal.getUserId());
                        session.setAttribute("username", userReal.getUsername());
                        session.setAttribute("isAlreadyLoginUser", true);
//                        System.out.println("result = " + result);
//                        request.getRequestDispatcher("/doUser?op=user").forward(request, response);
                        int userChooseCarId = -1;
                        try {
                            if (session.getAttribute("userChooseCarId") != null)
                                userChooseCarId = (int) session.getAttribute("userChooseCarId");
//                        System.out.println("pageFrom = " + pageFrom);
                            if (userChooseCarId != -1) {
                                response.sendRedirect("/doGoods?op=rentalCar&carId=" + userChooseCarId);
                            } else {
                                response.sendRedirect("/doUser?op=user");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        // 登录失败
                        response.getWriter().print("" +
                                "<script>" +
                                "alert('用户名或密码、身份选择错误，登录失败');" +
                                "history.go(-1);" +
                                "</script>");
                    }
                    break;
                case "shopAdmin":
                    CarStore carStore = new CarStore();
                    carStore.setStoreAdminUsername(username);
                    carStore.setStorePassword(password);
                    CarStoreService carStoreService = new CarStoreServiceImpl();
                    result = carStoreService.checkCarStoreLegitimate(carStore);
                    if (result > 0) {
                        // 登录成功
//                        response.getWriter().print("" +
//                                "<script>" +
//                                "alert('登录成功～');" +
//                                "history.go(-1);" +
//                                "</script>");
                        CarStore carStoreReal = carStoreService.getCarStoreAccountByUserName(username);
                        session.setAttribute("carStoreId", carStoreReal.getStoreId());
                        session.setAttribute("isAlreadyLogin", true);
                        session.setAttribute("carStoreUsername", carStoreReal.getStoreAdminUsername());
//                        request.setAttribute("carStoreId", carStore.getStoreId());
                        response.sendRedirect("/doCarStore?op=list");
                    } else {
                        // 登录失败
                        response.getWriter().print("" +
                                "<script>" +
                                "alert('用户名或密码、身份选择错误，登录失败');" +
                                "history.go(-1);" +
                                "</script>");
                    }
                    break;
                    // TODO 系统管理员
                case "sysAdmin":
                    Admin admin = new Admin();
                    admin.setAdminUsername(username);
                    admin.setAdminPassword(password);
                    AdminService adminService = new AdminServiceImpl();
                    Admin adminReal = adminService.getAdminAccountByUserName(username);
                    System.out.println("SignIn: adminReal: " + adminReal);
                    if (adminService.checkAdminLegitimate(admin)) {
                        // 登录成功
                        session.setAttribute("sysAdminId", adminReal.getAdminId());
                        response.sendRedirect("/doSysAdmin?op=list");
                    } else {
                        // 登录失败
                        response.getWriter().print("" +
                                "<script>" +
                                "alert('用户名或密码错误，登录失败');" +
                                "history.go(-1);" +
                                "</script>");
                    }
                    break;
            }
            userIdentity = request.getParameter("userIdentity");
            System.out.println("userIdentity" + userIdentity);

        } else {
            System.out.println("验证码不正确");
            response.getWriter().print("<script>alert('验证码不正确，请重新输入。');window.location.replace(\"/jsps/sign/sign-in.jsp\");</script>");
        }
    }

}
