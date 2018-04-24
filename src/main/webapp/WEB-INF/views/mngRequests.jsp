<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Requests</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/mgr/mr" method="post">
		<c:if test="${(requestScope.rL != null)}">
			<table border="1">
				<tr>
					<th>Request ID</th>
					<th>Account ID</th>
					<th>Amount</th>
					<th>Transaction ID</th>
					<th colspan="2">Action</th>
				</tr>
				<c:forEach var="rL" items="${requestScope.rL}">

					<tr>
						<td><input type="radio" name="reqId" value="${rL.reqID}" />${rL.reqID}</td>
						<td><input size="30" name="acctId"
							value="${rL.account.accountId}" readonly /></td>
						<td><input size="30" name="amount" value="${rL.amount}"
							readonly /></td>
						<td><input size="30" name="txnId" value="${rL.txn.txnId}"
							readonly /></td>
						<td><button name="action" value='Approve'>Approve</button></td>
						<td><button name="action" value='Decline'>Decline</button></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</form>
</body>
</html>