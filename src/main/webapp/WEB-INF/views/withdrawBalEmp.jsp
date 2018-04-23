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
	<form action="${contextPath}/emp/wb" method="post">
		<table>
			<tr>
				<td>ID Number:</td>
				<td><input type="text" name="idnum" size="30" /></td>
			</tr>

			<tr>
				<td>ID Type:</td>
				<td><select name="idtype">
						<option value="NONE">--- Select ---</option>
						<c:forEach var="item" items="${requestScope.idList}">
							<option>${item}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>Account ID:</td>
				<td><input type="text" name="acctid" size="30"
					value="${requestScope.acctId}" /></td>
			</tr>

			<tr>
				<td>Amount:</td>
				<td><input type="text" name="amount" size="30" /></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Withdraw Balance" /></td>
			</tr>
		</table>


	</form>
</body>
</html>