
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
	<%@include file="/include/menu.html" %>
	
	<h1>List Phone</h1><br>
	<h3>Search Phone</h3>
	<form action='/PhoneShop/phone/search-phone' accept-charset="utf-8"  method='post'>
		<input type='text' name='phoneName' placeholder='center phone name'>
		<input type='submit' value='search'>
	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>Image</th>
			<th>Name</th>
			<th>Category</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Shop</th>
			<th>Vendor</th>
			<th>Description</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		
		<c:forEach items="${phones}" var="phone">
			<tr>
				<td>${phone.phoneId}</td>
				<td>
					<c:forEach items="${phone.imageLinks}" var="imageLink" >
						<c:if test="${imageLink.isAvatar() }">
							<img alt="avatar" src="${imageLink.imageLink }" height="200" width="200">
						</c:if>
					</c:forEach>
				</td>
				<td>${phone.phoneName}</td>
				<td>${phone.phoneCategory }</td>
				<td>${phone.price }</td>
				<td>${phone.quantity }</td>
				<td>${phone.shopName }</td>
				<td>${phone.vendor }</td>
				<td>${phone.description }</td>
				<td><a href='/PhoneShop/phone/edit-phone?id=${phone.phoneId}'>Edit</a></td>
				<td><a href='/PhoneShop/phone/delete-phone?id=${phone.phoneId }'>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/PhoneShop/phone/add-phone">Add</a>
</body>
</html>