<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/library.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Sửa thông tin điện thoại</title>
</head>
<body>
<%
	int phoneId = Integer.valueOf(request.getParameter("id").trim());
	Phone phone = new PhoneDAOImpl().getElementById(phoneId);
	request.setAttribute("phone", phone);
	java.util.List<Category> categories = new CategoryDAOImpl().getElements(0, new CategoryDAOImpl().amountRows());
	request.setAttribute("categories", categories);
	
%>

	<h1>Thông tin điện thoại</h1><br>
	
	<form action='list-phone.jsp' method='post' enctype='multipart/form-data'>
		<table>
			<tr>
				<th>Avatar:</th>
				<td>
					<c:forEach items="${phone.imageLinks}" var="imageLink" >
						<c:if test="${imageLink.isAvatar }">
							<img alt="avatar" src="${imageLink.imageLink }">
						</c:if>
						
					</c:forEach>
					<input type='file' name='fileAvatar'>
				</td>
			</tr>
			<tr>
				<th> Name: </th>
				<td> <input type='text' name="txtPhoneName" value="${phone.phoneName}"> </td>
			</tr>
			<tr>
				<th> Price: </th>
				<td> <input type='text' name='txtPrice' value='${phone.price}'> </td>
			</tr>
			<tr>
				<th> Quantity: </th>
				<td><input type='number' name='txtQuantity' value='${phone.quantity}'> </td>
			</tr>
			<tr>
				<th> Category: </th> 
				<td>
					<select name="selectCategory" >
						<c:forEach items="${requestScope.categories}" var="category">
							<option  <c:if test="${phone.phoneCategory.equals(category.categoryName)}"> selected </c:if>>
								${category.categoryName}
							</option>
						</c:forEach>
					</select><br>
				</td>
			</tr>
			<tr>
				<th> Vendor: </th>
				<td> <input type="text" name="txtVendor" value="${phone.vendor}"> </td>
			</tr>
			<tr>
				<th> Description: </th>
			 	<td> <input type="text" name="txtDescription" value=${phone.description }> </td>
		 	</tr>
		 	<tr>
		 		<th></th>
				<td><input type="submit" value="Save"></td>
			</tr>
		</table>
		
	</form>
</body>
</html>