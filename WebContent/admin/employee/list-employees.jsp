<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/library.jsp" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Employees</title>
</head>
<body>
	<%@include file="/include/menu.html" %>
	<h1>List Employees</h1><br>
	<form action="/PhoneShop/employee/list-employees" method="post">
		<input type="text" name="search" placeholder="Enter employee name">
		<input type="search" value="search">
	</form>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Position</th>
			<th>Shop</th>
			<th>Address</th>
			<th>Phone Number</th>
			<th>Email</th>
			<th>Date begin</th>
			<th>Edit</th>	
			<th>Delete</th>
		</tr>
		
		<c:forEach items="${employees}" var="employee">
			<tr>
				<td>${employee.employeeId }</td>
				<td>${employee.employeeName }</td>
				<td>${employee.employeePosition }</td>
				<td>${employee.shopName }</td>
				<td>${employee.employeeAddress }</td>
				<td>${employee.employeePhoneNumber }</td>
				<td>${employee.employeeEmail }</td>
				<td>${employee.dateCreate.toString() }</td>
				<td><a href="/PhoneShop/employee/edit-employee?id=${employee.employeeId }">Edit</a></td>
				<td><a href="/PhoneShop/employee/delete-employee?id=${employee.employeeId }">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/PhoneShop/employee/add-employee">Add employee</a>
</body>
</html>