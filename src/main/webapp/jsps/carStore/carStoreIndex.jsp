<%--
  Created by IntelliJ IDEA.
  User: charlieli
  Date: 2021/2/23
  Time: 7:24 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.File" %>
<%@ page import="cn.edu.nbut.jerry.POJO.CarStore" %>
<%@ page import="cn.edu.nbut.jerry.config.NamesConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="店铺信息页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>店铺信息页面</title>

    <!-- 新 Bootstrap5 核心 CSS 文件 -->
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">    <!-- Icon CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <!-- 新 Bootstrap5 核心 JS 文件 -->
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>    <script src="<%=basePath%>/js/area.min.js"></script>
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
<%--    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search" disabled>--%>
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
    // 获取servlet传来的信息
    CarStore carStore = (CarStore) request.getAttribute("carStore");
    // 设置变量carStoreId存储到session中
    session.setAttribute("carStoreId", carStore.getStoreId());
%>
<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="position-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item">

                        <a class="nav-link active" aria-current="page" href="<%=basePath%>/doCarStore?op=list">
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
                <h1 class="h2">营业门店管理员主页</h1>
                <button type="button" class="btn btn-primary" onclick="doEdit()">点我开始修改你的店铺信息</button>
            </div>

            <%--            <%=basePath%>/doUser?op=userIndexEdit--%>
<%--            TODO action           --%>
            <form action="<%=basePath%>/doCarStore?op=edit" method="POST" enctype="multipart/form-data">
                <input type="hidden" value="<%=carStore.getStoreImagePath1()%>" name="imagePath1">
                <input type="hidden" value="<%=carStore.getStoreImagePath2()%>" name="imagePath2">
                <input type="hidden" value="<%=carStore.getStoreImagePath3()%>" name="imagePath3">
                <%
                    String store_img1, store_img2 ,store_img3;
                    String imgClass = "center-block";
                    if (carStore.getStoreImagePath1() == null
                            || carStore.getStoreImagePath1().equals("")
                            || carStore.getStoreImagePath1().equals("null")) {
                        store_img1 = basePath + File.separator + "resources" + File.separator
                                + "brand-image" + File.separator + "BigSurMorning.jpg";
                %>
                    <input type="hidden" value="true" name="isFirstUpload1">
                <%
                    } else {
                        store_img1 = basePath + carStore.getStoreImagePath1();
                    }

                    if (carStore.getStoreImagePath2() == null
                            || carStore.getStoreImagePath2().equals("")
                            || carStore.getStoreImagePath2().equals("null")) {
                        store_img2 = basePath + File.separator + "resources" + File.separator
                                + "brand-image" + File.separator + "BigSurNoon.jpg";
                %>
                <input type="hidden" value="true" name="isFirstUpload2">
                <%
                    } else{
                        store_img2 = basePath + carStore.getStoreImagePath2();
                    }
                    if (carStore.getStoreImagePath3() == null
                            || carStore.getStoreImagePath3().equals("")
                            || carStore.getStoreImagePath3().equals("null")) {
                        store_img3 = basePath + File.separator + "resources" + File.separator
                                + "brand-image" + File.separator + "BigSurEvening.jpg";
                %>
                <input type="hidden" value="true" name="isFirstUpload3">
                <%
                    } else {
                        store_img3 = basePath + carStore.getStoreImagePath3();
                    }
                %>
                <div class="container">
                    <div class="row">
                        <h1 class="h4">店铺展示图片</h1>
<%--                        TODO 店铺轮播图链接 初始进入给三张初始图片  --%>
                        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                            </div>
                            <!--  店铺轮播图  -->
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="<%=store_img1%>" class="<%=imgClass%>" alt="...">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>你的第一张图片</h5>
                                        <p></p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img src="<%=store_img2%>" class="<%=imgClass%>" alt="...">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>你的第二张图片</h5>
                                        <p></p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img src="<%=store_img3%>" class="<%=imgClass%>" alt="...">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>你的第三张图片</h5>
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"  data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"  data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                            <!-- <div class="bg-img w-50 h-50" style="background-image: url(portrait.jpeg);" ></div> -->
                        </div>

