<%@ page import="java.util.List" %>
<%@ page import="cn.edu.nbut.jerry.POJO.Car" %>
<%@ page import="cn.edu.nbut.jerry.service.impl.CarServiceImpl" %>
<%@ page import="cn.edu.nbut.jerry.service.CarService" %>
<%@ page import="cn.edu.nbut.jerry.POJO.CarStore" %>
<%@ page import="cn.edu.nbut.jerry.Utils.CommonUtils" %>
<%@ page import="cn.edu.nbut.jerry.config.NamesConfig" %><%--
  Created by IntelliJ IDEA.
  User: charlieli
  Date: 2021/3/2
  Time: 8:13 下午
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="汽车详情页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>汽车详情页面</title>

    <!-- 新 Bootstrap5 核心 CSS 文件 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- Icon CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <!-- 最新的 Bootstrap5 核心 JavaScript 文件 -->
    <!-- <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    <style>
        a{
            text-decoration:none;
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


    <!-- Custom styles for this -->
    <link href="<%=basePath%>css/index.css" rel="stylesheet">
    <!-- Custom js for this -->
    <script src="<%=basePath%>js/carHelp.min.js"></script>
    <script src="<%=basePath%>js/area.min.js"></script>
    <script src="<%=basePath%>js/utils.min.js"></script>
</head>
<body>

<header class="site-header sticky-top py-1">
    <nav class="container d-flex flex-column flex-md-row justify-content-between">
        <a class="py-2 d-none d-md-inline-block navbar-toggler" href="<%=basePath%>doIndex?op=list">汽车租赁系统</a>
        <%
            Car car = (Car) request.getAttribute("car");
            CarStore carStore = (CarStore) request.getAttribute("carStore");
            // 查看用户是否已经登录，如果登录就显示"退出"按钮，否则就提示用户登录"
            // 当然按钮功能也需要修改
            String showText, signInOrOutLink;
            String showUserIndex = "", selfIndexLink = "#";
            if (session.getAttribute("isAlreadyLoginUser") == null) {
                // 提示用户登录，并带上自己页面的链接跳转到登录页面
                // 弹出提示用户需要先登录
                session.setAttribute("userChooseCarId", car.getCarId());
        %>
        <script>
            // 本页URL是：http://localhost:8080/doGoods?op=rentalCar&carId=24
            // redirectUrl = /doGoods   +   ?op=rentalCar&carId=24
            let redirectUrl = window.location.pathname + window.location.search;
            alert('您还不是用户，请以用户身份登录!');
            window.location.href='<%=basePath%>jsps/sign/sign-in.jsp';
            // alert('请先登录!');window.location.href='login.jsp'
        </script>
        <%
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

<main>
    <!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-3">
                <h1 class="my-4"><%=carStore.getStoreName()%></h1>
                <!--       显示有的几种车辆类型  TODO a标签链接实现       -->
                <div class="list-group">
                    <a id="allCar" href="<%=basePath%>doCarRentalList?op=allType" class="list-group-item text-dark">所有车辆</a>
                    <a id="SUV" href="<%=basePath%>doCarRentalList?op=SUV" class="list-group-item text-dark">越野车</a>
                    <a id="car" href="<%=basePath%>doCarRentalList?op=car" class="list-group-item text-dark">小轿车</a>
                    <a id="sportCar" href="<%=basePath%>doCarRentalList?op=sportCar" class="list-group-item text-dark">跑车</a>
                    <a id="superCar" href="<%=basePath%>doCarRentalList?op=superCar" class="list-group-item text-dark">超级跑车</a>
                </div>
                <script type="text/javascript">
                    carTypeInit('<%=car.getCarType()%>')
                </script>
            </div>
            <%
//                List<Car> cars = carService.getCarsByCarType();
            %>
            <!-- /.col-lg-3 -->

            <div class="col-9">

                <div class="card mt-4">
                    <img class="card-img-top img-fluid" src="<%=car.getCarPicture()%>" alt="汽车图片">

                    <div class="row">
                        <div class="col-8">
                            <div class="card-body">
                                <h3 class="card-title"><%=car.getBrand()%> - <%=car.getBrandNumber()%></h3>
                                <h4 class="h4 text-danger">¥<%=car.getRentalPrice()%> / 天</h4>
                                <p class="card-text mb-0 mt-3">
                                    <%=car.getCarType()%> ｜<%=car.getCars()%>厢 | <%=car.getSeats()%>座
                                </p>
                                <p class="card-text my-0">门店联系电话：<%=carStore.getStoreContactNumber()%></p>
                                <p class="d-inline">取车地址：</p>
<%--                                <p id="spId"></p>--%>
                                <script type="text/javascript">
                                    getCarStoreArea('<%=carStore.getStoreArea()%>')
                                </script>
                                <p class="d-inline"><%=carStore.getStoreAddress()%></p>



                                <!-- 评星 -->
                                <%--<span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
                                                           4.0 stars--%>

                            </div>
                        </div>

                        <div class="col-4">
                            <div class="card-body">

                                <!-- 触发模态框选择租赁日期和归还日期，取车门店，并进行总金额的计算然后跳转到支付宝支付接口 -->
                                <button type="button" class="mt-5 btn btn-primary" data-bs-toggle="modal"
                                        data-bs-target="#orderACar">立即预定</button>

                            </div>
                        </div>
                    </div>



                </div>


                <!-- /.card -->
<%--                用户评价                --%>
<%--                <div class="card card-outline-secondary my-4">--%>
<%--                    <div class="card-header">--%>
<%--                        Product Reviews--%>
<%--                    </div>--%>
<%--                    <div class="card-body">--%>
<%--                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>--%>
<%--                        <small class="text-muted">Posted by Anonymous on 3/1/17</small>--%>
<%--                        <hr>--%>
<%--                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>--%>
<%--                        <small class="text-muted">Posted by Anonymous on 3/1/17</small>--%>
<%--                        <hr>--%>
<%--                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>--%>
<%--                        <small class="text-muted">Posted by Anonymous on 3/1/17</small>--%>
<%--                        <hr>--%>
<%--                        <a href="#" class="btn btn-success">Leave a Review</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <!-- /.card -->

            </div>
            <!-- /.col-lg-9 -->

        </div>

    </div>
    <!-- /.container -->


</main>

<!-- 模态框 -->
<%-- 点击提交，先到一个servlet，再跳转到支付宝操作 --%>
<div class="modal fade" id="orderACar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="staticBackdropLabelEdit">最后一步！</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
<%--                TODO action                             --%>

                <form class="form-control" action="<%=basePath%>doGoods?op=doAlipay" method="POST">
                <%--  input名称：
                                  商户唯一订单编号：      outTradeNo
                                  订单标题：             orderSubject
                                  订单描述：             orderDetail
                                  取车日期：             getCarDate
                                  还车日期：             returnCarDate
                                  总金额：              totalAmount
                                  取车门店Id：           getCarStoreId
                                  还车门店Id：           returnCarStoreId
                                                                                        --%>
                    <div class="container">
                        <div class="row">
                            <!--  1. 订车门店、还车门店
                                  2. 取车日期、还车日期
                                  3. 订单金额，提交给支付宝接口进行支付
                                  4. 修改汽车状态
                              -->
                            <%
                                // 创建一个唯一的订单编号 时间+3位随机数
                            %>
                            <input type="hidden" name="carId" value="<%=car.getCarId()%>">

                            <input type="hidden" name="outTradeNo"
                                   value="<%=CommonUtils.getRandomNumberString()%>">

                            <input type="hidden" name="orderSubject"
                                   value="<%=car.getBrand()+car.getBrandNumber()%> 租赁">

                            <input type="hidden" name="orderDetail"
                                   value="<%=car.getBrand()+car.getBrandNumber()%> 租赁">

                            <input id="inputGetCarDate" type="hidden" name="inputGetCarDate" value="">
                            <input id="inputReturnCarDate" type="hidden" name="inputReturnCarDate" value="">
<%--                            <div class="col-12 mb-3 row">--%>
                                <h4 class="form-label my-3 col-12">快速设置</h4>


                                <div class="col-6 mb-3">
                                    <label class="d-inline">取车日期：</label>
                                    <div class="btn-group mt-0" role="group">
                                        <script type="text/javascript">
                                            // 初始化日期变量
                                            let dayOffset = 1;
                                        </script>
                                        <button id="tomorrowBtn" class="btn btn-outline-success" type="button" value="1"
                                                onclick="setDate('getCarDate', 1);dayOffset=1">明天</button>
                                        <button id="dayAfterBtn" class="btn btn-outline-success" type="button" value="3"
                                                onclick="setDate('getCarDate', 3);dayOffset=3">后天</button>
                                        <button id="7daysBtn" class="btn btn-outline-success" type="button" value="8"
                                                onclick="setDate('getCarDate', 8);dayOffset=8">7天后</button>
<%--                                        <button class="btn btn-outline-secondary" type="button"--%>
<%--                                                onclick="setDate('getCarDate', 31)">30天后</button>--%>
                                    </div>
<%--                                    <input type="text" class="ms-2 form-control w-50 d-inline"--%>
<%--                                           placeholder="自定义取车日期" onchange="setDate('getCarDate', this.value)">--%>
                                </div>


                                <div class="col-6 mb-3">
                                    <label class="d-inline form-label">用车时长：</label>
                                    <div class="btn-group" role="group">
                                        <button class="btn btn-outline-success" type="button"
                                                onclick="setDate('returnCarDate', 1+dayOffset);setDate('getCarDate', dayOffset)">1天</button>
                                        <button class="btn btn-outline-success" type="button"
                                                onclick="setDate('returnCarDate', 3+dayOffset);setDate('getCarDate', dayOffset)">3天</button>
                                        <button class="btn btn-outline-success" type="button"
                                                onclick="setDate('returnCarDate', 7+dayOffset);setDate('getCarDate', dayOffset)">7天</button>
<%--                                        <button class="btn btn-outline-secondary" type="button"--%>
<%--                                                onclick="setDate('returnCarDate', 31);setDate('getCarDate', 1)">30天</button>--%>
                                        <input type="text" class="ms-2 form-control w-50 d-inline"
                                               placeholder="自定义天数"
                                               onchange="setDate('returnCarDate', parseInt(this.value)+parseInt(1));setDate('getCarDate', dayOffset)">
                                    </div>
                                </div>

<%--                            </div>--%>

                            <div class="col-6">
                                <label for="getCarDate" class="">取车日期：</label>
                                <input type="date" class="form-control d-inline w-50"
                                       id="getCarDate" name="getCarDate" value="" required
                                       onchange="countDays()">
                            </div>

                            <div class="col-6">
                                <label for="returnCarDate" class="">还车日期：</label>
                                <input type="date" class="form-control d-inline w-50"
                                       id="returnCarDate" name="returnCarDate" value="" required
                                       onchange="countDays()">
                            </div>


                            <div class="col-6 my-3">
                                <p class="d-inline me-1">车辆租赁价格:</p>
                                <p id="price" class="d-inline text-danger"><%=car.getRentalPrice()%></p>
                                <p class="d-inline">元 / 天</p>
                                <p id="" class="d-inline ms-5 me-0">
                                    您租赁的时长为：
                                </p>
                                <p id="rentalDays" class="d-inline text-danger"></p>
                                <p class="d-inline">天</p>
                            </div>
                            <!-- 显示租赁总金额 -->
                            <div class="col-6 my-3">
                                <p class="d-inline">总金额：</p>
                                <p id="totalAmount" class="d-inline text-danger"></p>
                                <input id="totalAmountInput" type="hidden" name="totalAmount" value="">
                                <p class="d-inline">元</p>
                            </div>
                            <!-- 显示租赁总金额 -->
                            <div class="col-6 my-3">
                                <p class="d-inline">取车日期：</p>
                                <p id="showGetCarDate" class="d-inline text-danger"></p>
                                <p class="d-inline"></p>
                            </div>
                            <div class="col-6 my-3">
                                <p class="d-inline">还车日期：</p>
                                <p id="showReturnCarDate" class="d-inline text-danger"></p>
                                <p class="d-inline"></p>
                            </div>
                            <!-- 显示租赁总金额 -->
                            <div class="col-6 my-3">
                                <p class="d-inline">取车门店：</p>
                                <p id="getCarStore" class="d-inline text-danger"><%=carStore.getStoreName()%></p>
                                <input type="hidden" name="getCarStoreId" value="<%=carStore.getStoreId()%>">
                                <p class="d-inline"></p>
                            </div>
                            <div class="col-6 my-3">
                                <p class="d-inline">还车门店：</p>
                                <p id="returnCarStore" class="d-inline text-danger"><%=carStore.getStoreName()%></p>
                                <input type="hidden" name="returnCarStoreId" value="<%=carStore.getStoreId()%>">
                                <p class="d-inline"></p>
                            </div>

                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">取消</button>
                        <button id="submitBtn" type="submit" class="btn btn-success" disabled>立刻去付钱</button>
                    </div>
                </form>
            </div>
        </div>
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
<footer class="container py-5">
    <div class="row">
        <div class="col-12 col-md">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="d-block mb-2" role="img" viewBox="0 0 24 24"><title>Product</title><circle cx="12" cy="12" r="10"></circle><path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"></path></svg>
            <small class="d-block mb-3 text-muted">&copy; 2020-2021</small>
        </div>
        <div class="col-6 col-md">
            <h5>Features</h5>
            <ul class="list-unstyled text-small">
                <li><a class="link-secondary" href="#">Cool stuff</a></li>
                <li><a class="link-secondary" href="#">Random feature</a></li>
                <li><a class="link-secondary" href="#">Team feature</a></li>
                <li><a class="link-secondary" href="#">Stuff for developers</a></li>
                <li><a class="link-secondary" href="#">Another one</a></li>
                <li><a class="link-secondary" href="#">Last time</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>Resources</h5>
            <ul class="list-unstyled text-small">
                <li><a class="link-secondary" href="#">Resource name</a></li>
                <li><a class="link-secondary" href="#">Resource</a></li>
                <li><a class="link-secondary" href="#">Another resource</a></li>
                <li><a class="link-secondary" href="#">Final resource</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>Resources</h5>
            <ul class="list-unstyled text-small">
                <li><a class="link-secondary" href="#">Business</a></li>
                <li><a class="link-secondary" href="#">Education</a></li>
                <li><a class="link-secondary" href="#">Government</a></li>
                <li><a class="link-secondary" href="#">Gaming</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>About</h5>
            <ul class="list-unstyled text-small">
                <li><a class="link-secondary" href="#">Team</a></li>
                <li><a class="link-secondary" href="#">Locations</a></li>
                <li><a class="link-secondary" href="#">Privacy</a></li>
                <li><a class="link-secondary" href="#">Terms</a></li>
            </ul>
        </div>
    </div>
</footer>
</body>
</html>