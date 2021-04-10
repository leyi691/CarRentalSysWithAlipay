<%@ page import="java.util.List" %>
<%@ page import="cn.edu.nbut.jerry.POJO.Order" %>
<%@ page import="cn.edu.nbut.jerry.config.NamesConfig" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="cn.edu.nbut.jerry.service.OrderService" %>
<%@ page import="cn.edu.nbut.jerry.service.impl.OrderServiceImpl" %>
<%@ page import="cn.edu.nbut.jerry.Utils.CommonUtils" %><%--
  Created by IntelliJ IDEA.
  User: charlieli
  Date: 2021/3/7
  Time: 9:17 下午
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
    <meta name="description" content="门店订单管理页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>门店订单管理页面</title>

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
<%--    <script type="text/javascript" src="<%=basePath%>/js/carStoreHelp.min.js"></script>--%>
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
    Integer carStoreId = (Integer) (session.getAttribute("carStoreId"));    // 这里获取session中的carStoreId内容
    System.out.println("lijiayuJSP carStoreId:" + carStoreId);
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
                        <a class="nav-link" href="<%=basePath%>/doCarStore?op=carManage&do=list">
                            <span data-feather="file"></span>
                            车辆管理
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="<%=basePath%>/doCarStore?op=<%=NamesConfig.CAR_STORE_DISPATCH_DO%>">
                            <span data-feather="shopping-cart"></span>
                            订单
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h3">订单信息</h1>
                <!-- todo 搜索功能实现 -->
                <div class="btn-toolbar mb-2 mb-md-0">
                    <%--   使用form表单自我提交 我真是个天才！！！！！！！！   --%>
                    <form class="row" id="thisForm1" name="thisForm" method="post">
                        <div class="col-auto">
                            <div class="input-group" role="group">
<%--                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"--%>
<%--                                        id="dropDownSearchType"--%>
<%--                                        data-bs-toggle="dropdown" aria-expanded="false">--%>
<%--                                    搜索类型--%>
<%--                                </button>--%>

<%--                                <ul class="dropdown-menu" aria-labelledby="dropDownSearchType">--%>
<%--                                    <li>--%>
<%--                                        <a class="dropdown-item" href="#">--%>
<%--                                            <%=NamesConfig.SEARCH_TYPE_CAR_BRAND%>--%>
<%--                                            <input name="searchType" type="hidden" value="<%=NamesConfig.SEARCH_TYPE_CAR_BRAND%>">--%>
<%--                                        </a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a class="dropdown-item" href="#">--%>
<%--                                            <%=NamesConfig.SEARCH_TYPE_ORDER_STATUE%>--%>
<%--                                            <input type="hidden" value="<%=NamesConfig.SEARCH_TYPE_ORDER_STATUE%>">--%>
<%--                                        </a>--%>
<%--                                    </li>--%>

<%--                                    <li>--%>
<%--                                        <a class="dropdown-item" href="#">--%>
<%--                                            <%=NamesConfig.SEARCH_TYPE_CAR_LICENSE_NO%>--%>
<%--                                            <input type="hidden" value="<%=NamesConfig.SEARCH_TYPE_CAR_LICENSE_NO%>">--%>
<%--                                        </a>--%>
<%--                                    </li>--%>
<%--                                </ul>--%>

                                <label>
                                    <input name="searchTxt" type="text" class="form-control" placeholder="<%=NamesConfig.SEARCH_TYPE_ORDER_STATUE%>">
                                </label>

                                <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">查询</button>
                                <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">显示所有</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
