<%--
  Created by IntelliJ IDEA.
  User: charlieli
  Date: 2021/2/23
  Time: 7:24 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.File" %>
<%@ page import="cn.edu.nbut.jerry.POJO.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="cn.edu.nbut.jerry.service.CarService" %>
<%@ page import="cn.edu.nbut.jerry.service.impl.CarServiceImpl" %>
<%@ page import="cn.edu.nbut.jerry.config.NamesConfig" %>
<%@ page import="cn.edu.nbut.jerry.Utils.CommonUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="汽车管理页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>汽车管理页面</title>

    <!-- 新 Bootstrap5 核心 CSS 文件 -->
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <!-- Icon CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <!-- 新 Bootstrap5 核心 JS 文件 -->
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<%--    <script src="<%=basePath%>/js/userHelp.min.js"></script>--%>
    <script type="text/javascript" src="<%=basePath%>/js/carStoreHelp.min.js"></script>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
        img{
            width: 100%;
            height: 20vw;
            object-fit: cover;
        }
        .listImg {
            width: 6vw;
            height: 6vw;
            object-fit: cover;
        }
        .bg-img{
            /* 加载背景图 */
            /*background-image: url(images/background-photo.jpg);*/

            /* 背景图垂直、水平均居中 */
            background-position: center center;

            /* 背景图不平铺 */
            background-repeat: no-repeat;

            /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
            /* background-attachment: fixed; */

            /* 让背景图基于容器大小伸缩 */
            background-size: cover;

            /* 设置背景颜色，背景图加载过程中会显示背景色 */
            background-color: #464646;
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="<%=basePath%>/css/userIndex.css" rel="stylesheet">
</head>
<body>

<%--<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">--%>
<%--    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="<%=basePath%>/doIndex?op=list">汽车租赁系统</a>--%>
<%--    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>
<%--    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">--%>
<%--    <ul class="navbar-nav px-3">--%>
<%--        <%--%>
<%--            if (session.getAttribute("isAlreadyLogin") == null){--%>
<%--        %>--%>
<%--        <script>alert('请先登录!');window.location.href='<%=basePath%>jsps/sign/sign-in.jsp'</script>--%>
<%--        <%--%>
<%--        } else {--%>
<%--        %>--%>
<%--        <li class="nav-item text-nowrap">--%>
<%--            <a class="nav-link" href="<%=basePath%>/doUser?op=signOut">登出</a>--%>
<%--        </li>--%>
<%--        <%--%>
<%--            }--%>
<%--        %>--%>
<%--    </ul>--%>
<%--</header>--%>
<header class="site-header sticky-top py-1">
    <nav class="container d-flex flex-column flex-md-row justify-content-between">
        <a class="py-2 d-none d-md-inline-block navbar-toggler" href="<%=basePath%>doIndex?op=list">汽车租赁系统</a>
        <!-- <a class="py-2" href="#" aria-label="Product">
        <svg xmlns="http://www.w3.org/2000/svg" p-id="2186" data-darkreader-inline-fill="" width="24" height="24"><path d="M278.357333 102.4h467.285334c45.841067 0 86.254933 29.764267 99.328 73.147733L921.6 429.568c2.901333 9.557333 7.168 18.670933 12.731733 26.999467l38.5024 57.856c14.6432 22.016 20.138667 48.776533 15.36 74.717866l-49.3568 266.171734A81.578667 81.578667 0 0 1 858.350933 921.6h-13.277866a104.004267 104.004267 0 0 1-85.742934-44.885333L719.803733 819.2H305.425067l-38.570667 56.968533A103.970133 103.970133 0 0 1 180.736 921.6h-13.994667a82.944 82.944 0 0 1-81.8176-67.3792L35.84 589.892267a101.376 101.376 0 0 1 15.291733-74.683734l38.536534-57.924266c5.5296-8.362667 9.8304-17.476267 12.6976-27.0336l76.663466-254.634667A103.492267 103.492267 0 0 1 278.357333 102.4z m0 68.266667c-15.2576 0-28.740267 9.898667-33.109333 24.405333l-76.663467 254.634667c-4.778667 15.9744-11.946667 31.1296-21.162666 45.056L108.885333 552.6528a33.792 33.792 0 0 0-5.12 24.917333l49.117867 264.328534a14.062933 14.062933 0 0 0 13.858133 11.400533h13.994667c11.502933 0 22.289067-5.666133 28.672-15.1552l38.6048-56.9344A69.3248 69.3248 0 0 1 305.425067 750.933333h414.378666c22.903467 0 44.3392 11.195733 57.173334 29.934934l39.5264 57.514666c6.417067 9.352533 17.134933 14.9504 28.5696 14.9504h13.312c6.144 0 11.400533-4.334933 12.4928-10.308266l49.322666-266.205867a33.792 33.792 0 0 0-5.12-24.917333l-38.468266-57.821867a169.745067 169.745067 0 0 1-21.230934-45.021867l-76.5952-254.020266a34.474667 34.474667 0 0 0-33.109333-24.3712H278.391467z" fill="#e6e6e6" p-id="2187" data-darkreader-inline-fill="" style="--darkreader-inline-fill:#696861;"></path><path d="M343.893333 485.410133H327.68a34.133333 34.133333 0 1 1 0-68.266666h156.5696a119.534933 119.534933 0 0 1 233.984 34.133333 119.466667 119.466667 0 0 1-233.984 34.133333h-72.0896v21.504c0 16.7936-15.291733 30.378667-34.133333 30.378667s-34.133333-13.585067-34.133334-30.378667v-21.504z m203.6736-34.133333a51.2 51.2 0 1 0 102.4 0 51.2 51.2 0 0 0-102.4 0z" fill="#e6e6e6" p-id="2188" data-darkreader-inline-fill="" style="--darkreader-inline-fill:#696861;"></path></svg>
        </a> -->
        <%
            // 查看用户是否已经登录，如果登录就显示"退出"按钮，否则就显示"登录 / 注册"
            // 当然按钮功能也需要修改
            String showText, signInOrOutLink;
            String showUserIndex = "", selfIndexLink = "#";
            if (session.getAttribute("isAlreadyLogin") == null
                    && session.getAttribute("isAlreadyLoginUser") == null
                    && session.getAttribute("sysAdminId") == null) {
                showText = NamesConfig.HEADER_FIFTH_SIGNUP_IN;
                signInOrOutLink = basePath + "jsps/sign/sign-in.jsp";
            } else {
                showText = NamesConfig.HEADER_FIFTH_EXIT;
                signInOrOutLink = basePath + "/doUser?op=signOut";
                if (session.getAttribute("userId") != null) {
                    showUserIndex = NamesConfig.HEADER_FORTH_USER;
                    selfIndexLink = "/doUser?op=user";
                } else if (session.getAttribute("carStoreId") != null) {
                    showUserIndex = NamesConfig.HEADER_FORTH_STORE;
                    selfIndexLink = "/doCarStore?op=list";
                }
            }
        %>
        <a class="py-2 d-none d-md-inline-block" href="<%=basePath%>/doIndex?op=list"><%=NamesConfig.HEADER_FIRST%></a>
        <a class="py-2 d-none d-md-inline-block" href="<%=basePath%>/doCarRentalList?op=allType"><%=NamesConfig.HEADER_SECOND%></a>
        <%=NamesConfig.HEADER_SUPPORT_LINK_MODAL_BTN%>
        <a class="py-2 d-none d-md-inline-block" href="<%=selfIndexLink%>"><%=showUserIndex%></a>
        <a class="py-2 d-none d-md-inline-block" href="#"></a>
        <a class="py-2 d-none d-md-inline-block" href="<%=signInOrOutLink%>"><%=showText%></a>
        <a class="py-2 d-none d-md-inline-block" href="#"></a>
    </nav>
</header>
<%
//     获取servlet传来的信息
    Integer carStoreIdSession = (Integer) (session.getAttribute("carStoreId"));    // 这里获取session中的carStoreId内容
    System.out.println("lijiayuJSP carStoreId:" + carStoreIdSession);
    Integer carStoreId = (Integer) request.getAttribute("carStoreId");
    List<Car> cars = null;
    CarService carService = new CarServiceImpl();
    String searchText = request.getParameter("searchTxt");
    String getPageNow = request.getParameter("pageNow");
    int pageNow = 0;
    if (searchText != null) {
        cars = carService.searchCarLikeBrand(searchText);
    } else if (getPageNow != null) {
        pageNow = Integer.parseInt(getPageNow);
        cars = (List<Car>) CommonUtils.getSpecificPage(pageNow, carService.getAllCarsByStoreId(carStoreIdSession), NamesConfig.ONE_PAGE_SIZE_SHORT);
    } else {
        cars = (List<Car>) request.getAttribute("cars");
    }
%>
<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="position-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item">

                        <a class="nav-link" aria-current="page" href="<%=basePath%>/doCarStore?op=list">
                            <%--TODO 各个链接的图标--%>
                            营业门店管理员主页
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="<%=basePath%>/doCarStore?op=carManage&do=list">
                            <span data-feather="file"></span>
                            车辆管理
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=basePath%>/doCarStore?op=<%=NamesConfig.CAR_STORE_DISPATCH_DO%>">
                            <span data-feather="shopping-cart"></span>
                            订单
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h3">车辆信息</h1>
                <div class="btn-group me-3">



                    <form class="row" id="thisForm1" name="thisForm" method="post">
<%--                        <div class="col-auto">--%>
<%--                            <label>--%>

<%--                            </label>--%>
<%--                        </div>--%>

                        <div class="col-auto">
                            <div class="input-group" role="group">
                                <label>
                                    <input name="searchTxt" type="text" class="form-control" placeholder="按车辆品牌搜索">
                                </label>
                                <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">查询</button>
                                <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">显示所有</button>
                            </div>
                        </div>
                        <div class="col-auto">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#staticBackdrop">
                                添加车辆
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr class="text-center">
                        <th scope="col">照片</th>
                        <th scope="col">车牌号</th>
                        <th scope="col">品牌</th>
                        <th scope="col">型号</th>
                        <th scope="col">汽车类型</th>
                        <th scope="col">租赁价格</th>
                        <th scope="col">排量</th>
                        <th scope="col">厢数</th>
                        <th scope="col">座数</th>
                        <th scope="col">发动机号</th>
                        <th scope="col">车架号</th>
                        <th scope="col">购入日期</th>
                        <th scope="col">状态</th>
                        <th scope="col">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        // 循环输出汽车信息
                        for (Car car : cars) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            %>
                    <tr>
                        <th scope="row" style="width: 6vw;">
                            <%
                                String carImg;
                                // 这里user.getPortrait() == null 而不能使用 user.getPortrait().equals("")
                                // 因为数据库里面 <null> 和 "" 不一样
                                if (car.getCarPicture() == null || car.getCarPicture().equals("") || car.getCarPicture().equals("null")) {
                                    carImg = basePath + File.separator + "resources" + File.separator
                                            + "brand-image" + File.separator + "lack_of_car_img.jpeg";
                            %>
                            <input type="hidden" value="true" name="isFirstUpload">
                            <%
                                } else {
                                    carImg = basePath + car.getCarPicture();
                                }
                            %>
                            <img src="<%=carImg%>" class="listImg" alt="汽车图片">
                        </th>
                        <td class="text-center align-middle"><%=car.getLicensePlateNumber()%></td>
                        <td class="text-center align-middle"><%=car.getBrand()%></td>
                        <td class="text-center align-middle"><%=car.getBrandNumber()%></td>
                        <td class="text-center align-middle"><%=car.getCarType()%></td>
                        <td class="text-center align-middle"><%=car.getRentalPrice()%></td>
                        <td class="text-center align-middle"><%=car.getDisplacement()%></td>
                        <td class="text-center align-middle"><%=car.getCars()%></td>
                        <td class="text-center align-middle"><%=car.getSeats()%></td>
                        <td class="text-center align-middle"><%=car.getEngineNumber()%></td>
                        <td class="text-center align-middle"><%=car.getFrameNumber()%></td>
                        <td class="text-center align-middle"><%=simpleDateFormat.format(car.getPurchaseDate())%></td>
                        <td class="text-center align-middle"><%=car.getCarStatus()%></td>
                        <td class="text-center align-middle">
<%--                            使用<a> 标签的id 来暂时保存carId 我真是个天才！！！！！！！！                       --%>
                        <form id="thisForm<%=car.getCarId()%>" name="thisForm" method="post">
                            <input type="hidden" name="toEditCarId" value="<%=car.getCarId()%>">
                        </form>
                        <a type="button" class="btn btn-outline-primary" onclick="sel(<%=car.getCarId()%>)">修改</a>
                        <a href="<%=basePath%>/doCarStore?op=carManage&do=delete&carId=<%=car.getCarId()%>" class="btn btn-outline-danger">删除</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>

            <div class="btn-group float-end mb-3">
                <%
                    // 上一页
                    int front = pageNow<=0 ? 0:pageNow-1;
                    // 下一页
                    int next = pageNow >= NamesConfig.ONE_PAGE_SIZE_SHORT ? NamesConfig.ONE_PAGE_SIZE_SHORT : pageNow + 1;
                    if (pageNow != 0) {
                %>
                <a class="btn btn-primary" href="<%=basePath%>/jsps/carStore/carStoreDispatch.jsp?&pageNow=<%=front%>">上一页</a>
                <%
                    }
                    if (pageNow < carService.getAllCarsByStoreId(carStoreIdSession).size() / 10) {
                %>

                <a class="btn btn-primary" href="<%=basePath%>/jsps/carStore/carStoreDispatch.jsp?pageNow=<%=next%>">下一页</a>
                <%
                    }
                %>
            </div>

        </main>
    </div>
</div>
<!-- 按钮：用于打开模态框 修改车辆信息 -->
<!-- Button trigger modal -->
<%
    // TODO 在编辑完成之后，记得将 editCarId 重置成 -1
    try {
        int editCarId = -1;
        editCarId = Integer.parseInt(request.getParameter("toEditCarId"));
        Car carEdit = carService.getCarById(editCarId);
        if (editCarId != -1) {
            System.out.println("lijiayuJSP:"+editCarId);
%>
<!-- Modal 修改车辆信息-->
<div class="modal fade" id="staticBackdropEdit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabelEdit">修改车辆信息</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="form-control" action="<%=basePath%>/doCarStore?op=carManage&do=edit" method="POST" enctype="multipart/form-data">
                    <div class="container">
                        <div class="row">
                            <input type="hidden" name="carStoreId" value="<%=carStoreId%>">
                            <input type="hidden" name="carId" value="<%=carEdit.getCarId()%>">
                            <%
                                // 如果用户第一次登录没有初始头像，则给他一个默认的。
                                String carImg;
                                // 这里user.getPortrait() == null 而不能使用 user.getPortrait().equals("")
                                // 因为数据库里面 <null> 和 "" 不一样
                                if (carEdit.getCarPicture() == null || carEdit.getCarPicture().equals("") || carEdit.getCarPicture().equals("null")) {

                            %>
                            <input type="hidden" value="true" name="isFirstUpload">
                            <%
                                }
                            %>
                            <input type="hidden" value="<%=carEdit.getCarPicture()%>" name="oriImgEdit">
                            <div class="col-6">
                                <label for="carPictureEdit"><i>*</i>车辆照片</label>
                                <input type="file" class="form-control"
                                       id="carPictureEdit" name="carPicture">
                            </div>

                            <div class="col-6">
                                <label for="licensePlateNumberEdit"><i>*</i>车牌号</label>
                                <input type="text" class="form-control"
                                       id="licensePlateNumberEdit" name="licensePlateNumber" required
                                       value="<%=carEdit.getLicensePlateNumber()%>">
                            </div>

                            <div class="col-6">
                                <label for="brandEdit"><i>*</i>品牌</label>
                                <input type="text" class="form-control"
                                       id="brandEdit" name="brand" required
                                       value="<%=carEdit.getBrand()%>">
                            </div>

                            <div class="col-6">
                                <label for="brandNumberEdit"><i>*</i>型号</label>
                                <input type="text" class="form-control"
                                       id="brandNumberEdit" name="brandNumber" required
                                       value="<%=carEdit.getBrandNumber()%>">
                            </div>

                            <div class="col-6">
                                <label for="rentalPriceEdit"><i>*</i>租赁价格</label>
                                <input type="text" class="form-control"
                                       id="rentalPriceEdit" name="rentalPrice" required
                                       value="<%=carEdit.getRentalPrice()%>">
                            </div>

                            <div class="col-6">
                                <label for="displacementEdit"><i>*</i>排量</label>
                                <input type="text" class="form-control"
                                       id="displacementEdit" name="displacement" required
                                       value="<%=carEdit.getDisplacement()%>">
                            </div>

                            <div class="col-6">
                                <label for="carsEdit"><i>*</i>厢数</label>
                                <input type="text" class="form-control"
                                       id="carsEdit" name="cars" required
                                       value="<%=carEdit.getCars()%>">
                            </div>

                            <div class="col-6">
                                <label for="seatsEdit"><i>*</i>座数</label>
                                <input type="text" class="form-control"
                                       id="seatsEdit" name="seats" required
                                       value="<%=carEdit.getSeats()%>">
                            </div>

                            <div class="col-6">
                                <label for="engineNumberEdit"><i>*</i>发动机号</label>
                                <input type="text" class="form-control"
                                       id="engineNumberEdit" name="engineNumber" required
                                       value="<%=carEdit.getEngineNumber()%>">
                            </div>

                            <div class="col-6">
                                <label for="frameNumberEdit"><i>*</i>车架号</label>
                                <input type="text" class="form-control"
                                       id="frameNumberEdit" name="frameNumber" required
                                       value="<%=carEdit.getFrameNumber()%>">
                            </div>

                            <div class="col-6">
                                <label for="purchaseDateEdit"><i>*</i>购入日期</label>
                                <input type="date" class="form-control"
                                       id="purchaseDateEdit" name="purchaseDate" required
                                    <%
                                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                        String date = simpleDateFormat.format(carEdit.getPurchaseDate());
                                                   %>
                                       value="<%=date%>">
                            </div>

                            <div class="col-6 mt-3">
                                <p class="d-inline"><i>*</i>车辆状态：</p>
                                <input class="form-check-input" type="radio" name="status" id="availableEdit" value="在店内" required>
                                <label class="form-check-label" for="availableEdit">
                                    在店内
                                </label>
                                <input class="form-check-input" type="radio" name="status" id="rentedEdit" value="已租出">
                                <label class="form-check-label" for="rentedEdit">
                                    已租出
                                </label>
                                <input class="form-check-input" type="radio" name="status" id="maintainEdit" value="维修中">
                                <label class="form-check-label" for="maintainEdit">
                                    维修中
                                </label>
                                <!-- <input class="form-check-input" type="radio" name="status" id="maintain"> -->
                                <!-- <label class="form-check-label text-danger" for="maintain">
                                报废
                                </label> -->
                                <script type="text/javascript">carStatusSelectInit("<%=carEdit.getCarStatus()%>")</script>
                                <br>
                                <p class="d-inline"><i>*</i>汽车类型：</p>
                                <input class="form-check-input" type="radio" name="carType" id="offRoadEdit" value="越野车" required>
                                <label class="form-check-label" for="offRoadEdit">
                                    越野车
                                </label>
                                <input class="form-check-input" type="radio" name="carType" value="小轿车" id="normalCarEdit">
                                <label class="form-check-label" for="normalCarEdit">
                                    小轿车
                                </label>
                                <input class="form-check-input" type="radio" name="carType" value="跑车" id="sportCarEdit">
                                <label class="form-check-label" for="sportCarEdit">
                                    跑车
                                </label>
                                <input class="form-check-input" type="radio" name="carType" value="超级跑车" id="superCarEdit">
                                <label class="form-check-label" for="superCarEdit">
                                    超级跑车
                                </label>
                                <!-- <input class="form-check-input" type="radio" name="status" id="maintain"> -->
                                <!-- <label class="form-check-label text-danger" for="maintain">
                                报废
                                </label> -->
                                <script type="text/javascript">carTypeSelectInit("<%=carEdit.getCarType()%>")</script>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    showModel()
</script>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!-- Modal 新增车辆信息-->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">新增车辆</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="form-control" action="<%=basePath%>/doCarStore?op=carManage&do=insert" method="POST" enctype="multipart/form-data">
                    <div class="container">
                        <div class="row">
                            <input type="hidden" name="carStoreId" value="<%=carStoreId%>">
                            <div class="col-6">
                                <label for="carPicture"><i>*</i>车辆照片</label>
                                <input type="file" class="form-control"
                                       id="carPicture" name="carPicture" required>
                            </div>

                            <div class="col-6">
                                <label for="licensePlateNumber"><i>*</i>车牌号</label>
                                <input type="text" class="form-control"
                                       id="licensePlateNumber" name="licensePlateNumber" required>
                            </div>

                            <div class="col-6">
                                <label for="brand"><i>*</i>品牌</label>
                                <input type="text" class="form-control"
                                       id="brand" name="brand" required>
                            </div>

                            <div class="col-6">
                                <label for="brandNumber"><i>*</i>型号</label>
                                <input type="text" class="form-control"
                                       id="brandNumber" name="brandNumber" required>
                            </div>




                            <div class="col-6">
                                <label for="rentalPrice"><i>*</i>租赁价格</label>
                                <input type="text" class="form-control"
                                       id="rentalPrice" name="rentalPrice" required>
                            </div>

                            <div class="col-6">
                                <label for="displacement"><i>*</i>排量</label>
                                <input type="text" class="form-control"
                                       id="displacement" name="displacement" required>
                            </div>

                            <div class="col-6">
                                <label for="cars"><i>*</i>厢数</label>
                                <input type="text" class="form-control"
                                       id="cars" name="cars" required>
                            </div>

                            <div class="col-6">
                                <label for="seats"><i>*</i>座数</label>
                                <input type="text" class="form-control"
                                       id="seats" name="seats" required>
                            </div>

                            <div class="col-6">
                                <label for="engineNumber"><i>*</i>发动机号</label>
                                <input type="text" class="form-control"
                                       id="engineNumber" name="engineNumber" required>
                            </div>

                            <div class="col-6">
                                <label for="frameNumber"><i>*</i>车架号</label>
                                <input type="text" class="form-control"
                                       id="frameNumber" name="frameNumber" required>
                            </div>

                            <div class="col-6">
                                <label for="purchaseDate"><i>*</i>购入日期</label>
                                <input type="date" class="form-control"
                                       id="purchaseDate" name="purchaseDate" required>
                            </div>

                            <div class="col-6 mt-3">
                                <p class="d-inline"><i>*</i>车辆状态：</p>
                                <input class="form-check-input" type="radio" name="status" id="available" value="在店内" required>
                                <label class="form-check-label" for="available">
                                    在店内
                                </label>
                                <input class="form-check-input" type="radio" name="status" id="rented" value="已租出">
                                <label class="form-check-label" for="rented">
                                    已租出
                                </label>
                                <input class="form-check-input" type="radio" name="status" id="maintain" value="维修中">
                                <label class="form-check-label" for="maintain">
                                    维修中
                                </label>
                                <!-- <input class="form-check-input" type="radio" name="status" id="maintain"> -->
                                <!-- <label class="form-check-label text-danger" for="maintain">
                                报废
                                </label> -->
                                <br>
                                <p class="d-inline"><i>*</i>汽车类型：</p>
                                <input class="form-check-input" type="radio" name="carType" id="offRoad" value="越野车" required>
                                <label class="form-check-label" for="offRoad">
                                    越野车
                                </label>
                                <input class="form-check-input" type="radio" name="carType" value="小轿车" id="normalCar">
                                <label class="form-check-label" for="normalCar">
                                    小轿车
                                </label>
                                <input class="form-check-input" type="radio" name="carType" value="跑车" id="sportCar">
                                <label class="form-check-label" for="sportCar">
                                    跑车
                                </label>
                                <input class="form-check-input" type="radio" name="carType" value="超级跑车" id="superCar">
                                <label class="form-check-label" for="superCar">
                                    超级跑车
                                </label>
                                <!-- <input class="form-check-input" type="radio" name="status" id="maintain"> -->
                                <!-- <label class="form-check-label text-danger" for="maintain">
                                报废
                                </label> -->
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%-------------------------------各种提醒（alert）---------------------------------------------------------------------%>
<!-- Modal 支持链接模态框 -->
<div class="modal fade" id="supportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel">需要帮助？</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5 class="d-inline">欢迎致电：</h5>
                <h5 class="d-inline text-danger">400-888-8888</h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">好的</button>
            </div>
        </div>
    </div>
</div>
<!-- 提示用户修改成功！ -->
<div id="editSuccess" class="alert alert-success fade" role="alert"
     style="width: auto; position: fixed; top: 80%; left: 55%; margin-left: -80px;">
    <strong>修改成功！</strong>
</div>
<!-- 提示用户新增成功！ -->
<div id="insertSuccess" class="alert alert-success fade" role="alert"
     style="width: auto; position: fixed; top: 80%; left: 55%; margin-left: -80px;">
    <strong>新增成功！</strong>
</div>
<!-- 提示用户删除成功！ -->
<div id="deleteSuccess" class="alert alert-success fade" role="alert"
     style="width: auto; position: fixed; top: 80%; left: 55%; margin-left: -80px;">
    <strong>删除成功！</strong>
</div>
<%--条件1--%>
<!-- 提示用户修改成功！ -->
<%
    try {
//        String isEditSuccess = (String) request.getAttribute("isEditSuccess");   // 容易报空指针异常，出异常也不报，直接空白页面
        String isEditSuccess = request.getParameter("editSuccess");
        String isInsertSuccess = request.getParameter("insertSuccess");
        String isDeleteSuccess = request.getParameter("deleteSuccess");
        if (isEditSuccess.equals("true")) {
%>
<script type="text/javascript">
    $("#editSuccess").addClass("show"); // 修改成功。
    window.setTimeout(function(){$("#editSuccess").removeClass("show");},1500);//显示的时间
</script>
<%
        }
        if (isInsertSuccess.equals("true")) {
%>
<script type="text/javascript">
    $("#insertSuccess").addClass("show"); // 新增成功。
    window.setTimeout(function(){$("#insertSuccess").removeClass("show");},1500);//显示的时间
</script>
<%
        }
        if (isDeleteSuccess.equals("true")){
%>
<script type="text/javascript">
    $("#deleteSuccess").addClass("show"); // 删除成功。
    window.setTimeout(function(){$("#deleteSuccess").removeClass("show");},1500);//显示的时间
</script>
<%
        }
    }catch (Exception e) {
        System.err.println("lijiayuJSP" + e);
    }
%>







<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>

</body>
</html>
