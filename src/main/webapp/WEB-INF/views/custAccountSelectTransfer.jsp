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
	<form action="${contextPath}/emp/tbal" method="post">

		<table border="1">
			<tr>
				<h3>From Account Customer Details</h3>
			</tr>
			<tr>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Customer ID</th>
				<th>Account Number</th>
				<th>Select</th>
			</tr>
			<c:forEach var="fcL" items="${requestScope.fromcL}">

				<tr>
					<td>${fcL.fName}</td>
					<td>${fcL.mName}</td>
					<td>${fcL.lName}</td>
					<td>${fcL.custId}</td>
					<td>${fcL.acctId}</td>
					<td><input type="radio" name="facctIdSelect"
						value="${fcL.acctId}" /></td>
				</tr>
			</c:forEach>
		</table>

		<table border="1">
			<tr>
				<h3>To Account Customer Details</h3>
			</tr>
			<tr>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Customer ID</th>
				<th>Account Number</th>
				<th>Select</th>
			</tr>
			<c:forEach var="tcL" items="${requestScope.tocL}">

				<tr>
					<td>${tcL.fName}</td>
					<td>${tcL.mName}</td>
					<td>${tcL.lName}</td>
					<td>${tcL.custId}</td>
					<td>${tcL.acctId}</td>
					<td><input type="radio" name="tacctIdSelect"
						value="${tcL.acctId}" /></td>
				</tr>
			</c:forEach>

			<tr>
				<td>Transfer Amount</td>
				<td><input type="text" name="tamount" /></td>
			</tr>
		</table>
		<input type="submit" value="Select Customer" />
	</form>
</body>
</html>