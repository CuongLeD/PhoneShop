<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@include file="/include/library.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<%
	java.util.List<Category> categories = new CategoryDAOImpl().getElements(0, new CategoryDAOImpl().amountRows());
	request.setAttribute("categories", categories);
	
	%>
	<c:set var = "s1" value="Samsung"  />
	<select name="selectCategory" >
						<c:forEach items="${requestScope.categories}" var="category">
							<option  <c:if test="${'Samsung'== category.categoryName}"> <c:out value="${s1}"/> </c:if>>
								 <c:out value="${s1}"/>
								${category.categoryName}
								
							</option>
						</c:forEach>
					</select><br>
</body>
</html>