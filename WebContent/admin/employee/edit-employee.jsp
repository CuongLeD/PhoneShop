<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/include/library.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit employee: ${employee.employeeName }</title>
</head>
<body>
	<%@include file="/include/menu.html" %>
	
	<h1>Edit employee: ${employee.employeeName }</h1>
	<form action="/PhoneShop/employee/edit-employee" method="post">
		<table>
			<tr>
				<th>ID:</th>
				<td> <input type="text" value="${employee.employeeId}" name="txtId" readonly="readonly"> </td>
			</tr>
			<tr>
				<th>Name: </th>
				<td> <input type="text" value="${employee.employeeName}" name="txtName"></td>
			</tr>
			<tr>
				<th>Role: </th>
				<td>
					<input type="text" name="txtRole" value="${employee.employeePosition }">
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
				<td><input type="text" value="${employee.employeeAddress }" name="txtAddress"></td>
			</tr>
			<tr>
				<th>Phone Number: </th>
				<td><input type="text" value="${employee.employeePhoneNumber }" name="txtPhoneNumber"></td> 
			</tr>
			<tr>
				<th> Email: </th>
				<td><input type="email" value="${employee.employeeEmail}" name="email"></td>	
			</tr>
			
			<tr>
				<th><input type="submit" value="Edit"></th> 
				<th><a href="/PhoneShop/employee/list-employees">Cancel</a></th>
			</tr>
		</table>
	</form>
</body>
</html>