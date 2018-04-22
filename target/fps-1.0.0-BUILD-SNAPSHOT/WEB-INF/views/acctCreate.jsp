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
<form:form commandName="account">
<table>
		<tr>
		    <td>Account Type:</td>
		    <td><form:input path="accountType" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Current Balance</td>
		    <td><form:input path="currentBalance"  size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td>Customer ID:</td>
		    <td><form:input path="custID" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td>Interest Rate:</td>
		    <td><form:input path="interestRate" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>



</form:form>

</body>
</html>