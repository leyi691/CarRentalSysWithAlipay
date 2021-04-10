package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.POJO.User;
import cn.edu.nbut.jerry.Utils.CommonUtils;
import cn.edu.nbut.jerry.config.NamesConfig;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.UserService;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;
import cn.edu.nbut.jerry.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/doUser")
public class UserServlet extends HttpServlet {
    private HttpSession session;
    private final OrderService orderService = new OrderServiceImpl();
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    // 因为在不同系统下文件分割符不同，所以用 File.separator
    private final static String UPLOAD_DIRECTORY = File.separator + "resources" + File.separator + "portrait-image";

//    // 上传配置
//    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
//    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        System.out.println("UserServlet");
        if ("user".equals(action)) {
            // 跳转到显示用户信息的页面
            System.out.println("ShowUser");
            userMain(request, response);
        } else if ("userIndexEdit".equals(action)) {
            // 修改用户信息
            System.out.println("userIndexEdit");
            userEdit(request, response);
        } else if ("signOut".equals(action)) {
            // 登出
            signOut(request, response);
        } else if ("orderList".equals(action)) {
            System.out.println("orderList");
            showOrderList(request, response);
        }
    }

    protected void userMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        if (request.getSession().getAttribute("userId") == null) {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        } else {
            int userId = (int) (request.getSession().getAttribute("userId"));
            User user = userService.getUserById(userId);
            request.getSession().setAttribute("user", user);
//        request.setAttribute("user", user);
            request.getRequestDispatcher("/jsps/user/userIndex.jsp").forward(request, response);
        }
    }

    protected void userEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取jsp传来的信息 由于要传递文件，所以需要使用 enctype="multipart/form-data"由jsp封装成二进制流，
        // 所以我们要将二进制流转换成文本得到数据
        User user = new User();
        boolean isPasswordChanged = false;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String isFirstUploadPortrait = null;
        // 解析request请求
        if (items != null) {
            int i = 0;
            for (Object item : items) {
                System.out.println("mark: " + i++ + " times");
                FileItem fileItem = (FileItem) item;
                // 判断是否是第一次上传头像

                if (fileItem.isFormField()) {
                    switch (fileItem.getFieldName()) {
//                        case "inputUserId":
//                            Integer userId = Integer.parseInt(fileItem.getString("utf-8"));
////                            System.out.println("inputUserId = " + userId);
//                            user.setUserId(userId);
//                            break;
                        // 也不知道为什么，这里的 inputUsername 怎么都获取不到，很奇怪
//                        case "inputUsername":
//                            String username = fileItem.getString("utf-8");
//                            System.out.println("inputUsername = " + username);
//                            user.setUsername(username);
//                            break;
                        case "inputUserId":
                            String username = fileItem.getString("utf-8");
                            System.out.println("inputUserId = " + username);
                            user.setUsername(username);
                            break;
                        case "inputPassword":
                            String password = fileItem.getString("utf-8");
                            System.out.println("inputPassword = " + password);
                            if (!password.equals("")) {
                                isPasswordChanged = true;
                                user.setPassword(password);
                            }
                            break;
                        case "inputNickname":
                            String nickName = fileItem.getString("utf-8");
                            System.out.println("inputNickname = " + nickName);
                            user.setNickname(nickName);
                            break;
                        case "inputSex":
                            String sex = fileItem.getString("utf-8");
//                            System.out.println("inputSex = " + sex);
                            user.setSex(sex);
                            break;
                        case "inputPhoneNumber":
                            String phoneNumber = fileItem.getString("utf-8");
//                            System.out.println("inputPhoneNumber = " + phoneNumber);
                            user.setPhoneNumber(phoneNumber);
                            break;
                        case "inputIdType":
                            String idType = fileItem.getString("utf-8");
//                            System.out.println("inputIdType = " + idType);
                            user.setIdType(idType);
                            break;
                        case "inputIdNumber":
                            String idNumber = fileItem.getString("utf-8");
//                            System.out.println("inputIdNumber = " + idNumber);
                            user.setIdNumber(idNumber);
                            break;
                        case "originalImageName":
                            String originalImageName = fileItem.getString("utf-8");
                            System.out.println("originalImageName = " + originalImageName);
                            user.setPortrait(originalImageName);
                            break;
                        case "Area":
                            String area = fileItem.getString("utf-8");
//                            System.out.println("Area = " + area);
                            user.setArea(area);
                            break;
                        case "inputAddress":
                            String address = fileItem.getString("utf-8");
//                            System.out.println("inputAddress = " + address);
                            user.setAddress(address);
                            break;

                        // 迷之操作 为什么我要用input传值判断是不是第一次上传啊，用request传值就好啦
                        // 可是request传值传不过来啊


                        case "isFirstUploadPortrait":
                            isFirstUploadPortrait = fileItem.getString("utf-8");
                            System.err.println("isFirstUploadPortrait = " + isFirstUploadPortrait);
                            break;
                    }
                } else {
                    // 如果是文件就上传到 portrait-img 文件夹中

                    // 得到文件的完整路径
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    Date date = new Date();
                    String path = fileItem.getName();

                    // 得到去除路径的文件名
                    String fileName = path.substring(path.lastIndexOf("\\") + 1);

                    // 为了避免下载文件时出现中文传输参数乱码，
                    // 故将文件存储名设置为年月日+时分秒，当上传超过一个文件时完全可以考虑使用3位随机数。
                    String type;
                    if (!"".equals(fileName) && fileItem.getSize() != 0 && fileItem.getSize() <= MAX_FILE_SIZE) {
                        // 可以考虑添加随机数
                        // int b = (int) (Math.random() * 1000);

                        // 获取文件类型
                        type = fileName.substring(path.lastIndexOf("."));

                        // 存储文件最终路径
                        fileName = simpleDateFormat.format(date) + type;
                        System.out.println("fileName = " + fileName);

                        // 将文件保存在web目录的resources/portrait-image中
                        if ("uploadPortrait".equals(fileItem.getFieldName())) {
                            System.out.println("uploadPortrait = " + fileItem.getName());
                            try {
                                String filePath = this.getServletContext().getRealPath("");
//                                System.out.println("filePath = " + filePath);
//                                System.out.println("deletePath = " + filePath + user.getPortrait());
                                // 如果文件存在，则先删除再加入
                                boolean isFileDeleted = deleteFile(filePath + user.getPortrait());
                                System.err.println("isFirstUploadPortrait = " + isFirstUploadPortrait);
                                // 如果文件删除，或者用户第一次上传，就需要创建并保存文件到文件夹中
                                if (isFileDeleted || Objects.equals(isFirstUploadPortrait, "true")) {
                                    fileItem.write(new File(filePath + UPLOAD_DIRECTORY, fileName));
                                    // 同时也要将路径保存在数据库中
                                    user.setPortrait(UPLOAD_DIRECTORY + File.separator + fileName);
                                }
                            } catch (Exception e) {
                                System.out.println("fileItem.write(new File(UPLOAD_DIRECTORY, fileName))======" + e);
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("items为空。也就是jsp传递过来的是空值");
        }


//                String      username        =       request.getParameter                        ("inputUsername");
//                String      password        =       request.getParameter                        ("inputPassword");
//                String      nickname        =       request.getParameter                        ("inputNickname");
//                String      sex             =       request.getParameter                        ("inputSex");
//                String      phoneNumber     =       request.getParameter                        ("inputPhoneNumber");
//                String      idType          =       request.getParameter                        ("inputIdType");
//                String      idNumber        =       request.getParameter                        ("inputIdNumber");
//                String      area            =       request.getParameter                        ("Area");
//                String      address         =       request.getParameter                        ("inputAddress");
//                String      fileName        =       request.getParameter                        ("uploadPortrait");


//        Integer     userId          =       Integer.valueOf(request.getParameter        ("inputUserId"));
//        String      username        =       request.getParameter                        ("inputUsername");
//        String      password        =       request.getParameter                        ("inputPassword");
//        String      nickname        =       request.getParameter                        ("inputNickname");
//        String      sex             =       request.getParameter                        ("inputSex");
//        String      phoneNumber     =       request.getParameter                        ("inputPhoneNumber");
//        String      idType          =       request.getParameter                        ("inputIdType");
//        String      idNumber        =       request.getParameter                        ("inputIdNumber");
//        String      area            =       request.getParameter                        ("Area");
//        String      address         =       request.getParameter                        ("inputAddress");
//        String      fileName        =       request.getParameter                        ("uploadPortrait");

//        user.       setUserId                      (userId);
//        user.       setUsername                    (username);
//        user.       setPassword                    (password);
//        user.       setNickname                    (nickname);
//        user.       setSex                         (sex);
//        user.       setPhoneNumber                 (phoneNumber);
//        user.       setIdType                      (idType);
//        user.       setIdNumber                    (idNumber);
//        user.       setArea                        (area);
//        user.       setAddress                     (address);
//        user.       setPortrait                    (fileName);

//        // 跳转到 message.jsp
//        request.getServletContext().getRequestDispatcher("/jsps/message.jsp").forward(
//                request, response);
//        user.setPortrait(fileUpload(request, response));

        UserService userService = new UserServiceImpl();
        int result;
        if (isPasswordChanged) {
            System.out.println("updateUserByUsername");
            result = userService.updateUserByUsername(user);
        } else {
            System.out.println("updateUserByUsernameWithoutPassword");
            result = userService.updateUserByUsernameWithoutPassword(user);
        }
        System.out.println("result = " + result);
//        System.out.println("userIdNumber = " + user.getIdNumber());
        if (result > 0) {
            System.out.println("result > 0");
            // 修改成功，刷新页面
//            request.setAttribute("user", user);k
//            request.setAttribute("isEditSuccess", "true");
            response.sendRedirect("/doUser?op=user");
//            request.getRequestDispatcher("/doUser?op=user").forward(request, response);
        } else if (result == 0) {
            response.getWriter().print("<script>alert('修改失败!');history.go(-1);</script>"); // history.go(-1) -1代表回退回去前面的页面
        } else if (result == -1) {
            response.getWriter().print("<script>alert('修改失败!');history.go(-1);</script>");
        }
    }

    private boolean deleteFile(String filePath) {
        File file = new File(filePath);
        boolean isFileDeleted = false;
        // 再次上传需要先删除已经存在的文件
        if (file.exists()) {
            isFileDeleted = file.delete();
            System.out.println("isFileDeleted = " + isFileDeleted);
        } else {
            System.err.println("删除文件失败:" + filePath + "不存在！");
        }
        return isFileDeleted;
    }

    protected void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置提示语句
        // request.getSession().setAttribute("msg", "退出成功!即将跳转首页");
        // 跳转提示页面
        // request.getRequestDispatcher("/msg.jsp").forward(request, response);
        // 销毁session对象
        request.getSession().invalidate();
        PrintWriter out = response.getWriter();
        out.print("<script>alert('确定退出？');</script>");
        //重定向到首页
        response.sendRedirect("/doIndex?op=list");
    }

    private void showOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 显示 "当前登录用户" 所有的订单
        session = request.getSession();
        if (session.getAttribute("userId") != null) {
            int userId = (int) session.getAttribute("userId");
            List<Order> orders = (List<Order>) CommonUtils.getSpecificPage(0, orderService.getAllOrderByUserId(userId), NamesConfig.ONE_PAGE_SIZE_LONG);
            System.out.println("orders.size() = " + orders.size());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsps/user/userOrder.jsp").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        }
    }








    // TODO 将头像上传到服务器，并读取显示在页面上！ DONE!

//    protected String fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String filePath = null;
//
//        // 检测是否为多媒体上传
//        if (!ServletFileUpload.isMultipartContent(request)) {
//            // 如果不是则停止
//            PrintWriter writer = response.getWriter();
//            writer.print("<script>alert('Error: 表单必须包含 enctype=multipart/form-data');history.go(-1);</script>");
//            writer.flush();
//            return null;
//        }
//
//        // 配置上传参数
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
//        factory.setSizeThreshold(MEMORY_THRESHOLD);
//        // 设置临时存储目录
//        factory.setRepository(new File(System.getProperty("java.io.tempDir")));
//
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        // 设置最大文件上传值
//        upload.setFileSizeMax(MAX_FILE_SIZE);
//
//        // 设置最大请求值 (包含文件和表单数据)
//        upload.setSizeMax(MAX_REQUEST_SIZE);
//
//        // 中文处理
//        upload.setHeaderEncoding("UTF-8");
//
//        // 构造临时路径来存储上传的文件
//        // 这个路径相对当前应用的目录
//        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
//
//
//        // 如果目录不存在则创建
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//
//        try {
//            // 解析请求的内容提取文件数据
//            @SuppressWarnings("unchecked")
//            List<FileItem> formItems = upload.parseRequest(request);
//
//            if (formItems != null && formItems.size() > 0) {
//                // 迭代表单数据
//                for (FileItem item : formItems) {
//                    // 处理不在表单中的字段
//                    if (!item.isFormField()) {
//                        String fileName = new File(item.getName()).getName();
//                        System.out.println("fileName= " + fileName);
//                        filePath = uploadPath + File.separator + fileName;
//                        File storeFile = new File(filePath);
//                        // 在控制台输出文件的上传路径
//                        System.out.println(filePath);
//                        // 保存文件到硬盘
//                        item.write(storeFile);
//                        request.setAttribute("message",
//                                "文件上传成功!");
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("错误信息: " + ex.getMessage());
//            request.setAttribute("message", "错误信息: " + ex.getMessage());
//        }
//        return filePath;
//    }

}
