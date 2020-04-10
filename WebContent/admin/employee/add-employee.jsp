<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/include/library.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add employee</title>
</head>
<body>
	<%@include file="/include/menu.html" %>
	
	<h1>Add employee</h1>
	<form action="/PhoneShop/employee/add-employee" method="post">
		<table>
			<tr>
				<th>Name: </th>
				<td> <input type="text" name="txtName"></td>
			</tr>
			<tr>
				<th>Role: </th>
				<td>
					<select name="selectRole">
							<option>
								ROLE_ADMIN
							</option>
							<option>
								ROLE_STAFF
							</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>Shop: </th>
				<td>
					<select name="selectShop">
						<c:forEach items="${shops}" var="shop">
							<option>${shop.shopName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Address: </th>
				<td><input type="text" name="txtAddress"></td>
			</tr>
			<tr>
				<th>Phone Number: </th>
				<td><input type="text"  name="txtPhoneNumber"></td> 
			</tr>
			<tr>
				<th>Email: </th>
				<td><input type="email"  name="email"></td> 
			</tr>
			<tr>
				<th><input type="submit" value="Add"></th> 
				<th><a href="/PhoneShop/employee/list-employee">Cancel</a></th>
			</tr>
		</table>
	</form>
</body>
</html>