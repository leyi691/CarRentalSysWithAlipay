<%@ page import="cn.edu.nbut.jerry.POJO.User" %>
<%@ page import="java.io.File" %>
<%@ page import="cn.edu.nbut.jerry.POJO.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.nbut.jerry.POJO.CarStore" %>
<%@ page import="cn.edu.nbut.jerry.service.CarStoreService" %>
<%@ page import="cn.edu.nbut.jerry.service.impl.CarStoreServiceImpl" %>
<%@ page import="cn.edu.nbut.jerry.config.NamesConfig" %>
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
    <meta name="description" content="系统管理员页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>系统管理员页面</title>

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
    <%--    <script src="<%=basePath%>/js/userHelp.min.js"></script>--%>
    <!--自定义的js脚本-->
    <script type="text/javascript" src="<%=basePath%>/js/utils.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/area.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/adminHelp.min.js"></script>

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


    <!-- Custom styles for this template -->
    <link href="<%=basePath%>/css/userIndex.css" rel="stylesheet">
</head>
<body>
<%
    if (session.getAttribute("sysAdminId") == null){
%>
<script>alert('请先登录!');window.location.href='<%=basePath%>sign-in.jsp'</script>
<%
    }
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
                        <a class="nav-link" aria-current="page" href="<%=basePath%>/doSysAdmin?op=list">
                            <span data-feather="home"></span>
                            系统管理员主页
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="<%=basePath%>/doSysAdmin?op=shopManage&do=list">
                            <span data-feather="order"></span>
                            门店管理
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=basePath%>/doSysAdmin?op=userManage&do=list">
                            <span data-feather="order"></span>
                            用户管理
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">门店管理</h1>

                <form class="row" id="thisForm1" name="thisForm" method="post">

                    <div class="col-auto">
                        <div class="input-group" role="group">
                            <label>
                                <input name="searchTxt" type="text" class="form-control" placeholder="按门店名称搜索">
                            </label>
                            <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">查询</button>
                            <button type="button" class="btn btn-outline-secondary" onclick="sel(1)">显示所有</button>
                        </div>
                    </div>
                    <div class="col-auto">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#insertCarShopModal">
                            添加门店</button>
                    </div>
                </form>



            </div>
            <%
                CarStoreService carStoreService = new CarStoreServiceImpl();
                // 显示所有的门店信息
                List<CarStore> carStores = null;
                String getPageNow = request.getParameter("pageNow");
                String searchText = request.getParameter("searchTxt");
                int pageNow = 0;
                if (searchText != null) {
                    carStores = (List<CarStore>) CommonUtils.getSpecificPage(pageNow, carStoreService.searchCarStoreLikeCarStoreNameForSysAdmin(searchText), NamesConfig.ONE_PAGE_SIZE_MID);
                } else if (getPageNow != null) {
                    pageNow = Integer.parseInt(getPageNow);
                    carStores = (List<CarStore>) CommonUtils.getSpecificPage(pageNow, carStoreService.getAllCarStoreAccount(), NamesConfig.ONE_PAGE_SIZE_MID);
                } else if (request.getAttribute("carStores") != null) {
                    carStores = (List<CarStore>) request.getAttribute("carStores");
                } else {
                    carStores = (List<CarStore>) CommonUtils.getSpecificPage(pageNow, carStoreService.getAllCarStoreAccount(), NamesConfig.ONE_PAGE_SIZE_MID);
                }
            %>
            <%--            <%=basePath%>/doUser?op=userIndexEdit--%>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr class="text-center">
                            <th scope="col">编号</th>
                            <th scope="col">门店用户名</th>
                            <th scope="col">门店密码</th>
                            <th scope="col">门店名</th>
                            <th scope="col">门店联系电话</th>
                            <th scope="col">门店区域</th>
                            <th scope="col">门店具体地址</th>
                            <th scope="col">门店状态</th>
                            <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        // 循环输出汽车信息
                        for (CarStore carStore : carStores) {
//                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    %>
                    <tr>
                        <th scope="row" class="text-center align-middle">
                            <%=carStore.getStoreId()%>
                        </th>
                        <td class="text-center align-middle"><%=carStore.getStoreAdminUsername()%></td>
<%--                        TODO 这里的密码要不要明文显示呢？             --%>
                        <td class="text-center align-middle"><%=carStore.getStorePassword()%></td>
                        <td class="text-center align-middle"><%=carStore.getStoreName()%></td>
                        <td class="text-center align-middle"><%=carStore.getStoreContactNumber()%></td>

                        <td class="text-center align-middle">
                            <script type="text/javascript">
                                getCarStoreArea('<%=carStore.getStoreArea()%>')
                            </script>
                        </td>

                        <td class="text-center align-middle"><%=carStore.getStoreAddress()%></td>
                        <td class="text-center align-middle"><%=carStore.getStoreStatus()%></td>


                        <td class="text-center align-middle">
                            <%--                            使用<a> 标签的id 来暂时保存carId 我真是个天才！！！！！！！！                       --%>
                            <form id="thisForm<%=carStore.getStoreId()%>" name="thisForm" method="post">
                                <input type="hidden" name="toEditCarStoreId" value="<%=carStore.getStoreId()%>">
                            </form>
                            <a type="button" class="btn btn-outline-primary" onclick="sel(<%=carStore.getStoreId()%>)">修改</a>
                            <a href="<%=basePath%>/doSysAdmin?op=shopManage&do=delete&storeId=<%=carStore.getStoreId()%>" class="btn btn-outline-danger">删除</a>
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
                    int next = pageNow >= NamesConfig.ONE_PAGE_SIZE_MID ? NamesConfig.ONE_PAGE_SIZE_MID : pageNow + 1;
                    if (pageNow != 0) {
                %>
                <a class="btn btn-primary" href="<%=basePath%>/jsps/sysAdmin/sysAdminManageCarStore.jsp?&pageNow=<%=front%>">上一页</a>
                <%
                    }
                    if (pageNow < carStoreService.getAllCarStoreAccount().size() / NamesConfig.ONE_PAGE_SIZE_MID) {
                %>
                <a class="btn btn-primary" href="<%=basePath%>/jsps/sysAdmin/sysAdminManageCarStore.jsp?pageNow=<%=next%>">下一页</a>
                <%
                    }
                %>
            </div>

        </main>
    </div>
</div>


<!--新增门店模态框-->
<!--   触发id：  -->
<div class="modal fade" id="insertCarShopModal" data-bs-keyboard="true" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">新增门店</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="form-control" action="<%=basePath%>/doSysAdmin?op=shopManage&do=insert" method="POST">
                    <div class="container">
                        <div class="row">

                            <div class="col-6">
                                <label for="storeUserName"><i>*</i>门店用户名：</label>
                                <input type="text" class="form-control"
                                       id="storeUserName" name="storeUserName" required>
                                <div class="form-text mt-0 mb-2">
                                    注：一旦注册成功用户名不可修改。</div>
                            </div>

                            <div class="col-6">
                                <label for="password"><i>*</i>门店密码：</label>
                                <input type="password" class="form-control"
                                       id="password" name="password" required>
                                <div class="form-text mt-0 mb-2">
                                    密码长度须为8~20个字符。</div>
                            </div>

                            <div class="col-6">
                                <label for="contactNumber"><i>*</i>联系电话：</label>
                                <input type="text" class="form-control"
                                       id="contactNumber" name="contactNumber" required>
                            </div>

                            <div class="col-6">
                                <label for="storeName"><i>*</i>门店名称：</label>
                                <input type="text" class="form-control"
                                       id="storeName" name="storeName" required>

                            </div>

                            <div class="col-6 mt-2">
                                <p class="form-label"><i>*</i>门店状态：</p>
                                <input type="radio" name="inputStatus" id="inputStatusOpenInsert" class="form-check-input" value="开业中">
                                <label for="inputStatusOpenInsert" class="form-label my-0 m-1 text-success">开业中</label>

                                <input type="radio" name="inputStatus" id="inputStatusSuspendedInsert" class="form-check-input" value="暂停营业">
                                <label for="inputStatusSuspendedInsert" class="form-label my-0 m-1 text-warning">暂停营业</label>

                                <input type="radio" name="inputStatus" id="inputStatusTerminatedInsert" class="form-check-input" value="永久停业">
                                <label for="inputStatusTerminatedInsert" class="form-label my-0 m-1 text-danger">永久停业</label>

                                <br>

                                <label for="address" class="mt-2"><i>*</i>具体地址：</label>
                                <input type="text" class="form-control mb-5"
                                       id="address" name="address" required>
                            </div>


                            <div class="col-6 mt-2">
                                <script type="text/javascript">
                                    // input name 是 Area
                                    var area = new AreaCtrlNormal("省份："," 城市："," 地区：");
                                    area.write();
                                    area.loadData();
                                </script>
                            </div>


<%--                            <div class="col-6 mb-5">--%>

<%--                            </div>--%>




                        </div>
                    </div>

                    <div class="modal-footer mt-5">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--修改门店模态框-->
<!--   触发id：  -->
<%
    try {
        Integer toEditCarStoreId = Integer.valueOf(request.getParameter("toEditCarStoreId"));
        CarStore carStoreEdit = carStoreService.getCarStoreAccountById(toEditCarStoreId);

%>
<div class="modal fade" id="editShopModal" data-bs-keyboard="true" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">修改门店信息</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="form-control" action="<%=basePath%>/doSysAdmin?op=shopManage&do=edit" method="POST">
                    <div class="container">
                        <div class="row">
                            <input type="hidden" name="storeIdEdit" value="<%=toEditCarStoreId%>">
                            <div class="col-6">
                                <label for="storeUserNameEdit"><i>*</i>门店用户名：</label>
                                <input type="text" class="form-control"
                                       id="storeUserNameEdit" name="storeUserName" required
                                       value="<%=carStoreEdit.getStoreAdminUsername()%>" disabled>
                                <div class="form-text mt-0 mb-2">
                                    注：一旦注册成功用户名不可修改。</div>
                            </div>

                            <div class="col-6">
                                <label for="passwordEdit"><i>*</i>门店密码：</label>
                                <input type="password" class="form-control" placeholder="··········"
                                       id="passwordEdit" name="password">
                                <div class="form-text mt-0 mb-2">
                                    密码长度须为8~20个字符。</div>
                            </div>

                            <div class="col-6">
                                <label for="contactNumberEdit"><i>*</i>联系电话：</label>
                                <input type="text" class="form-control"
                                       id="contactNumberEdit" name="contactNumber" required
                                       value="<%=carStoreEdit.getStoreContactNumber()%>" >
                            </div>

                            <div class="col-6">
                                <label for="storeNameEdit"><i>*</i>门店名称：</label>
                                <input type="text" class="form-control"
                                       id="storeNameEdit" name="storeName" required
                                       value="<%=carStoreEdit.getStoreName()%>" >

                            </div>

                            <div class="col-6 mt-2">
                                <p class="form-label"><i>*</i>门店状态：</p>
                                <input type="radio" name="inputStatus" id="inputStatusOpen" class="form-check-input" value="开业中">
                                <label for="inputStatusOpen" class="form-label my-0 m-1 text-success">开业中</label>

                                <input type="radio" name="inputStatus" id="inputStatusSuspended" class="form-check-input" value="暂停营业">
                                <label for="inputStatusSuspended" class="form-label my-0 m-1 text-warning">暂停营业</label>

                                <input type="radio" name="inputStatus" id="inputStatusTerminated" class="form-check-input" value="永久停业">
                                <label for="inputStatusTerminated" class="form-label my-0 m-1 text-danger">永久停业</label>

                                <br>

                                <label for="addressEdit" class="mt-2"><i>*</i>具体地址：</label>
                                <input type="text" class="form-control mb-5"
                                       id="addressEdit" name="address" required
                                       value="<%=carStoreEdit.getStoreAddress()%>">
                                <script type="text/javascript">
                                    storeStatusSelectInit('<%=carStoreEdit.getStoreStatus()%>')
                                </script>
                            </div>


                            <div class="col-6 mt-2">
                                <script type="text/javascript">
                                    // input name 是 Area
                                    var area = new AreaCtrlNormal("省份："," 城市："," 地区：");
                                    area.value='<%=carStoreEdit.getStoreArea()%>'
                                    area.write();
                                    area.loadData();
                                </script>
                            </div>


                            <%--                            <div class="col-6 mb-5">--%>

                            <%--                            </div>--%>




                        </div>
                    </div>

                    <div class="modal-footer mt-5">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    showModel('editShopModal')
</script>
<%
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }
%>
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
<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>


</body>
</html>
