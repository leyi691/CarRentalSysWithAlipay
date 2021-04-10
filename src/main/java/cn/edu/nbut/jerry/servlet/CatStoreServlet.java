package cn.edu.nbut.jerry.servlet;

import cn.edu.nbut.jerry.POJO.Car;
import cn.edu.nbut.jerry.POJO.CarStore;
import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.Utils.CommonUtils;
import cn.edu.nbut.jerry.Utils.PayUtils;
import cn.edu.nbut.jerry.config.NamesConfig;
import cn.edu.nbut.jerry.service.CarService;
import cn.edu.nbut.jerry.service.CarStoreService;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.impl.CarServiceImpl;
import cn.edu.nbut.jerry.service.impl.CarStoreServiceImpl;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;
import com.alipay.api.response.AlipayTradeRefundResponse;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/doCarStore", name = "汽车商店控制")
public class CatStoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // 上传文件存储目录
    // 因为在不同系统下文件分割符不同，所以用 File.separator
    // 这里的文件目录是大目录，每个店都有自己的文件夹存放图片
    private final static String UPLOAD_DIRECTORY = File.separator + "resources" + File.separator + "carStore-image";
    private final static String UPLOAD_DIRECTORY_FOR_CARS = File.separator + "resources" + File.separator + "car-image";
    // 上传配置
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private int CAR_STORE_ID;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 解决表单传递过来的中文乱码问题
        response.setContentType("text/html;charset=utf-8"); // 解决弹出窗口中文乱码的问题
        String action = request.getParameter("op");
        System.out.println("CarStoreServlet");
        if ("list".equals(action)) {
            System.out.println("list");
            carStoreMainIndex(request, response);
        } else if ("edit".equals(action)) {
            System.out.println("editCarStore");
            editCarStore(request, response);
        } else if ("carManage".equals(action)) {
            System.out.println("carManage");
            carManage(request, response);
        } else if (NamesConfig.CAR_STORE_DISPATCH_DO.equals(action)) {
            carDispatch(request, response);
        }
    }

    /**
     * 显示 商店主页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void carStoreMainIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarStoreService carStoreService = new CarStoreServiceImpl();
//        CarStore carStorePass = (CarStore) request.getAttribute("carStore");
        if (request.getSession().getAttribute("carStoreId") == null) {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        } else {
            CAR_STORE_ID = (int) (request.getSession().getAttribute("carStoreId"));
            System.out.println("lijiayuCARSTORE:carStoreId = " + CAR_STORE_ID);
            // TODO 以下测试用的代码记得删掉，并启用上面那行
            // 测试用代码起始位置
//        CarStore carStorePass = carStoreService.queryCarStoreAccountById(1);
            // 测试用代码结束位置

            CarStore carStore = carStoreService.getCarStoreAccountById(CAR_STORE_ID);
            request.setAttribute("carStore", carStore); // 正常传递数据
            request.getRequestDispatcher("/jsps/carStore/carStoreIndex.jsp").forward(request, response);
        }
//        TODO NEXT 把值显示在jsp中
    }

    /**
     * 修改汽车商店的信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editCarStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取jsp传来的信息 由于要传递文件，所以需要使用 enctype="multipart/form-data"由jsp封装成二进制流，
        // 所以我们要将二进制流转换成文本得到数据
        CarStore carStore = new CarStore();
        boolean isPasswordChanged = false;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        // 解析request请求
        if (items != null) {
            int i = 0;
            String isFirstUpload1 = null;
            String isFirstUpload2 = null;
            String isFirstUpload3 = null;
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
                        case "inputAdminId":      // 用户名
                            String username = fileItem.getString("utf-8");
                            System.out.println("inputAdminUsername = " + username);
                            carStore.setStoreAdminUsername(username);
                            break;
                        case "inputPassword":       // 密码
                            String password = fileItem.getString("utf-8");
                            System.out.println("inputPassword = " + password);
                            if (!password.equals("")) {
                                isPasswordChanged = true;
                                carStore.setStorePassword(password);
                            }
                            break;
                        case "inputStoreName":      // 店铺名
                            String storeName = fileItem.getString("utf-8");
                            System.out.println("inputNickname = " + storeName);
                            carStore.setStoreName(storeName);
                            break;
                        case "inputStatus":         // 店铺状态
                            String status = fileItem.getString("utf-8");
                            System.out.println("inputSex = " + status);
                            carStore.setStoreStatus(status);
                            break;
                        case "inputPhoneNumber":    // 联系电话
                            String phoneNumber = fileItem.getString("utf-8");
//                            System.out.println("inputPhoneNumber = " + phoneNumber);
                            carStore.setStoreContactNumber(phoneNumber);
                            break;
                        // 将原有的照片路径不变
                        case "imagePath1":
                            String imagePath1 = fileItem.getString("utf-8");
                            System.err.println("lijiayu: imagePath1 = " + imagePath1);
                            carStore.setStoreImagePath1(imagePath1);
                            break;
                        case "imagePath2":
                            String imagePath2 = fileItem.getString("utf-8");
                            System.err.println("lijiayu: imagePath2 = " + imagePath2);
                            carStore.setStoreImagePath2(imagePath2);
                            break;
                        case "imagePath3":
                            String imagePath3 = fileItem.getString("utf-8");
                            System.err.println("lijiayu: imagePath3 = " + imagePath3);
                            carStore.setStoreImagePath3(imagePath3);
                            break;
                            // TODO 轮播图 图片2地址 、 3 、 4 ......


                        case "Area":                // 区域代码
                            String area = fileItem.getString("utf-8");
//                            System.out.println("Area = " + area);
                            carStore.setStoreArea(area);
                            break;
                        case "inputAddress":        // 具体地址
                            String address = fileItem.getString("utf-8");
//                            System.out.println("inputAddress = " + address);
                            carStore.setStoreAddress(address);
                            break;

//                             判断是否是第一次上传
                        case "isFirstUpload1":   // 判断是否是第一次上传图片
                            isFirstUpload1 = fileItem.getString("utf-8");
                            System.err.println("lijiayu: isFirstUpload1 no attb = " + isFirstUpload1);
                            break;
                        case "isFirstUpload2":   // 判断是否是第一次上传图片
                            isFirstUpload2 = fileItem.getString("utf-8");
                            System.err.println("lijiayu: isFirstUpload2 no attb = " + isFirstUpload2);
                            break;
                        case "isFirstUpload3":   // 判断是否是第一次上传图片
                            isFirstUpload3 = fileItem.getString("utf-8");
                            System.err.println("lijiayu: isFirstUpload3 no attb = " + isFirstUpload3);
                            break;
                    }
                }
                else {
                    // 如果是文件就上传到 carStore-image 文件夹中

                    // 得到文件的完整路径
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    Date date = new Date();
                    String path = fileItem.getName();

                    // 得到去除路径的文件名
                    String fileName = path.substring(path.lastIndexOf("\\") + 1);

                    // 为了避免下载文件时出现中文传输参数乱码，
                    // 故将文件存储名设置为年月日+时分秒，当上传超过一个文件时完全可以考虑使用3位随机数。
                    String fileType;
                    if (!"".equals(fileName) && fileItem.getSize() != 0 && fileItem.getSize() <= MAX_FILE_SIZE) {
                        // 可以考虑添加随机数
                         int b = (int) (Math.random() * 1000);

                        // 获取文件类型
                        fileType = fileName.substring(path.lastIndexOf("."));

                        // 文件存储名 包含日期时间 + 3位随机数
                        fileName = simpleDateFormat.format(date)+ b + fileType;
                        System.out.println("fileName = " + fileName);
                        // carStoreDir文件夹 = <绝对路径>/CarRental/
                        String filePath = this.getServletContext().getRealPath("");
                        // userDirRelative 文件夹 = resources/carStore-image/<用户名> 上传到数据库的就是这个
                        String userDirRelative = UPLOAD_DIRECTORY + File.separator + carStore.getStoreAdminUsername();
                        // carStoreDir文件夹 = <绝对路径>/CarRental/resources/carStore-image/<用户名>
                        String carStoreDirAbsolute = filePath + userDirRelative;
                        System.out.println("carStoreDirAbsolute = " + carStoreDirAbsolute);


                        // 如果目录不存在则创建 最终存储目录 carStoreDirAbsolute
                        File uploadDir = new File(carStoreDirAbsolute);
                        // 这里提醒一下
                        // 1.mkdir()只能创建一级目录，且父目录必须存在
                        // 2.mkdirs()可以直接创建多级目录
                        if (!uploadDir.exists()) {
                            System.out.println("uploadDir.mkdirs() = " + uploadDir.mkdirs());
                        }
                        // 将文件保存在web目录的resources/carStore-image中 TODO 每个用户一个单独文件夹。
                        // 文件只上传 最多5个文件/次
                        // 第一个文件
                        if ("inputFileName1".equals(fileItem.getFieldName())){
                            System.out.println("inputFileName1 = " + fileItem.getName());
                            try {

//                                System.out.println("filePath = " + filePath);
//                                System.out.println("deletePath = " + filePath + user.getPortrait());

                                boolean isFileDeleted = deleteFile(filePath + carStore.getStoreImagePath1());
                                System.out.println("lijiayu file: delete file path======" + carStoreDirAbsolute + File.separator + fileName);
                                // 为什么这里的getAttribute获取不到jsp中设置的值呢，是因为jsp不是通过转发过来servlet的，所以要通过传统方式
//                                String isFirstAddImage = (String) request.getAttribute("isFirstUpload1");
//                                System.out.println("lijiayu: isFirstUpload1 = " + isFirstAddImage);
                                // 如果文件已经删除或者是第一次上传文件
                                if (isFileDeleted || Objects.requireNonNull(isFirstUpload1).equals("true")) {
                                    fileItem.write(new File(carStoreDirAbsolute, fileName));
                                    // 同时也要将路径保存在数据库中
                                    System.out.println("lijiayu file: setStoreImagePath1======" + userDirRelative);
                                    carStore.setStoreImagePath1(userDirRelative + File.separator + fileName);
                                }
                            } catch (Exception e) {
                                System.out.println("lijiayu: fileItem.write(new File(UPLOAD_DIRECTORY, fileName))======" + e);
                                e.printStackTrace();
                            }
                        }
                        if ("inputFileName2".equals(fileItem.getFieldName())){
                            System.out.println("inputFileName2 = " + fileItem.getName());
                            try {

//                                System.out.println("filePath = " + filePath);
//                                System.out.println("deletePath = " + filePath + user.getPortrait());

                                boolean isFileDeleted = deleteFile(filePath + carStore.getStoreImagePath2());
                                if (isFileDeleted || Objects.requireNonNull(isFirstUpload2).equals("true")) {
                                    fileItem.write(new File(carStoreDirAbsolute, fileName));
                                    // 同时也要将路径保存在数据库中
                                    carStore.setStoreImagePath2(userDirRelative + File.separator + fileName);
                                }
                            } catch (Exception e) {
                                System.out.println("fileItem.write(new File(UPLOAD_DIRECTORY, fileName))======" + e);
                                e.printStackTrace();
                            }
                        }
                        if ("inputFileName3".equals(fileItem.getFieldName())){
                            System.out.println("inputFileName3 = " + fileItem.getName());
                            try {

//                                System.out.println("filePath = " + filePath);
//                                System.out.println("deletePath = " + filePath + user.getPortrait());

                                boolean isFileDeleted = deleteFile(filePath + carStore.getStoreImagePath3());
                                if (isFileDeleted || Objects.requireNonNull(isFirstUpload3).equals("true")) {
                                    fileItem.write(new File(carStoreDirAbsolute, fileName));
                                    // 同时也要将路径保存在数据库中
                                    carStore.setStoreImagePath3(userDirRelative + File.separator + fileName);
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

        CarStoreService service = new CarStoreServiceImpl();
        int result;
        if (isPasswordChanged) {
            result = service.updateCarStoreByUsername(carStore);
        } else {
            result = service.updateCarStoreByUsernameWithoutPassword(carStore);
        }
        System.out.println("result = " + result);
//        System.out.println("userIdNumber = " + user.getIdNumber());
        if (result > 0) {
            System.out.println("result > 0");
            // 修改成功，刷新页面
//            request.setAttribute("isEditSuccess", "true"); // 表示修改成功
//            request.setAttribute("carStore", carStore);
            response.sendRedirect("/doCarStore?op=list");
        } else if (result == 0) {
            response.getWriter().print("<script>alert('修改失败!');history.go(-1);</script>"); // history.go(-1) -1代表回退回去前面的页面
        } else if (result == -1) {
            response.getWriter().print("<script>alert('修改失败!');history.go(-1);</script>");
        }
    }

    /**
     * 订单出车，取消订单
     * 路径：/doCarStore?op=carManage&do=list
     */
    protected void carManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CarStoreService carStoreService = new CarStoreServiceImpl();
        CarService carService = new CarServiceImpl();
        // TODO 以下测试用的代码记得删掉，并启用上面那行
        // 测试用代码起始位置

        String action = request.getParameter("do");


        switch (action) {
            case "list":
                // 展示所有汽车
                // 查询这个门店所有的汽车，并显示在页面上
                List<Car> cars = carService.getAllCarsByStoreId(CAR_STORE_ID);
                cars = (List<Car>) CommonUtils.getSpecificPage(0, cars, NamesConfig.ONE_PAGE_SIZE_SHORT);
                request.setAttribute("carStoreId", CAR_STORE_ID);
                request.setAttribute("cars", cars);
                // TODO 修改重定向的页面 jsp
                request.getRequestDispatcher("/jsps/carStore/carStoreCarManage.jsp").forward(request, response);
                break;
            case "insert": {
                // 增加汽车信息，提示增加成功。
                // 获取jsp传来的信息 由于要传递文件，所以需要使用 enctype="multipart/form-data"由jsp封装成二进制流，
                // 所以我们要将二进制流转换成文本得到数据
                Car carInsert = new Car();
                boolean isPasswordChanged = false;
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                // 解析request请求
                if (items != null) {
                    int i = 0;
                    String isFirstUpload = null;
//                String isFirstUpload2 = null;
//                String isFirstUpload3 = null;
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
                                // 也不知道为什么，这里的 inputUsername 怎么都获取不到，很奇怪 哦后来知道了，别问我为什么知道的，
                                case "carStoreId":
                                    Integer carStoreId = Integer.valueOf(fileItem.getString("utf-8"));
                                    System.out.println("carStoreId = " + carStoreId);
                                    carInsert.setStoreId(carStoreId);
                                    break;
                                case "licensePlateNumber":  // 车牌号
                                    String licensePlateNumber = fileItem.getString("utf-8");
                                    System.out.println("licensePlateNumber = " + licensePlateNumber);
                                    carInsert.setLicensePlateNumber(licensePlateNumber);
                                    break;
//                            case "inputPassword":       // 密码
//                                String password = fileItem.getString("utf-8");
//                                System.out.println("inputPassword = " + password);
//                                if (!password.equals("")) {
//                                    isPasswordChanged = true;
//                                    carStore.setStorePassword(password);
//                                }
//                                break;
                                case "brand":      // 店铺名
                                    String brand = fileItem.getString("utf-8");
                                    System.out.println("brand = " + brand);
                                    carInsert.setBrand(brand);
                                    break;
                                case "brandNumber":         // 店铺状态
                                    String brandNumber = fileItem.getString("utf-8");
                                    System.out.println("brandNumber = " + brandNumber);
                                    carInsert.setBrandNumber(brandNumber);
                                    break;
                                case "rentalPrice":         // 店铺状态
                                    String rentalPrice = fileItem.getString("utf-8");
                                    System.out.println("rentalPrice = " + rentalPrice);
                                    carInsert.setRentalPrice(rentalPrice);
                                    break;
                                case "displacement":         // 店铺状态
                                    String displacement = fileItem.getString("utf-8");
                                    System.out.println("displacement = " + displacement);
                                    carInsert.setDisplacement(displacement);
                                    break;
                                case "cars":         // 店铺状态 TODO 可能会获取不到
                                    Integer carsInput = Integer.valueOf(fileItem.getString("utf-8"));
                                    System.out.println("cars = " + carsInput);
                                    carInsert.setCars(carsInput);
                                    break;
                                case "seats":         // 店铺状态 TODO 可能会获取不到
                                    Integer seats = Integer.valueOf(fileItem.getString("utf-8"));
                                    System.out.println("seats = " + seats);
                                    carInsert.setSeats(seats);
                                    break;
                                case "engineNumber":         // 店铺状态
                                    String engineNumber = fileItem.getString("utf-8");
                                    System.out.println("engineNumber = " + engineNumber);
                                    carInsert.setEngineNumber(engineNumber);
                                    break;
                                case "frameNumber":         // 店铺状态
                                    String frameNumber = fileItem.getString("utf-8");
                                    System.out.println("frameNumber = " + frameNumber);
                                    carInsert.setFrameNumber(frameNumber);
                                    break;
                                case "purchaseDate":         // 店铺状态
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
                                    try {
                                        Date purchaseDate = simpleDateFormat.parse(fileItem.getString("utf-8"));
                                        System.out.println("purchaseDate = " + purchaseDate);
                                        carInsert.setPurchaseDate(purchaseDate);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case "status":         // 店铺状态
                                    String status = fileItem.getString("utf-8");
                                    System.out.println("status = " + status);
                                    carInsert.setCarStatus(status);
                                    break;
                                case "carType":         // 店铺状态
                                    String carType = fileItem.getString("utf-8");
                                    System.out.println("status = " + carType);
                                    carInsert.setCarType(carType);
                                    break;
                                // TODO 轮播图 图片2地址 、 3 、 4 ......

//                             判断是否是第一次上传
                                case "isFirstUpload":   // 判断是否是第一次上传图片
                                    isFirstUpload = fileItem.getString("utf-8");
                                    System.err.println("lijiayu: isFirstUpload no attb = " + isFirstUpload);
                                    break;
//                            case "isFirstUpload2":   // 判断是否是第一次上传图片
//                                isFirstUpload2 = fileItem.getString("utf-8");
//                                System.err.println("lijiayu: isFirstUpload2 no attb = " + isFirstUpload2);
//                                break;
//                            case "isFirstUpload3":   // 判断是否是第一次上传图片
//                                isFirstUpload3 = fileItem.getString("utf-8");
//                                System.err.println("lijiayu: isFirstUpload3 no attb = " + isFirstUpload3);
//                                break;
                            }
                        } else { // 如果是文件就上传到 car-image 文件夹中
                            // 需要修改的地方：
                            // 1.UPLOAD_DIRECTORY userDirRelative
                            // 2.在jsp中添加判断是否是第一次上传
                            System.err.println("lijiayu: 这是一个文件 = " + fileItem.getFieldName());
                            // 得到文件的完整路径
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                            Date date = new Date();
                            String path = fileItem.getName();

                            // 得到去除路径的文件名
                            String fileName = path.substring(path.lastIndexOf("\\") + 1);

                            // 为了避免下载文件时出现中文传输参数乱码，
                            // 故将文件存储名设置为年月日+时分秒，当上传超过一个文件时完全可以考虑使用3位随机数。
                            String fileType;
                            if (!"".equals(fileName) && fileItem.getSize() != 0 && fileItem.getSize() <= MAX_FILE_SIZE) {
                                // 可以考虑添加随机数
//                            int b = (int) (Math.random() * 1000);

                                // 获取文件类型
                                fileType = fileName.substring(path.lastIndexOf("."));

                                // 文件存储名 包含日期时间 + 3位随机数
                                fileName = simpleDateFormat.format(date) + fileType;
                                System.out.println("fileName = " + fileName);
                                // carStoreDir文件夹 = <绝对路径>/CarRental/
                                String filePath = this.getServletContext().getRealPath("");
                                // userDirRelative 文件夹 = resources/carStore-image/<用户名> 上传到数据库的就是这个
                                String userDirRelative = UPLOAD_DIRECTORY_FOR_CARS + File.separator;
                                // carDir文件夹 = <绝对路径>/CarRental/resources/car-image
                                String carDirAbsolute = filePath + userDirRelative;
                                System.out.println("carStoreDirAbsolute = " + carDirAbsolute);


                                // 如果目录不存在则创建 最终存储目录 carStoreDirAbsolute
                                File uploadDir = new File(carDirAbsolute);
                                // 这里提醒一下
                                // 1.mkdir()只能创建一级目录，且父目录必须存在
                                // 2.mkdirs()可以直接创建多级目录
                                if (!uploadDir.exists()) {
                                    System.out.println("uploadDir.mkdirs() = " + uploadDir.mkdirs());
                                }
                                // 将文件保存在web目录的resources/car-image中
                                if ("carPicture".equals(fileItem.getFieldName())) {
                                    System.out.println("carPicture = " + fileItem.getName());
                                    try {
                                        boolean isFileDeleted = deleteFile(filePath + carInsert.getCarPicture());
                                        System.out.println("lijiayu file: delete file path======" + carDirAbsolute + fileName);
                                        // 新增不需要删除文件
//                                    if (isFileDeleted) {
                                        fileItem.write(new File(carDirAbsolute, fileName));
                                        // 同时也要将路径保存在数据库中
                                        System.out.println("lijiayu file: setCarPicture======" + carDirAbsolute);
                                        carInsert.setCarPicture(userDirRelative + File.separator + fileName);
//                                    }
                                    } catch (Exception e) {
                                        System.out.println("lijiayu: fileItem.write(new File(UPLOAD_DIRECTORY, fileName))======" + e);
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

                int result;
                result = carService.addCarById(carInsert);
                System.out.println("result = " + result);
//        System.out.println("userIdNumber = " + user.getIdNumber());
                if (result > 0) {
                    System.out.println("result > 0");
                    // 新增成功，刷新页面
                    response.sendRedirect(request.getContextPath() + "/doCarStore?op=carManage&do=list&insertSuccess=true");
                } else if (result == 0) {
                    response.getWriter().print("<script>alert('新增失败!');history.go(-1);</script>"); // history.go(-1) -1代表回退回去前面的页面
                } else if (result == -1) {
                    response.getWriter().print("<script>alert('新增失败!');history.go(-1);</script>");
                }
                break;
            }
            case "edit": {
                // 修改汽车信息，刷新页面，提示修改成功。
                // 增加汽车信息，提示增加成功。
                // 获取jsp传来的信息 由于要传递文件，所以需要使用 enctype="multipart/form-data"由jsp封装成二进制流，
                // 所以我们要将二进制流转换成文本得到数据
                Car carEdit = new Car();
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                // 解析request请求
                if (items != null) {
                    int i = 0;
                    String isFirstUpload = null;
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
                                // 也不知道为什么，这里的 inputUsername 怎么都获取不到，很奇怪 哦后来知道了，别问我为什么知道的，
                                case "carId":
                                    Integer carId = Integer.valueOf(fileItem.getString("utf-8"));
                                    System.out.println("carId = " + carId);
                                    carEdit.setCarId(carId);
                                    break;
                                case "carStoreId":
                                    Integer carStoreId = Integer.valueOf(fileItem.getString("utf-8"));
                                    System.out.println("carStoreId = " + carStoreId);
                                    carEdit.setStoreId(carStoreId);
                                    break;
                                case "licensePlateNumber":  // 车牌号
                                    String licensePlateNumber = fileItem.getString("utf-8");
                                    System.out.println("licensePlateNumber = " + licensePlateNumber);
                                    carEdit.setLicensePlateNumber(licensePlateNumber);
                                    break;
//                            case "inputPassword":       // 密码
//                                String password = fileItem.getString("utf-8");
//                                System.out.println("inputPassword = " + password);
//                                if (!password.equals("")) {
//                                    isPasswordChanged = true;
//                                    carStore.setStorePassword(password);
//                                }
//                                break;
                                case "brand":      // 店铺名
                                    String brand = fileItem.getString("utf-8");
                                    System.out.println("brand = " + brand);
                                    carEdit.setBrand(brand);
                                    break;
                                case "brandNumber":         // 店铺状态
                                    String brandNumber = fileItem.getString("utf-8");
                                    System.out.println("brandNumber = " + brandNumber);
                                    carEdit.setBrandNumber(brandNumber);
                                    break;
                                case "rentalPrice":         // 店铺状态
                                    String rentalPrice = fileItem.getString("utf-8");
                                    System.out.println("rentalPrice = " + rentalPrice);
                                    carEdit.setRentalPrice(rentalPrice);
                                    break;
                                case "displacement":         // 店铺状态
                                    String displacement = fileItem.getString("utf-8");
                                    System.out.println("displacement = " + displacement);
                                    carEdit.setDisplacement(displacement);
                                    break;
                                case "cars":         // 店铺状态 TODO 可能会获取不到
                                    Integer carsInput = Integer.valueOf(fileItem.getString("utf-8"));
                                    System.out.println("cars = " + carsInput);
                                    carEdit.setCars(carsInput);
                                    break;
                                case "seats":         // 店铺状态 TODO 可能会获取不到
                                    Integer seats = Integer.valueOf(fileItem.getString("utf-8"));
                                    System.out.println("seats = " + seats);
                                    carEdit.setSeats(seats);
                                    break;
                                case "engineNumber":         // 店铺状态
                                    String engineNumber = fileItem.getString("utf-8");
                                    System.out.println("engineNumber = " + engineNumber);
                                    carEdit.setEngineNumber(engineNumber);
                                    break;
                                case "frameNumber":         // 店铺状态
                                    String frameNumber = fileItem.getString("utf-8");
                                    System.out.println("frameNumber = " + frameNumber);
                                    carEdit.setFrameNumber(frameNumber);
                                    break;
                                case "purchaseDate":         // 店铺状态
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
                                    try {
                                        Date purchaseDate = simpleDateFormat.parse(fileItem.getString("utf-8"));
                                        System.out.println("purchaseDate = " + purchaseDate);
                                        carEdit.setPurchaseDate(purchaseDate);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case "status":         // 店铺状态
                                    String status = fileItem.getString("utf-8");
                                    System.out.println("status = " + status);
                                    carEdit.setCarStatus(status);
                                    break;
                                case "carType":         // 店铺状态
                                    String carType = fileItem.getString("utf-8");
                                    System.out.println("carType = " + carType);
                                    carEdit.setCarType(carType);
                                    break;
                                case "oriImgEdit":         // 店铺状态
                                    String oriImg = fileItem.getString("utf-8");
                                    System.out.println("oriImg = " + oriImg);
                                    carEdit.setCarPicture(oriImg);
                                    break;

//                             判断是否是第一次上传
                                case "isFirstUpload":   // 判断是否是第一次上传图片
                                    isFirstUpload = fileItem.getString("utf-8");
                                    System.err.println("lijiayu: isFirstUpload no attb = " + isFirstUpload);
                                    break;
//                            case "isFirstUpload2":   // 判断是否是第一次上传图片
//                                isFirstUpload2 = fileItem.getString("utf-8");
//                                System.err.println("lijiayu: isFirstUpload2 no attb = " + isFirstUpload2);
//                                break;
//                            case "isFirstUpload3":   // 判断是否是第一次上传图片
//                                isFirstUpload3 = fileItem.getString("utf-8");
//                                System.err.println("lijiayu: isFirstUpload3 no attb = " + isFirstUpload3);
//                                break;
                            }
                        } else { // 如果是文件就上传到 car-image 文件夹中
                            // 需要修改的地方：
                            // 1.UPLOAD_DIRECTORY userDirRelative
                            // 2.在jsp中添加判断是否是第一次上传
                            System.err.println("lijiayu: 这是一个文件 = " + fileItem.getFieldName());
                            // 得到文件的完整路径
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                            Date date = new Date();
                            String path = fileItem.getName();

                            // 得到去除路径的文件名
                            String fileName = path.substring(path.lastIndexOf("\\") + 1);

                            // 为了避免下载文件时出现中文传输参数乱码，
                            // 故将文件存储名设置为年月日+时分秒，当上传超过一个文件时完全可以考虑使用3位随机数。
                            String fileType;
                            if (!"".equals(fileName) && fileItem.getSize() != 0 && fileItem.getSize() <= MAX_FILE_SIZE) {
                                // 可以考虑添加随机数
//                            int b = (int) (Math.random() * 1000);

                                // 获取文件类型
                                fileType = fileName.substring(path.lastIndexOf("."));

                                // 文件存储名 包含日期时间 + 3位随机数
                                fileName = simpleDateFormat.format(date) + fileType;
                                System.out.println("fileName = " + fileName);
                                // carStoreDir文件夹 = <绝对路径>/CarRental/
                                String filePath = this.getServletContext().getRealPath("");
                                // userDirRelative 文件夹 = resources/carStore-image/<用户名> 上传到数据库的就是这个
                                String userDirRelative = UPLOAD_DIRECTORY_FOR_CARS + File.separator;
                                // carDir文件夹 = <绝对路径>/CarRental/resources/car-image
                                String carDirAbsolute = filePath + userDirRelative;
                                System.out.println("carStoreDirAbsolute = " + carDirAbsolute);


                                // 如果目录不存在则创建 最终存储目录 carStoreDirAbsolute
                                File uploadDir = new File(carDirAbsolute);
                                // 这里提醒一下
                                // 1.mkdir()只能创建一级目录，且父目录必须存在
                                // 2.mkdirs()可以直接创建多级目录
                                if (!uploadDir.exists()) {
                                    System.out.println("uploadDir.mkdirs() = " + uploadDir.mkdirs());
                                }
                                // 将文件保存在web目录的resources/car-image中
                                if ("carPicture".equals(fileItem.getFieldName())) {
                                    System.out.println("carPicture = " + fileItem.getName());
                                    try {
                                        boolean isFileDeleted = deleteFile(filePath + carEdit.getCarPicture());
                                        System.out.println("lijiayu file: delete file path======" + carDirAbsolute + fileName);
                                        if (isFileDeleted || Objects.equals(isFirstUpload, "true")) {
                                            fileItem.write(new File(carDirAbsolute, fileName));
//                                      同时也要将路径保存在数据库中
                                            System.out.println("lijiayu file: setCarPicture======" + carDirAbsolute);
                                            carEdit.setCarPicture(userDirRelative + fileName);
                                        }
                                    } catch (Exception e) {
                                        System.out.println("lijiayu: fileItem.write(new File(UPLOAD_DIRECTORY, fileName))======" + e);
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

                int result;
                result = carService.updateCarById(carEdit);
                System.out.println("lijiayuCarEdit FinalResult = " + result);
//        System.out.println("userIdNumber = " + user.getIdNumber());
                if (result > 0) {
                    System.out.println("result > 0");
                    // 修改成功，刷新页面
                    response.sendRedirect("/doCarStore?op=carManage&do=list&editSuccess=true");
                } else if (result == 0) {
                    response.getWriter().print("<script>alert('修改失败!');history.go(-1);</script>"); // history.go(-1) -1代表回退回去前面的页面
                } else if (result == -1) {
                    response.getWriter().print("<script>alert('修改失败!');history.go(-1);</script>");
                }
                break;
            }
            case "delete": {
                // 删除汽车信息，刷新页面，提示删除成功。
                int carId = Integer.parseInt(request.getParameter("carId"));
                Car carTemp = carService.getCarById(carId);
                int result = carService.deleteCarById(carId);
                String filePath = this.getServletContext().getRealPath("");
                boolean isFileDeleted = deleteFile(filePath + carTemp.getCarPicture());
                if (result > 0 && isFileDeleted)
                    response.sendRedirect(request.getContextPath() + "/doCarStore?op=carManage&do=list&deleteSuccess=true");
//            response.sendRedirect(request.getContextPath() + "/admin/doDept?op=list");
                else
                    response.getWriter().print("<script>alert('删除失败!');history.go(-1);</script>"); // history.go(-1) -1代表回退回去前面的页面

                break;
            }
        }
    }

    protected void carDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        CarService carService = new CarServiceImpl();
        String act = "";
        act = request.getParameter("do");
        if (act != null) {
            switch (act) {
                case "dispatch": {// 出车操作
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Order order = orderService.getOrderById(orderId);
                    if (order.getOrderStatus().equals(NamesConfig.TRADE_FINISHED) || order.getOrderStatus().equals(NamesConfig.TRADE_CLOSED) || order.getOrderStatus().equals(NamesConfig.TRADE_REFUND)) {
                        break;
                    } else {
                        order.setOrderStatus(NamesConfig.WAIT_BUYER_GET_CAR);
                        orderService.updateOrderStatusByOutTradeNo(order);
                    }
                    break;
                }
                case "cancel": {// 取消订单
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Order order = orderService.getOrderById(orderId);
                    if (order.getOrderStatus().equals(NamesConfig.TRADE_FINISHED) || order.getOrderStatus().equals(NamesConfig.TRADE_CLOSED) || order.getOrderStatus().equals(NamesConfig.WAIT_BUYER_GET_CAR)) {
                        break;
                    } else {
                        // 如果用户已支付，就退钱；或者还未刷新，处于待支付状态
                        if (order.getOrderStatus().equals(NamesConfig.TRADE_SUCCESS) || order.getOrderStatus().equals(NamesConfig.WAIT_BUYER_PAY)) {
                            AlipayTradeRefundResponse refundResponse = PayUtils.aliRefund(order.getOutTradeNo(), "", order.getTotalAmount(), NamesConfig.REFUND_REASON, "");
                            try {
                                if (refundResponse!=null) {
                                    if (refundResponse.getMsg().contains("0")) {
                                        response.getWriter().print("" +
                                                "<script>" +
                                                "alert('"+ refundResponse.getSubMsg() +"');" +
                                                "history.go(0);" +
                                                "</script>");
                                    } else if (refundResponse.getFundChange().equals("Y")) {
                                        Car car = carService.getCarById(order.getCarId());
                                        car.setCarStatus(NamesConfig.CAR_IN_SHOP);
                                        carService.updateCarStatusById(car);
                                        order.setOrderStatus(NamesConfig.TRADE_REFUND);
                                        orderService.updateOrderStatusByOutTradeNo(order);
                                    }
                                }
                            }
                            catch (Exception e){
                                    response.getWriter().print("" +
                                            "<script>" +
                                            "alert('"+ refundResponse.getSubMsg() +"');" +
                                            "history.go(0);" +
                                            "</script>");
                                }
                        } else if (order.getOrderStatus().equals(NamesConfig.TRADE_REFUND)) {
                            break;
                        } else {
                            // 如果还处于待支付状态，直接取消订单，并且将车辆状态修改为"在店内"
                            order.setOrderStatus(NamesConfig.TRADE_CLOSED);
                            orderService.updateOrderStatusByOutTradeNo(order);
                            Car car = carService.getCarById(order.getCarId());
                            car.setCarStatus(NamesConfig.CAR_IN_SHOP);
                            carService.updateCarStatusById(car);
                        }
                    }
                    break;
                }
                case "return": { // 还车
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    Order order = orderService.getOrderById(orderId);
                    if (order.getOrderStatus().equals(NamesConfig.TRADE_FINISHED) || order.getOrderStatus().equals(NamesConfig.TRADE_CLOSED) || order.getOrderStatus().equals(NamesConfig.TRADE_REFUND)) {
                        break;
                    } else {
                        order.setOrderStatus(NamesConfig.TRADE_FINISHED);
                        orderService.updateOrderStatusByOutTradeNo(order);
                        Car car = carService.getCarById(order.getCarId());
                        car.setCarStatus(NamesConfig.CAR_IN_SHOP);
                        carService.updateCarStatusById(car);
                    }
                    break;
                }
            }
        }
        carDispatchList(request, response); // 显示出车信息
    }

    /**
     * 显示出车信息页面，刷新页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void carDispatchList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();

        if (request.getSession().getAttribute("carStoreId") != null) {
            Integer carStoreId = (Integer) request.getSession().getAttribute("carStoreId");
            List<Order> orders = orderService.getAllOrderForCarStore(carStoreId);
            orders = (List<Order>) CommonUtils.getSpecificPage(0, orders, NamesConfig.ONE_PAGE_SIZE_SHORT);
            request.setAttribute("pageNow", 0);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsps/carStore/carStoreDispatch.jsp").forward(request, response);
//          response.sendRedirect("/jsps/carStore/carStoreDispatch.jsp");
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('请先登录!');window.location.href='/jsps/sign/sign-in.jsp'</script>");
        }
    }

    /**
     * 重定向到出车信息页面 无用
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void carDispatchListRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("carStoreId") != null) {
            response.sendRedirect("/jsps/carStore/carStoreDispatch.jsp");
        }
    }

    /**
     * 删除文件
     * @param filePath 绝对路径
     * @return 删除是否成功
     */
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
}
