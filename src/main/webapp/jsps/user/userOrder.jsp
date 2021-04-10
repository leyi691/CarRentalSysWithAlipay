<%@ page import="cn.edu.nbut.jerry.POJO.User" %>
<%@ page import="java.io.File" %>
<%@ page import="cn.edu.nbut.jerry.POJO.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="cn.edu.nbut.jerry.service.OrderService" %>
<%@ page import="cn.edu.nbut.jerry.service.impl.OrderServiceImpl" %>
<%@ page import="cn.edu.nbut.jerry.config.NamesConfig" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="cn.edu.nbut.jerry.Utils.CommonUtils" %><%--
  Created by IntelliJ IDEA.
  User: charlieli
  Date: 2021/2/8
  Time: 3:58 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="个人信息页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>个人信息</title>

    <!-- 新 Bootstrap5 核心 CSS 文件 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- Icon CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <!-- 新 Bootstrap5 核心 JS 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="<%=basePath%>/js/area.min.js"></script>
    <%--    <script src="<%=basePath%>/js/userHelp.min.js"></script>--%>
    <script type="text/javascript" src="<%=basePath%>/js/userHelp.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/utils.min.js"></script>
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

<%--    <link href="<%=basePath%>/css/index.css" rel="stylesheet">--%>
    <!-- Custom styles for this template -->
    <link href="<%=basePath%>/css/userIndex.css" rel="stylesheet">
</head>
<body>
<%
    if (session.getAttribute("userId") == null){
%>
<script>alert('请先登录!');window.location.href='<%=basePath%>sign-in.jsp'</script>
<%
    }
%>
<%
    try {
        String err = request.getParameter("err");
        if (err.equals("alipay")) {
            %>
        <script>alert('支付宝接口调用失败');</script>
<%
        }
    } catch (Exception ignored) {}
