<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form>

		<table border="1">
			<tr>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Account Number</th>
			</tr>
			<c:set var="cL" value="${requestScope.cL}"></c:set>
			<c:set var="cD" value="${fn:split(cL, ' ')}"></c:set>
			<tr>
				<th>${cD[0]}</th>
				<th>${cD[1]}</th>
				<th>${cD[2]}</th>
				<th><button formaction="${contextPath}/cust/select"
						value="${cD[3]}" /></th>
			</tr>
		</table>

	</form>
</body>
</html>