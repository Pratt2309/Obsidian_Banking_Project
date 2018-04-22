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
	<form:form commandName="customer">
		<table>

			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Middle Name:</td>
				<td><form:input path="middleName" size="30" /></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Home Phone:</td>
				<td><form:input path="homePhone" size="30" /></td>
			</tr>

			<tr>
				<td>Mobile Phone:</td>
				<td><form:input path="mobilePhone" size="30"
						required="required" /></td>
			</tr>

			<tr>
				<td>Office Phone:</td>
				<td><form:input path="officePhone" size="30" /></td>
			</tr>

			<tr>
				<td>Email(Primary):</td>
				<td><form:input path="email1" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Email(Secondary):</td>
				<td><form:input path="email2" size="30" /></td>
			</tr>

			<tr>
				<td>Date of Birth:</td>
				<td><form:input path="DOB" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Street Address 1:</td>
				<td><form:input path="streetAddr1" size="30"
						required="required" /></td>
			</tr>

			<tr>
				<td>Street Address 2:</td>
				<td><form:input path="streetAddr2" size="30" /></td>
			</tr>

			<tr>
				<td>City:</td>
				<td><form:input path="city" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>State:</td>
				<td><form:input path="state" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Country:</td>
				<td><form:input path="country" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Zip Code:</td>
				<td><form:input path="zipcode" size="30" required="required" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>

		</table>
	</form:form>
</body>
</html>