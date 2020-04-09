<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/library.jsp" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Phone</title>
</head>
<body>
	<%@include file="/include/menu.html" %>
	<form action="/PhoneShop/phone/add-phone" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th> Avatar: </th>
				<td> <input type="file" name="avatar" > </td>
			</tr>
			<tr>
				<th> Image 1: </th>
				<td> <input type="file" name="image1" > </td>
			</tr>
			<tr>
				<th> Image 2: </th>
				<td> <input type="file" name="image2" > </td>
			</tr>
			<tr>
				<th> Image 3: </th>
				<td> <input type="file" name="image3" > </td>
			</tr>
			<tr>
				<th> Name: </th>
				<td> <input type="text" name="txtName"></td>
			</tr>
			<tr>
				<th> Price: </th>
				<td> <input type='text' name='txtPrice' > </td>
			</tr>
			<tr>
				<th> Quantity: </th>
				<td><input type='number' name='txtQuantity' > </td>
			</tr>
			<tr>
				<th> Category: </th> 
				<td>
					<select name="selectCategory" >
						<c:forEach items="${requestScope.categories}" var="category">
							<option>
								${category.categoryName}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th> Shop </th>
				<td> 
					<select name="selectShop">
						<c:forEach items="${requestScope.shops}" var="shop">
							<option>${shop.shopName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th> Vendor: </th>
				<td>
					<select name="selectVendor" >
						<c:forEach items="${requestScope.vendors}" var="vendor">
							<option >
								${vendor.vendorName}
							</option>
						</c:forEach>
					</select><br> 
				</td>
			</tr>
			<tr>
				<th> Description: </th>
			 	<td> <input type="text" name="txtDescription" > </td>
		 	</tr>
		 	<tr>
				<td><input type="submit" value="Add"></td>
				<td><a href="/PhoneShop/phone/list-phone">Cancel</a></td>
			</tr>
		</table>
	</form>

</body>
</html>