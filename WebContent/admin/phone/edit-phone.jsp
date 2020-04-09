<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/library.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Edit Phone</title>
</head>
<body>
	<%@include file="/include/menu.html" %>

	<h1>Phone information: <c:out value="${phone.phoneName}" /> </h1><br>
	
	<form action='/PhoneShop/phone/edit-phone?id=${phone.phoneId }' method='post' >
		<table>
			<tr>
				<th>Avatar:</th>
				<td>
					<c:forEach items="${phone.imageLinks}" var="imageLink" >
						<c:if test="${imageLink.isAvatar() }">
							<img alt="avatar" src="${imageLink.imageLink }" height="200" width="200">
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>Images:</th>
				<td>
					<c:forEach items="${phone.imageLinks}" var="imageLink" >
							<img  alt="avatar" src="${imageLink.imageLink }" height="100" width="100">
						
						
					</c:forEach>
				</td>
			</tr>
			
			<tr>
				<th>ID: </th>
				<td> <label for="labPhoneId"> ${phone.phoneId }</label> </td>
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
				<th> Shop: </th>
				<td>
					<select name="selectShop">
						<c:forEach items="${requestScope.shops}" var="shop">
							<option <c:if test="${phone.shopName eq shop.shopName }"> selece</c:if> >
							 	${shop.shopName} 
							 </option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th> Vendor: </th>
				<td>
					<select name="selectVendor">
						<c:forEach items="${requestScope.vendors}" var="vendor">
							<option <c:if test="${phone.vendor eq vendor.vendorName}"> select</c:if> >
									 ${vendor.vendorName} 
							 </option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th> Description: </th>
			 	<td> <input type="text" name="txtDescription" value=${phone.description }> </td>
		 	</tr>
		 	<tr>
				<td><input type="submit" value="Edit"></td>
				<td><a href ="/PhoneShop/phone/list-phone">Cancel</a></td>
			</tr>
		</table>
		
	</form>
</body>
</html>