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
	<form action="${contextPath}/emp/csw" method="post">
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="fname" size="30" /></td>
			</tr>

			<tr>
				<td>Middle Name</td>
				<td><input type="text" name="mname" size="30" /></td>
			</tr>

			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lname" size="30" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Search Customer" /></td>
			</tr>
		</table>

		<c:if test="${(requestScope.cL != null)}">
			<table border="1">
				<tr>
					<th>First Name</th>
					<th>Middle Name</th>
					<th>Last Name</th>
					<th>Customer Number</th>
					<th>Account Number</th>

				</tr>
				<c:forEach var="cL" items="${requestScope.cL}">

					<tr>
						<th>${cL.fName}</th>
						<th>${cL.mName}</th>
						<th>${cL.lName}</th>
						<th>${cL.custId}</th>
						<th><button formaction="${contextPath}/emp/wb"
								formmethod="get" name="acctId" value="${cL.acctId}">${cL.acctId}</button></th>

					</tr>
				</c:forEach>
			</table>
		</c:if>



	</form>

</body>
</html>