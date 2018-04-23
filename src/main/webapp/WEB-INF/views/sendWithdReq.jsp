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
	<form method="post" action="${contextPath}/emp/swr">
		<table>
			<tr>
				<th>Manager First Name:</th>
				<th>Manager Middle Name:</th>
				<th>Manager Last Name:</th>
				<th>Manager ID:</th>
			</tr>
			<c:forEach var="eL" items="${requestScope.eL}">
				<tr>
					<td>${eL.firstName}</td>
					<td>${eL.middleName}</td>
					<td>${eL.lastName}</td>
					<td><input type="radio" name="mngrId" value="${eL.empId}" />${eL.empId}</td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<td>Amount</td>
				<td><input size="30" name="amount" required="required"
					value="${requestScope.amount}" /></td>
			</tr>

			<tr>
				<td>Account ID:</td>
				<td><input size="30" name="accountId" required="required"
					value="${requestScope.acctId} " /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Send Request" /></td>
			</tr>

		</table>

	</form>
</body>
</html>