<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@include file="/include/library.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${requestScope.shop.shopName} <p>
	<form action="/PhoneShop/control/test" method="post">
		<input type="text" name="txtShop">
		<select name="selectShop">
			<option>cường </option>
			<option>cuong</option>
		</select>
		<input type='submit' value='gui'>
	</form>
	
</body>
</html>