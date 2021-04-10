<%--
  Created by IntelliJ IDEA.
  User: charlieli
  Date: 2020/11/12
  Time: 9:28 上午
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="GB2312">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="登录页面">
    <meta name="author" content="励佳余">
    <link rel="shortcut icon" href="<%=basePath%>/resources/brand-image/carRentalSys.png" type="image/x-icon">
    <title>请登录</title>
    <!-- 新 Bootstrap5 核心 CSS 文件 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- Icon CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <!-- <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous">

    </script>
    <!-- Custom styles for this template -->
    <link href="<%=basePath%>css/sign-in.css?v=<%= System.currentTimeMillis()%>" rel="stylesheet">
</head>
<body class="text-center">
<main class="form-signin">
    <form method="POST" action="<%=basePath%>sign/doSign?op=doSignIn" name="formSign">
        <!-- aliyun矢量库 -->
        <svg t="1612338278420" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2144" data-darkreader-inline-fill="" width="128" height="128"><path d="M512 62.08A449.92 449.92 0 1 0 961.92 512 449.92 449.92 0 0 0 512 62.08z m0 135.04a135.04 135.04 0 1 1-135.04 135.04A134.72 134.72 0 0 1 512 197.12z m0 640a323.84 323.84 0 0 1-269.76-144.96c0-89.6 179.84-138.56 269.76-138.56s268.48 48.96 269.76 138.56A323.84 323.84 0 0 1 512 835.84z" p-id="2145"></path></svg>
        <h1 class="h3 mb-3 fw-normal">请登录</h1>

        <!-- <label for="inputUsername" class="form-label"></label> -->
        <input type="text" name="inputUsername" id="inputUsername" class="form-control mb-3" placeholder="请输入用户名" required autofocus>
        <!-- <label for="inputPassword" class="form-label"></label> -->
        <input type="password" name="inputPassword" id="inputPassword" class="form-control mb-3" placeholder="请输入密码" required>

<%--        验证码         --%>
        <div class="mb-3 row">
            <img src="<%=basePath%>/VerificationCodeServlet" alt="" class="col-md-4">
            <div class="col-md-8">
                <label>
                    <input class="form-control" type="text" name="verificationCode" placeholder="请输入左图的验证码">
                </label>
            </div>
        </div>


        <h6 class="md-3">
            身份：
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" id="user" name="userIdentity" value="user" checked>
                <label for="user" class="form-check-label">会员</label>
            </div>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" id="shopAdmin" name="userIdentity" value="shopAdmin">
                <label for="shopAdmin" class="form-check-label">营业门店管理员</label>
            </div>
            <div class="form-check form-check-inline">
                <input type="radio" class="form-check-input" id="sysAdmin" name="userIdentity" value="sysAdmin">
                <label for="sysAdmin" class="form-check-label">系统管理员</label>
            </div>
        </h6>


        <button class="w-100 btn btn-lg btn-primary" type="submit">登录</button>
        <h6 class="fw-normal my-3">
            没有账号？
            <a class="btn btn-secondary btn-sm" href="<%=basePath%>jsps/sign/sign-up.jsp">注册一个</a>
            <a class="btn btn-outline-secondary btn-sm" href="<%=basePath%>doIndex?op=list">返回主页</a>
        </h6>
        <p class="mt-lg-5 mb-3 text-muted">&copy; 2020-2021 汽车租赁系统</p>
    </form>
</main>
</body>
</html>