<%
    List<Order> orders = null;
    OrderService orderService = new OrderServiceImpl();
    String searchTxt = request.getParameter("searchTxt");
    String getPageNow = request.getParameter("pageNow");
    int pageNow = 0;
    if (searchTxt != null && getPageNow != null) {
        pageNow = Integer.parseInt(getPageNow);
        orders = (List<Order>) CommonUtils.getSpecificPage(pageNow, orderService.searchOrderLikeStoreNameForUser(searchTxt, carStoreId), NamesConfig.ONE_PAGE_SIZE_SHORT);
    } else if (getPageNow != null) {
        pageNow = Integer.parseInt(getPageNow);
        orders = (List<Order>) CommonUtils.getSpecificPage(pageNow, orderService.getAllOrderForCarStore(carStoreId), NamesConfig.ONE_PAGE_SIZE_SHORT);
    } else {
        orders = (List<Order>) request.getAttribute("orders");
    }
%>
<%--            table 表单            --%>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr class="text-center">
                        <th scope="col">编号</th>
                        <th scope="col">照片</th>
                        <th scope="col">车牌号</th>
                        <th scope="col">品牌</th>
                        <th scope="col">型号</th>
                        <th scope="col">租赁用户名</th>
                        <th scope="col">租赁用户昵称</th>
                        <th scope="col">租赁价格</th>
                        <th scope="col">总金额</th>
                        <th scope="col">租车日期</th>
                        <th scope="col">还车日期</th>
                        <th scope="col">订单状态</th>
                        <th scope="col">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (orders!=null) {
                            for (Order order : orders){
                    %>
                    <tr>
                        <td class="text-center align-middle"><%=order.getOrderId()%></td>
                        <th scope="row" style="width: 6vw;">
                            <img src="<%=order.getCarPicture()%>" style="width: 6vw; height: 6vw; object-fit: cover;">
                        </th>
                        <td class="text-center align-middle"><%=order.getLicensePlateNumber()%></td>
                        <td class="text-center align-middle"><%=order.getBrand()%></td>
                        <td class="text-center align-middle"><%=order.getBrandNumber()%></td>
                        <td class="text-center align-middle"><%=order.getUsername()%></td>
                        <td class="text-center align-middle"><%=order.getNickname()%></td>
                        <td class="text-center align-middle"><%=order.getRentalPrice()%> 元/天</td>
                        <td class="text-center align-middle"><%=order.getTotalAmount()%></td>
                        <td class="text-center align-middle"><%=sdf.format(order.getRentalDate())%></td>
                        <td class="text-center align-middle"><%=sdf.format(order.getReturnDate())%></td>
                        <td class="text-center align-middle"><%=order.getOrderStatus()%></td>
                        <td class="text-center align-middle">
                            <div class="btn-group" role="group" aria-label="Basic example">
<%--                                此处 <a>标签 id 不能重复        --%>
                                <a id="dispatchBtn<%=order.getOrderId()%>" href="<%=basePath%>/doCarStore?op=carDispatch&do=dispatch&orderId=<%=order.getOrderId()%>"
                                   class="btn btn-outline-success">出车</a>

                                <a id="returnBtn<%=order.getOrderId()%>" href="<%=basePath%>/doCarStore?op=carDispatch&do=return&orderId=<%=order.getOrderId()%>"
                                   class="btn btn-outline-warning">还车</a>

                                <a id="cancelBtn<%=order.getOrderId()%>" href="<%=basePath%>/doCarStore?op=carDispatch&do=cancel&orderId=<%=order.getOrderId()%>"
                                   class="btn btn-outline-danger">取消订单</a>
<%--                                <script type="text/javascript">--%>
<%--                                    initOrderListBtn('<%=order.getOrderStatus()%>', '<%=order.getOrderId()%>')--%>
<%--                                </script>--%>
                            </div>
                        </td>
                    </tr>
                    <%
                            }
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
                    if (pageNow < orderService.getAllOrderByStoreId(carStoreId).size() / NamesConfig.ONE_PAGE_SIZE_SHORT) {
                %>
                <a class="btn btn-primary" href="<%=basePath%>/jsps/carStore/carStoreDispatch.jsp?pageNow=<%=next%>">下一页</a>
                <%
                    }
                %>
            </div>

        </main>





    </div>
</div>

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



<script type="text/javascript" src="<%=basePath%>/js/carStoreHelp.min.js"></script>
</body>
</html>
