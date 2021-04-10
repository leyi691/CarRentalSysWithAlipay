<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: charlieli
  Date: 2021/2/7
  Time: 12:54 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="登录页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>注册</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- Icon CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <!-- <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <%--    <script src="<%=basePath%>js/address.js"></script>--%>
    <%--    <script src="<%=basePath%>js/address2.js"></script>--%>
    <script type="text/javascript" src="<%=basePath%>/js/area.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="<%=basePath%>/css/sign-in.css" rel="stylesheet">
    <style>
        a{
            text-decoration:none;
        }
        .bg-img{
            /* 加载背景图 */
            background-image: url("/resources/brand-image/bgimg.jpeg");

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
</head>
<body class="">
<main class="form-signin">
    <form method="POST" action="<%=basePath%>/sign/doSign?op=doSignUp" id="form-sign-up">
        <div class="text-center">
            <!-- aliyun矢量库 -->
            <svg t="1612338278420" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2144" data-darkreader-inline-fill="" width="128" height="128"><path d="M512 62.08A449.92 449.92 0 1 0 961.92 512 449.92 449.92 0 0 0 512 62.08z m0 135.04a135.04 135.04 0 1 1-135.04 135.04A134.72 134.72 0 0 1 512 197.12z m0 640a323.84 323.84 0 0 1-269.76-144.96c0-89.6 179.84-138.56 269.76-138.56s268.48 48.96 269.76 138.56A323.84 323.84 0 0 1 512 835.84z" p-id="2145"></path></svg>
            <h1 class="h3 mb-3 fw-normal">欢迎注册本站会员</h1>
            <h6>
                已有账号？<a class="btn btn-primary btn-sm" href="sign-in.jsp">登录</a>
            </h6>
        </div>



        <div class="col-12 mb-3">
            <label for="inputUsername" class="form-label my-0"><i>*</i>用户名</label>
            <input type="text" name="inputUsername" id="inputUsername" class="form-control"
                   placeholder="请设置用户名" autofocus required>
            <div class="form-text mt-0 text-danger">注：一旦注册成功，用户名不可修改。</div>
        </div>



        <div>
            <label for="inputPassword" class="form-label my-0"><i>*</i>密码</label>
            <input type="password" name="inputPassword" id="inputPassword" class="form-control"
                   aria-describedby="passwordHelpBlock" placeholder="请设置登录密码" required>
<%--            <div id="passwordHelpBlock" class="form-text mb-3 my-0 text-danger">--%>
<%--                密码长度须为8~20个字符。--%>
<%--            </div>--%>
        </div>


        <div class="col-12">
            <label for="inputNickname" class="form-label my-0"><i>*</i>昵称</label>
            <input type="text" name="inputNickname" id="inputNickname" class="form-control mb-3"
                   placeholder="请输入昵称" required>
        </div>


        <div class="md-3 col-12">
            <i>*</i>性别：
            <input type="radio" name="inputSex" id="inputSex" class="form-check-input" value="无" checked required>
            <label for="inputSex" class="form-label my-0 m-1">不愿透露</label>

            <input type="radio" name="inputSex" id="inputSexMan" class="form-check-input" value="男">
            <label for="inputSexMan" class="form-label my-0 m-1">男</label>

            <input type="radio" name="inputSex" id="inputSexWoman" class="form-check-input" value="女">
            <label for="inputSexWoman" class="form-label my-0 m-1">女</label>
        </div>


        <div class="col-12 mt-3">
            <label for="inputPhoneNumber" class="form-label my-0"><i>*</i>手机号</label>
            <input type="text" name="inputPhoneNumber" id="inputPhoneNumber" class="form-control mb-3"
                   placeholder="请输入手机号码" required>
        </div>

        <div class="col-12 mt-3">
            <label for="inputIdType" class="form-label my-0"><i>*</i>证件类型</label>
            <select name="inputIdType" id="inputIdType" class="form-control" required>
                <option value="居民身份证">居民身份证</option>
                <option value="军官证">军官证</option>
                <option value="武警警官证">武警警官证</option>
                <option value="士兵证">士兵证</option>
                <option value="护照">护照</option>
            </select>
        </div>


        <div class="col-12 mt-3">
            <label for="inputIdNumber" class="form-label my-0"><i>*</i>证件号码</label>
            <input type="text" name="inputIdNumber" id="inputIdNumber" class="form-control mb-3"
                   placeholder="证件号码" required>
        </div>

<%--        <!-- 省市区三级联动原型1 begin -->--%>
<%--        <div class="form-group my-3">--%>
<%--            <label class="col-lg-6 form-label my-0 m-3"><i>*</i>请选择所在地址</label>--%>
<%--            <select name="input_province" id="input_province" class="form-control" >--%>
<%--                <option value="">--请选择--</option>--%>
<%--            </select>--%>
<%--            <select name="input_city" id="input_city" class="form-control">--%>
<%--                <option value=""></option>--%>
<%--            </select>--%>
<%--            <select name="input_area" id="input_area" class="form-control">--%>
<%--                <option value=""></option>--%>
<%--            </select>--%>
<%--        </div>--%>
<%--        <!-- 省市区三级联动  end-->--%>

<%--        您选择的是： <input type="text" value="" id="addr-show">--%>

<%--        <!-- 省份选择 -->--%>
<%--        <select id="prov" name="prov" onchange="showCity(this)">--%>
<%--            <option>=请选择省份=</option>--%>
<%--        </select>--%>
<%--        <!-- 城市选择 -->--%>
<%--        <select id="city" onchange="showCountry(this)">--%>
<%--            <option>=请选择城市=</option>--%>
<%--        </select>--%>
<%--        <!-- 区县选择 -->--%>
<%--        <select id="country" onchange="selectCountry(this)">--%>
<%--            <option>=请选择县区=</option>--%>
<%--        </select>--%>
                <script type="text/javascript">
                    var area = new AreaCtrlNormal("省份:","城市:","地区:");
                    area.write();
                    area.loadData();
                </script>


        <!--  具体地址-->
        <div class="col-12 mt-3">
            <label for="inputAddress" class="form-label my-0"><i>*</i>具体地址</label>
            <input type="text" name="inputAddress" id="inputAddress" class="form-control mb-3" placeholder="具体地址" required>
        </div>
<%--        <h6 class="md-3">--%>
<%--            身份：--%>
<%--            <div class="form-check form-check-inline">--%>
<%--                <input type="radio" class="form-check-input" id="user" name="shopAdmin" value="user" checked>--%>
<%--                <label for="user" class="form-check-label">会员</label>--%>
<%--            </div>--%>
<%--            <div class="form-check form-check-inline">--%>
<%--                <input type="radio" class="form-check-input" id="carAdmin" name="shopAdmin" value="shopAdmin">--%>
<%--                <label for="carAdmin" class="form-check-label">营业门店管理员</label>--%>
<%--            </div>--%>
<%--            <div class="form-check form-check-inline">--%>
<%--                <input type="radio" class="form-check-input" id="sysAdmin" name="shopAdmin" value="sysAdmin">--%>
<%--                <label for="sysAdmin" class="form-check-label">系统管理员</label>--%>
<%--            </div>--%>
<%--        </h6>--%>

        <%
            // 获取当前时间
            Date today = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = simpleDateFormat.format(today);
        %>
        <%--        将当前时间隐式传送给servlet           --%>
        <label hidden>
            <input type="hidden" name="inputDateToday" value="<%=todayDate%>" required>
        </label>
        <%--        验证码         --%>
        <div class="mb-3 row">
            <img src="<%=basePath%>/VerificationCodeServlet" alt="" class="col-md-4">
            <div class="col-md-8">
                    <input class="form-control" type="text" name="verificationCode"
                           placeholder="请输入左图的验证码" required>
            </div>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">注 册</button>

        <p class="mt-lg-5 mb-3 text-muted text-center">&copy; 2020-2021 汽车租赁系统</p>
    </form>
</main>


<%--<!-- 三级联动原型1 begin -->--%>
<%--<script type="text/javascript" src="<%=basePath%>/js/address.js"></script>--%>
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
<%--<!-- 三级联动 end -->--%>

<!-- 用户名不含有中文字符 -->
<%--<script language="javascript">--%>

<%--    function checkCN(str){--%>
<%--        // indexOf()方法查找字符串是否包含"\u" ,如果不包含返回-1--%>
<%--        return escape(str).indexOf("%u") < 0;--%>
<%--    }--%>
<%--    // function checkBlankSpace(str){--%>
<%--    //     return escape(str).indexOf(" ") < 0;--%>
<%--    // }--%>

<%--    function check(){--%>
<%--        let username = document.getElementById("inputUsername").value;--%>
<%--        let password = document.getElementById("inputPassword").value;--%>
<%--        let IdNumber = document.getElementById("inputIdNumber").value;--%>
<%--        let IdType = document.getElementById("inputIdType").value;--%>
<%--        if(!checkCN(username)) {--%>
<%--            alert("用户名不允许包含中文字符！");--%>
<%--            document.getElementById("inputUsername").focus();--%>
<%--            history.go(0);--%>
<%--            return;--%>
<%--        }--%>
<%--        if(password.length < 8 || password.length > 20) {--%>
<%--            alert("密码长度须大于8或者小于20");--%>
<%--            document.getElementById("inputPassword").focus();--%>
<%--            history.go(0);--%>
<%--        }--%>
<%--        if(IdNumber.length !== 18 && IdNumber.length !== 15 && IdType === "居民身份证") {--%>
<%--            alert("身份证长度错误");--%>
<%--            document.getElementById("inputIdNumber").focus();--%>
<%--            history.go(0);--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>
