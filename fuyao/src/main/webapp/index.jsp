<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>热烈欢迎</h1>
	<div>
		<h2>登陆</h2>
		<form action="login/login" method="post">
			<input type="text" name="userName">
			<input type="password" name="passWord">
			<input type="submit" value="登陆"> 
		</form>
	</div>
	<div>
		<h2>修改</h2>
		<form action="login/update" method="post">
			id<input type="text" name="id">
			用户名<input type="text" name="userName">
			密码<input type="password" name="passWord">
			<input type="submit" value="修改"> 
		</form>
	</div>
</body>
</html>