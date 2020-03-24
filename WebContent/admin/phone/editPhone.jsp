<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/library.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Sửa thông tin điện thoại</title>
</head>
<body>
<%
	int phoneId = Integer.valueOf(request.getParameter("id").trim());
	Phone phone = new PhoneDAOImpl().getElementById(phoneId);
	request.setAttribute("phone", phone);
	
%>

	<h1>Thông tin điện thoại</h1><br>
	
	<form action='listPhone.jsp' method='post' enctype='multipart/form-data'>
		Phone name: <input type='text' name='txtPhoneName' value='${phone.phoneName}'>
		
	</form>
</body>
</html>