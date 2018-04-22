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
	<form action="${contextPath}/emp/tb" method="post">
		<table>
			<tr>
				<td><h3>From Account Customer</h3></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="fromfname" size="30" /></td>
			</tr>

			<tr>
				<td>Middle Name</td>
				<td><input type="text" name="frommname" size="30" /></td>
			</tr>

			<tr>
				<td>Last Name</td>
				<td><input type="text" name="fromlname" size="30" /></td>
			</tr>


		</table>

		<table>
			<tr>
				<td><h3>To Account Customer</h3></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="tofname" size="30" /></td>
			</tr>

			<tr>
				<td>Middle Name</td>
				<td><input type="text" name="tomname" size="30" /></td>
			</tr>

			<tr>
				<td>Last Name</td>
				<td><input type="text" name="tolname" size="30" /></td>
			</tr>


		</table>
		<input type="submit" value="Search Customer" />

	</form>

</body>
</html>