<%--                        TODO 设置一个一直浮动在右边的按钮用来激活修改--%>


                        <!-- 左侧：用户名 密码 昵称 手机号 右侧： 店铺状态 区域 地址 -->
                        <!-- 左侧开始：用户名 密码 昵称 手机号 -->
                        <div class="col-6 mt-3 mb-5">
                            <div class="mb-3">
                                <input type="hidden" name="inputAdminId" value="<%=carStore.getStoreAdminUsername()%>">
                                <label for="inputAdminName" class="form-label">用户名：</label>
                                <input type="text" class="form-control"
                                       value="<%=carStore.getStoreAdminUsername()%>" id="inputAdminName" name="inputAdminName"
                                       placeholder=""  disabled readonly>
                                <div class="form-text">用户名不可修改</div>
                            </div>


                            <div class="mb-3">
                                <label for="inputPassword" class="form-label">密码：</label>
                                <input type="password" class="form-control"
                                       value="" id="inputPassword" name="inputPassword"
                                       placeholder="············" disabled>
                                <div class="form-text">限制长度为8~20个字符</div>
                            </div>

                            <!-- <div class="mt-3">
                              <label for="inputIdType" class="form-label my-0">证件类型：</label>
                              <select name="inputIdType" id="inputIdType" class="form-control" disabled>
                                  <option value="居民身份证">居民身份证</option>
                                  <option value="军官证">军官证</option>
                                  <option value="武警警官证">武警警官证</option>
                                  <option value="士兵证">士兵证</option>
                                  <option value="护照">护照</option>
                              </select>
                          </div> -->
                            <!-- 省市区三级联动 begin -->
                            <script type="text/javascript">
                                var area = new AreaCtrlNormal("省份："," 城市："," 地区：");
                                area.value = '<%=carStore.getStoreArea()%>';
                                area.write();
                                area.loadData();
                            </script>
                            <!-- 省市区三级联动  end -->

                            <!--  具体地址-->
                            <div class="mt-1">
                                <label for="inputAddress" class="form-label my-0">具体地址：</label>
                                <input type="text" name="inputAddress" id="inputAddress" class="form-control mb-3"
                                       value="<%=carStore.getStoreAddress()%>" disabled>
                            </div>


                        </div>


                        <!-- 右侧开始：店铺名称 联系电话 店铺状态 上传店铺的照片 -->
                        <div class="col-6 mt-3">
                            <div class="mb-3">
                                <label for="inputStoreName" class="form-label">店铺名称：</label>
                                <input type="text" class="form-control"
                                       value="<%=carStore.getStoreName()%>" id="inputStoreName" name="inputStoreName"
                                       disabled>
                            </div>

                            <div class="mt-3">
                                <label for="inputPhoneNumber" class="form-label my-0">联系电话：</label>
                                <input type="text" name="inputPhoneNumber" id="inputPhoneNumber" class="form-control mb-3"
                                       value="<%=carStore.getStoreContactNumber()%>" disabled>
                            </div>



                            <div class="mb-3">
                                门店状态：

                                <input type="radio" name="inputStatus" id="inputStatusOpen" class="form-check-input" value="开业中" disabled>
                                <label for="inputStatusOpen" class="form-label my-0 m-1 text-success">开业中</label>

                                <input type="radio" name="inputStatus" id="inputStatusSuspended" class="form-check-input" value="暂停营业" disabled>
                                <label for="inputStatusSuspended" class="form-label my-0 m-1 text-warning">暂停营业</label>

                                <input type="radio" name="inputStatus" id="inputStatusTerminated" class="form-check-input" value="永久停业" disabled>
                                <label for="inputStatusTerminated" class="form-label my-0 m-1 text-danger">永久停业</label>

                                <!--  先等待input标签加载完成，再将其选中  -->
                                <script type="text/javascript">storeStatusSelectInit("<%=carStore.getStoreStatus()%>")</script>
                            </div>

                            <div class="mt-0">
                                <div class="form-label mb-1">上传店铺的照片：</div>
<%--                                <script type="text/javascript">--%>
<%--                                    var addFiles = new addComponent();--%>
<%--                                    addFiles.write();--%>
<%--                                </script>--%>
                                <!-- addInput() name属性值是你自己添加的 -->
                                <button type="button" class="btn btn-outline-success" id="addImgInputBtn" name="button"
                                       onclick="addInput('inputFileName')" disabled
                                       data-bs-toggle="tooltip" data-bs-placement="top" title="再点一次可以再添加一张照片">

                                    添加图片</button>
                                <label for="addImgInputBtn"></label>
                                <button type="button" class="btn btn-outline-danger" id="deleteImgInputBtn" name="button"
                                        onclick="deleteInput()" disabled>

                                    删除最后加入的图片</button>
                                <label for="deleteImgInputBtn"></label>

                                <div class="form-text">注：你的店铺只能拥有最多3张图片，上传新的图片将会覆盖老的图片。点击"添加图片按钮加入/替换第一张图片。"</div>


                                <span id="upload">
                                <!-- 这里是input添加的地方，使用JavaScript添加 -->
                                </span>
                                <!-- 提示用户最多只能添加3张照片 -->
                                <div id="warning" class="alert alert-warning fade mt-2" role="alert" >
                                    <strong>一次最多只能添加3张照片</strong>
                                </div>
