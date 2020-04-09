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
	
	<c:choose>
		<c:when test="${param.e == 1}">
			<p>You are not an administrator<br>
				Please login again!
			</p>
		</c:when>
		<c:otherwise>
			<c:if test="${param.e == 2}">
			<p>Username or password is not correct!</p>	
			</c:if>
		</c:otherwise>
	</c:choose>
	<form action="/PhoneShop/login" method="post" >
		<input type="text" name="username" placeholder="Enter username"><br>
		<input type="password" name="password" placeholder="Enter password"><br>
		<input type="submit" value="Login"><br>
	</form>
</body>
</html>