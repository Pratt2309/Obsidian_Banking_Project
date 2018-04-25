<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account Statement</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form method="post">

		<table border="1">
			<tr>
				<h3>Account Summary</h3>
			</tr>
			<tr>
				<th align="center">Transaction ID</th>
				<th align="center">Date</th>
				<th align="center">From Account ID</th>
				<th align="center">To Account ID</th>
				<th align="center">Amount</th>
				<th align="center">Mode</th>
				<th align="center">Status</th>
				<th align="center">Transaction Type</th>
			</tr>
			<c:set var="count" value="${0}" scope="page" />
			<c:forEach var="tL" items="${requestScope.txnL}">
				<tr>
					<td><input type='text' name='txnId${count}'
						value="${tL.txnId}" readonly="true" align="center" /></td>

					<td><input type='text' name='date${count}' value="${tL.date}"
						readonly="true" align="center" /></td>

					<td><input type='text' name='fromAccountId${count}'
						value="${tL.fromAccountId}" readonly="true" align="center" /></td>

					<td><input type='text' name='toAccountId${count}'
						value="${tL.toAccountId}" readonly="true" align="center" /></td>

					<td><input type='text' name='amount${count}'
						value="${tL.amount}" readonly="true" align="center" /></td>

					<td><input type='text' name='mode${count}' value="${tL.mode}"
						length="" readonly="true" align="center" /></td>

					<td><input type='text' name='status${count}'
						value="${tL.status}" readonly="true" size="40" align="center" /></td>

					<td><input type='text' name='txnType${count}'
						value="${tL.txnType}" readonly="true" align="center" /></td>

					<c:set var="count" value="${count + 1 }" scope="page" />
				</tr>
			</c:forEach>
			<tr>
				<td><input name="ct" type="hidden" value="${count}" /></td>
			</tr>
		</table>
		<input type="submit" value="Export as Excel"
			formaction="${contextPath}/csv/export" />
	</form>

</body>
</html>