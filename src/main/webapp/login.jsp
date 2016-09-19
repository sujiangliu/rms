<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rms</title>
	<!-- 引用样式 -->
    <link rel="stylesheet" type="text/css" href="${baseURL }/common/css/login.css">
    <!-- 引用js -->
    <script src="${baseURL}/common/js/jquery-2.2.3.min.js"></script>
    <script src="${baseURL}/common/js/message.js"></script>
    <script src="${baseURL}/common/js/login.js"></script>
</head>
<body>

    <div class="container">
        <form class="loginForm" method="post">
          <h1>登陆</h1>
          <p>
            <label for="login">用户名</label>
            <input type="text" name="username" placeholder="请输入用户名" required=""></p>
          <p>
            <label for="password">密码</label>
            <input type="password" name="password" placeholder="请输入密码" required=""></p>
          <p>
            <input type="submit" name="submit" onclick="return subimitForm()" value="登陆"></p>
        </form>
    </div>
</body>
</html>