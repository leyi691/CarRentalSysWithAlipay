package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Admin;
import cn.edu.nbut.jerry.POJO.CarStore;
import cn.edu.nbut.jerry.POJO.User;
import cn.edu.nbut.jerry.Utils.CommonUtils;
import cn.edu.nbut.jerry.config.NamesConfig;
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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "系统管理员处理", urlPatterns = "/doSysAdmin")
public class sysAdminServlet extends HttpServlet {
    private HttpSession session;
    private final AdminService      adminService          = new AdminServiceImpl();
    private final CarStoreService   carStoreService       = new CarStoreServiceImpl();
    private final UserService       userService           = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        if ("list".equals(action)) {
            // 显示管理员主页面
            System.out.println("系统管理员主页面");
            showMain(request, response);
        } else if ("editSelf".equals(action)){
            System.out.println("系统管理员修改自身信息");
            editSelf(request, response);
        } else if ("shopManage".equals(action)) {
            System.out.println("系统管理员管理门店信息");
            shopManage(request, response);
        } else if ("userManage".equals(action)) {
            System.out.println("系统管理员管理用户信息");
            userManage(request, response);
        }
    }

    /**
     * 显示系统管理员信息
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void showMain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session = request.getSession();
        // 判断是否登录
        if (session.getAttribute("sysAdminId") != null) {
            int sysAdminId = (int) session.getAttribute("sysAdminId");
            Admin admin = adminService.getAdminAccountById(sysAdminId);
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/jsps/sysAdmin/sysAdminIndex.jsp").forward(request, response);
        } else {
            // 未登录提示登录
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        }
    }

    /**
     * 修改系统管理员信息
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void editSelf(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session = request.getSession();
        // 判断是否登录
        if (session.getAttribute("sysAdminId") != null) {
            int adminId                 =           Integer.parseInt(request.getParameter       ("inputAdminId"));
            String password             =           request.getParameter                        ("inputPassword");
            String adminName            =           request.getParameter                        ("inputAdminName");
            String phoneNumber          =           request.getParameter                        ("inputPhoneNumber");

            Admin adminEdit = new Admin();

            adminEdit.setAdminId                    (adminId);
            adminEdit.setAdminName                  (adminName);
            adminEdit.setAdminPhoneNumber           (phoneNumber);

            boolean isPasswordChanged = false;
            int result = -1;

            if (!password.equals("")) {
                adminEdit.setAdminPassword(password);
                isPasswordChanged = true;
            }
            if (isPasswordChanged) {
                result = adminService.updateAdminAccountById(adminEdit);
            } else {
                result = adminService.updateAdminByIdWithoutPassword(adminEdit);
            }
            if (result > 0) {
                showMain(request, response);
            } else {
                response.getWriter().print("" +
                        "<script>" +
                        "alert('修改失败');" +
                        "history.go(0);" +
                        "</script>");
            }
        } else {
            // 未登录提示登录
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        }
    }

    protected void shopManage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session = request.getSession();
        // 判断是否登录
        if (session.getAttribute("sysAdminId") != null) {
            String action = request.getParameter("do");
            switch (action) {
                case "list":
                    // 展示所有门店列表
                    showCarStoreList(request, response);
                    break;
                case "insert":
                    // 新增门店信息
                    insertCarStore(request, response);
                    break;
                case "edit":
                    // 修改门店信息
                    editCarStore(request, response);
                    break;
                case "delete":
                    // 删除门店
                    deleteStore(request, response);
                    break;
            }
        } else {
            // 未登录提示登录
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        }
    }
    private void deleteStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 删除门店
        Integer storeId = Integer.valueOf(request.getParameter("storeId"));
        int result = carStoreService.deleteCarStoreAccountById(storeId);
        if (result > 0) {
            // 删除成功
            response.sendRedirect("/jsps/sysAdmin/sysAdminManageCarStore.jsp");
        } else {
            response.getWriter().print("" +
                    "<script>" +
                    "alert('删除失败！');" +
                    "history.go(-1);" +
                    "</script>");
        }
    }


    /**
     * 新增门店
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void insertCarStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 新增门店信息

//                Integer     adminId             = (Integer) session.getAttribute("sysAdminId");
        String      storeUserName       =           request.getParameter("storeUserName");
        String      storeName           =           request.getParameter("storeName");
        String      password            =           request.getParameter("password");
        String      contactNumber       =           request.getParameter("contactNumber");
        String      inputStatus         =           request.getParameter("inputStatus");
        String      area                =           request.getParameter("Area");
        String      address             =           request.getParameter("address");

        CarStore carStoreInsert = new CarStore();

        carStoreInsert      .setStoreAdminUsername        (storeUserName);
        carStoreInsert      .setStoreName                 (storeName);
        carStoreInsert      .setStorePassword             (password);
        carStoreInsert      .setStoreContactNumber        (contactNumber);
        carStoreInsert      .setStoreStatus               (inputStatus);
        carStoreInsert      .setStoreArea                 (area);
        carStoreInsert      .setStoreAddress              (address);

        int result = carStoreService.addCarStoreAccount(carStoreInsert);
        if (result > 0) {
            // 插入成功
            response.sendRedirect("/jsps/sysAdmin/sysAdminManageCarStore.jsp");
        } else {
            response.getWriter().print("" +
                    "<script>" +
                    "alert('添加失败！');" +
                    "history.go(-1);" +
                    "</script>");
        }
    }

    /**
     * 修改门店信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void editCarStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 修改门店信息

        boolean isPasswordChanged = false;
        int result = -1;
//                Integer     adminId             = (Integer) session.getAttribute("sysAdminId");
        Integer     storeId             = Integer.valueOf(  request.getParameter        ("storeIdEdit"));
        String      storeName           =                   request.getParameter        ("storeName");
        String      password            =                   request.getParameter        ("password");
        String      contactNumber       =                   request.getParameter        ("contactNumber");
        String      inputStatus         =                   request.getParameter        ("inputStatus");
        String      area                =                   request.getParameter        ("Area");
        String      address             =                   request.getParameter        ("address");

        CarStore carStoreEdit = new CarStore();

        carStoreEdit      .setStoreId                   (storeId);
        carStoreEdit      .setStoreName                 (storeName);

        carStoreEdit      .setStoreContactNumber        (contactNumber);
        carStoreEdit      .setStoreStatus               (inputStatus);
        carStoreEdit      .setStoreArea                 (area);
        carStoreEdit      .setStoreAddress              (address);


        // 密码单独处理 需要判断用户有没有修改密码，毕竟密码不能明文显示在页面上。
        if (!password.equals("")) {
            isPasswordChanged = true;
            carStoreEdit      .setStorePassword             (password);
        }


        if (isPasswordChanged) {
            // 密码修改了
            result = carStoreService.updateCarStoreAccountById(carStoreEdit);
        } else {
            // 密码没有修改
            result = carStoreService.updateCarStoreByIdWithoutPassword(carStoreEdit);
        }


        if (result > 0) {
            // 修改成功
            response.sendRedirect("/jsps/sysAdmin/sysAdminManageCarStore.jsp");
        } else {
            response.getWriter().print("" +
                    "<script>" +
                    "alert('修改失败！');" +
                    "history.go(-1);" +
                    "</script>");
        }
    }

    /**
     * 展示所有门店信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showCarStoreList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 显示所有的门店信息
        List<CarStore> carStoreList = (List<CarStore>) CommonUtils.getSpecificPage(0, carStoreService.getAllCarStoreAccount(), NamesConfig.ONE_PAGE_SIZE_MID);
        request.setAttribute("carStores", carStoreList);
        request.getRequestDispatcher("/jsps/sysAdmin/sysAdminManageCarStore.jsp").forward(request, response);
    }

    protected void userManage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session = request.getSession();
        // 判断是否登录
        if (session.getAttribute("sysAdminId") != null) {
            String action = request.getParameter("do");
            switch (action) {
                case "list":
                    // 展示所有用户列表
                    showUserList(request, response);
                    break;
                case "delete":
                    // 删除用户
                    deleteUser(request, response);
                    break;
            }
        } else {
            // 未登录提示登录
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        }
    }
    /**
     * 展示所有用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 显示所有的门店信息
        List<User> users = (List<User>) CommonUtils.getSpecificPage(0, userService.getAllUser(), NamesConfig.ONE_PAGE_SIZE_LONG);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsps/sysAdmin/sysAdminManageUser.jsp").forward(request, response);
    }

    /**
     * 删除用户
     * @param request
     * @param response
     * @throws IOException
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 删除用户
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        int result = userService.deleteUserById(userId);
        if (result > 0) {
            // 删除成功
            response.sendRedirect("/jsps/sysAdmin/sysAdminManageUser.jsp");
        } else {
            response.getWriter().print("" +
                    "<script>" +
                    "alert('删除失败！');" +
                    "history.go(-1);" +
                    "</script>");
        }
    }
}
