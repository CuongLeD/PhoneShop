
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="control.dao.PhoneDAOImpl"%>
<%@page import="model.Phone" %>
<%@page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Phone</title>
</head>
<body>
	<%
		PhoneDAOImpl phoneDao = new PhoneDAOImpl();
		List<Phone> phones = phoneDao.getElements(0, 10);
		request.setAttribute("phones", phones);
	%>
	
	<h1>List Phone</h1><br>
	<h3>Search Phone</h3>
	<form action='phone/ListPhone.jsp?namePhone=${param.findPhone}' method='post'>
		<input type='text' name='findPhone' placeholder='center phone id or name'>
		<input type='submit' value='search'>
	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Category</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Vendor</th>
			<th>Description</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
		<c:forEach items="${phones}" var="phone">
			<tr>
				<td>${phone.phoneId}</td>
				<td>${phone.phoneName}</td>
				<td>${phone.phoneCategory }</td>
				<td>${phone.price }</td>
				<td>${phone.quantity }</td>
				<td>${phone.vendor }</td>
				<td>${phone.decription }</td>
				<td><a href='editPhone.jsp?id=${phone.phoneId}'>Edit</a></td>
				<td><a href='deletePhone?id=${phone.phoneId }'>Delete</a></td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>