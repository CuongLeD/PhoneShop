
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.dao.PhoneDAOImpl"%>
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
	
	
	<h1>List Phone</h1><br>
	<h3>Search Phone</h3>
	<form action='phone/listPhone.jsp?namePhone=${param.findPhone}' method='post'>
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
			<th>Add</th>
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
				<td>${phone.description }</td>
				<td><a href="/admin/phone/add-phone.jsp">Add</a></td>
				<td><a href='admin/phone/edit-phone.jsp?id=${phone.phoneId}'>Edit</a></td>
				<td><a href='/admin/phone/delete-phone?id=${phone.phoneId }'>Delete</a></td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>