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
	<form:form commandName="idDetails" method="post">

		<table>
			<tr>
				<td>ID Type:</td>
				<td><form:select path="idType">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${requestScope.idList}" />
					</form:select></td>
			</tr>

			<tr>
				<td>ID Number:</td>
				<td><form:input path="idNum" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Customer ID:</td>
				<td><form:input path="customer.custId" size="30"
						required="required" value="${requestScope.custId}" /></td>
			</tr>

			<tr>
				<td>ID Expiration Date:</td>
				<td><form:input path="idExpDate" size="30" required="required" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>

		</table>

	</form:form>
</body>
</html>