<%--                                <input type="file" id="files">--%>
<%--                                <input class="form-control" name="uploadFirst" type="file" id="upload-1" disabled>--%>
<%--                                <input class="form-control" name="uploadSecond" type="file" id="uploadSecond" disabled>--%>
<%--                                <input class="form-control" name="uploadThird" type="file" id="uploadThird" disabled>--%>
                            </div>
                        </div>



                        <!-- row justify-content-md-center 用于居中  -->
                        <div class="row justify-content-md-center mt-3">
                            <div class="col-6">
                                <button class="w-100 btn btn-outline-primary" type="submit" id="btn-submitEdit" hidden>提交修改</button>
                            </div>
                        </div>

                    </div>
                </div>
            </form>

        </main>
    </div>
</div>


<%--各种提醒（alert）--------------------------------------------------------------------------%>
<!-- 提示用户修改成功！ -->
<div id="success" class="alert alert-success fade" role="alert"
     style="width: auto; position: fixed; top: 80%; left: 55%; margin-left: -80px;">
    <strong>修改成功！</strong>
</div>

<%--条件1--%>
<!-- 提示用户修改成功！ -->
<%
    try {
        String isEditSuccess = (String) request.getAttribute("isEditSuccess");   // 容易报空指针异常，出异常也不报，直接空白页面
//        System.out.println("request.getAttribute(\"isEditSuccess\") = " + isEditSuccess);
        if (isEditSuccess.equals("true")) {
%>
<script type="text/javascript">
    $("#success").addClass("show"); // 修改成功。
    window.setTimeout(function(){$("#success").removeClass("show");},2000);//显示的时间
</script>
<%
        }
    }catch (Exception e) {
        System.err.println("lijiayu" + e);
    }
%>







<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>
<!-- <script src="./js/userIndex.js"></script> -->
<!-- 三级联动 begin -->
<%--<script type="text/javascript" src="/js/address.js"></script>--%>
<%--<script >--%>

<%--    $(function () {--%>
<%--        var html = "";--%>
<%--        $("#input_city").append(html);--%>
<%--        $("#input_area").append(html);--%>
<%--        $.each(pdata,function(idx,item){--%>
<%--            if (parseInt(item.level) == 0) {--%>
<%--                html += "<option value="+item.code+" >"+ item.names +"</option> ";--%>
<%--            }--%>
<%--        });--%>
<%--        $("#input_province").append(html);--%>

<%--        $("#input_province").change(function(){--%>
<%--            if ($(this).val() == "") return;--%>
<%--            $("#input_city option").remove();--%>
<%--            $("#input_area option").remove();--%>
<%--            //var code = $(this).find("option:selected").attr("exid");--%>
<%--            var code = $(this).find("option:selected").val();--%>
<%--            code = code.substring(0,2);--%>
<%--            var html = "<option value=''>--请选择--</option>";--%>
<%--            $("#input_area option").append(html);--%>
<%--            $.each(pdata,function(idx,item){--%>
<%--                if (parseInt(item.level) == 1 && code == item.code.substring(0,2)) {--%>
<%--                    html +="<option value="+item.code+" >"+ item.names +"</option> ";--%>
<%--                }--%>
<%--            });--%>
<%--            $("#input_city ").append(html);--%>
<%--        });--%>

<%--        $("#input_city").change(function(){--%>
<%--            if ($(this).val() == "") return;--%>
<%--            $("#input_area option").remove();--%>
<%--            var code = $(this).find("option:selected").val();--%>
<%--            code = code.substring(0,4);--%>
<%--            var html = "<option value=''>--请选择--</option>";--%>
<%--            $.each(pdata,function(idx,item){--%>
<%--                if (parseInt(item.level) == 2 && code == item.code.substring(0,4)) {--%>
<%--                    html +="<option value="+item.code+" >"+ item.names +"</option> ";--%>
<%--                }--%>
<%--            });--%>
<%--            $("#input_area ").append(html);--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
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
</body>
</html>
