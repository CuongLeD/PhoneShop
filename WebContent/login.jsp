<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login</h1><br>
	
	<c:if test="${param.e != null}">
		<p>Username or password not correct</p>
	</c:if>
	<form action="admin/phone/listPhone.jsp" method="post">
		<input type="text" name="username" placeholder="username"><br>
		<input type="password" name="password"><br>
		<input type="submit" value="Login"><br>
	</form>
</body>
</html>