%>
<%--<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">--%>
<%--    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="<%=basePath%>/doIndex?op=list">汽车租赁系统</a>--%>
<%--    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>
<%--    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">--%>
<%--    <ul class="navbar-nav px-3">--%>
<%--        <li class="nav-item text-nowrap">--%>
<%--            <a class="nav-link" href="<%=basePath%>/doUser?op=signOut">登出</a>--%>
<%--        </li>--%>
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
                } else if (session.getAttribute("sysAdminId") != null){
                    showUserIndex = NamesConfig.HEADER_FORTH_SYSADMIN;
                    selfIndexLink = "/doSysAdmin?op=list";
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
<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="position-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<%=basePath%>/doUser?op=user">
                            <span data-feather="home"></span>
                            个人主页
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="<%=basePath%>/doUser?op=orderList">
                            <span data-feather="order"></span>
                            订单
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
<%
//    User user = (User) session.getAttribute("user");
//    String outTradeNo = null;
//    try {
//        outTradeNo = request.getParameter("outTradeNo");
//        System.out.println("userOrder.jsp: outTradeNo = " + outTradeNo);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">历史订单</h1>

                <!-- todo 搜索功能实现 -->
                <div class="btn-toolbar mb-2 mb-md-0">
                    <%--   使用form表单自我提交 我真是个天才！！！！！！！！   --%>
                    <form class="row" id="thisForm1" name="thisForm" method="post">
                    <div class="col-auto">
                        <label>
                            <input name="searchTxt" type="text" class="form-control" placeholder="按门店名搜索">
                        </label>
                    </div>
                    <div class="col-auto">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">查询</button>
                            <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">显示所有订单</button>
                        </div>
                    </div>
                    </form>
                    <div class="col-auto ms-4">
                        <%
                            try {
                                Order order = null;
                                order = (Order) request.getAttribute("order");
                                if (order != null){

                        %>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#showRentalCar">
                            查看您当前的预定信息
                        </button>
                        <%
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </div>
                </div>
            </div>
<%
    List<Order> orders = null;
    OrderService orderService = new OrderServiceImpl();
    String searchText = request.getParameter("searchTxt");
    String getPageNow = request.getParameter("pageNow");
    Integer userId = (Integer) request.getSession().getAttribute("userId");
    int pageNow = 0;
    if (searchText != null){
        orders = orderService.searchOrderLikeStoreNameForUser(searchText, userId);
    } else if (getPageNow != null) {
        pageNow = Integer.parseInt(getPageNow);
        orders = (List<Order>) CommonUtils.getSpecificPage(pageNow, orderService.getAllOrderByUserId(userId), NamesConfig.ONE_PAGE_SIZE_LONG);
    } else if (orders != null){
        orders = (List<Order>) request.getAttribute("orders");
    } else {
        orders = (List<Order>) CommonUtils.getSpecificPage(0, orderService.getAllOrderByUserId(userId), NamesConfig.ONE_PAGE_SIZE_LONG);
    }
%>
            <div class="table-responsive">
                <!-- <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas> -->
                <table class="table">
                    <thead>
                    <tr class="text-center">
                        <th>编号</th>
                        <th>车型</th>
                        <th>门店</th>
                        <th>租金</th>
                        <th>总金额</th>
                        <th>取车时间</th>
                        <th>还车时间</th>
                        <th>订单状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        System.out.println("userOrder.jsp: orders = " + orders);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        for (Order order : orders) {
                            System.out.println("userOrder.jsp: OrderId = " + order.getOrderId());
                    %>

                    <tr>
                        <%--   使用form表单自我提交 我真是个天才！！！！！！！！   --%>
                        <form id="thisForm<%=order.getOrderId()%>" name="thisForm" method="post">
                            <input type="hidden" name="orderOutTradeNo_Sel" value="<%=order.getOutTradeNo()%>">
                        </form>
                        <td class="text-center align-middle"><%=order.getOrderId()%></td>
                        <td class="text-center align-middle"><%=order.getBrand()+" | "+order.getBrandNumber()+" | "+order.getCarType()+" | "+order.getDisplacement()%></td>
                        <td class="text-center align-middle"><%=order.getStoreName()%></td>
                        <td class="text-center align-middle"><%=order.getRentalPrice()%></td>
                        <td class="text-center align-middle"><%=order.getTotalAmount()%></td>
                        <td class="text-center align-middle"><%=sdf.format(order.getRentalDate())%></td>
                        <td class="text-center align-middle"><%=sdf.format(order.getReturnDate())%></td>
                        <td class="text-center align-middle"><%=order.getOrderStatus()%></td>
                        <td class="text-center align-middle">
                            <%
                                if (order.getOrderStatus().equals(NamesConfig.WAIT_BUYER_PAY)){
                            %>
                            <form method="post" action="<%=basePath%>/doGoods?op=toPay">
                                <input type="hidden" name="orderId" value="<%=order.getOrderId()%>">
<%--                                <input type="hidden" name="orderId" value="<%=order.getOrderId()%>">--%>
                                <input type="submit" class="btn btn-sm btn-success mt-3" value="去付钱">
                            </form>
                            <%
                                } else {
                            %>
                            <a type="button" class="btn btn-sm btn-outline-primary" onclick="sel(<%=order.getOrderId()%>)">查看详情</a>
                            <%
                                }
                            %>
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
                    int next = pageNow >= NamesConfig.ONE_PAGE_SIZE_LONG ? NamesConfig.ONE_PAGE_SIZE_LONG : pageNow + 1;
                    if (pageNow != 0) {
                %>
                <a class="btn btn-primary" href="<%=basePath%>/jsps/user/userOrder.jsp?&pageNow=<%=front%>">上一页</a>
                <%
                    }
                    if (pageNow < orderService.getAllOrderByUserId(userId).size() / NamesConfig.ONE_PAGE_SIZE_LONG) {
                %>

                <a class="btn btn-primary" href="<%=basePath%>/jsps/user/userOrder.jsp?pageNow=<%=next%>">下一页</a>
                <%
                    }
                %>
            </div>

        </main>



    </div>
</div>
<%
    try {
        String outTradeNo_sel = request.getParameter("orderOutTradeNo_Sel");
        System.out.println("USER JSP: outTradeNo_sel = " + outTradeNo_sel);
        Order orderSel = orderService.getOrderByOutTradeNoForUser(outTradeNo_sel);
        System.out.println("USER JSP: orderSel = " + orderSel);
        if (outTradeNo_sel != null) {
%>
<!--===================================== 用户点击详情 模态框 ========================================-->
<div class="modal fade" id="showInfo" tabindex="-1" aria-labelledby="showInfo" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">

            <div class="modal-header">
                <h3 class="modal-title">订单详情：</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body row">

                <div class="col-3">
                    <img src="<%=orderSel.getCarPicture()%>"
                         style="

                         width: 15.5vw;
                         height: 8.6vw;
                         object-fit: cover"
                         alt="汽车图片">
                </div>

                <div class="col-9 row">
                    <div class="col-6">
                        <p>车型：<%=orderSel.getBrand()+" | "+orderSel.getBrandNumber()+" | "+orderSel.getCarType()%></p>
                    </div>

                    <div class="col-6">
                        <p>订单状态：<%=orderSel.getOrderStatus()%></p>
                    </div>

                    <div class="col-6">
                        <p>取车门店：<%=orderSel.getStoreName()%></p>
                    </div>

                    <div class="col-6">
                        <p>租金：<%=orderSel.getRentalPrice()%></p>
                    </div>

                    <div class="col-6">
                        <p>还车门店：<%=orderSel.getReturnStoreName()%></p>
                    </div>

                    <div class="col-6">
                        <p>总金额：<%=orderSel.getTotalAmount()%></p>
                    </div>

                    <div class="col-6">
                        <p>取车时间：<%=sdf.format(orderSel.getRentalDate())%></p>
                    </div>

                    <div class="col-6">
                        <p>还车时间：<%=sdf.format(orderSel.getReturnDate())%></p>
                    </div>

                    <div class="col-12">
                        <span class="text-danger">如有问题请拨打门店联系电话：<%=orderSel.getStoreContactNumber()%></span>
                    </div>
                </div>


            </div>


        </div>
    </div>
</div>
<script>
    showModel('showInfo');
</script>
<%
        }
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }
%>

<%
    Order order = null;
    try {
        order = (Order) request.getAttribute("order");
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (order != null) {

%>
<!--===================================== 用户从支付宝跳转回来 模态框 ========================================-->
<div class="modal fade" id="showRentalCar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel">您当前的预定信息如下：</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body row">
                <div class="col-6">
                    <p>取车时间：<%=order.getRentalDate()%></p>
                </div>
                <div class="col-6">
                    <p>还车时间：<%=order.getReturnDate()%></p>
                </div>
                <div class="col-6">
                    <p>车牌：<%=order.getBrand()%></p>
                </div>
                <div class="col-6">
                    <p>车型：<%=order.getBrandNumber()%></p>
                </div>
                <div class="col-6">
                    <p>取车门店：<%=order.getStoreName()%></p>
                </div>
                <div class="col-6">
                    <p>还车门店：<%=order.getReturnStoreName()%></p>
                </div>
            </div>

            <div class="modal-footer justify-content-between">
                <p class="text-danger">友情提示：请合理安排您的用车时间</p>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">好的</button>
            </div>

        </div>
    </div>
</div>
<%
    }
%>
<!--===================================== Modal 支持链接模态框 =====================================-->
<div class="modal fade" id="supportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">需要帮助？</h3>
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

<%--&lt;%&ndash;各种提醒（alert）&ndash;%&gt;--%>
<%--<!-- 提示用户修改成功！ -->--%>
<%--<div id="success" class="alert alert-success fade" role="alert"--%>
<%--     style="width: auto; position: fixed; top: 80%; left: 55%; margin-left: -80px;">--%>
<%--    <strong>修改成功！</strong>--%>
<%--</div>--%>

<%--&lt;%&ndash;条件1&ndash;%&gt;--%>
<%--<!-- 提示用户修改成功！ -->--%>
<%--<%--%>
<%--    try{--%>
<%--        String flag = (String) request.getAttribute("isEditSuccess");--%>
<%--        System.out.println("request.getAttribute(\"isEditSuccess\") = " + flag);--%>
<%--        if (flag.equals("true")) {--%>

<%--%>--%>
<%--            <script type="text/javascript">--%>
<%--                $("#success").addClass("show"); // 修改成功。--%>
<%--                window.setTimeout(function(){$("#success").removeClass("show");}, 2000);//显示的时间--%>
<%--            </script>--%>
<%--<%--%>
<%--        }--%>
<%--    } catch (Exception e) {--%>
<%--        e.printStackTrace();--%>
<%--    }--%>
<%--%>--%>
<%--<!-- TODO 判断如果用户有预定的车辆就显示模态框 -->--%>
<%--<%--%>
<%--    if (outTradeNo != null) {--%>

<%--%>      <script>--%>
<%--            // 自动弹出modal框--%>
<%--            showModel('showRentalCar');--%>
<%--        </script>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>



<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>

</body>
</html>
