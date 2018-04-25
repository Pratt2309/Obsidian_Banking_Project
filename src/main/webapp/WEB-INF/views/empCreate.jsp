<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function ajaxEvent() {
		try {
			var httpRequest = new XMLHttpRequest();

		} catch (e) {
		}
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState == 4) {

				var showdata = httpRequest.responseText;
				if (showdata.match("Unique Username!")) {
					document.getElementById("button").disabled = false;
				} else {
					document.getElementById("button").disabled = true;
				}
				coursediv.innerHTML = httpRequest.responseText;
			}
		}
		var queryString = document.getElementById("queryString").value;
		httpRequest.open("POST", "../ajaxServiceE.htm?username=" + queryString,
				true);
		httpRequest.send();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
	<form:form commandName="employee">
		<table>
			<tr>
				<td>Employee Type:</td>
				<td><form:input path="role" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required"
						pattern='([A-Z]{1}[a-z]{1,30})' /></td>
			</tr>

			<tr>
				<td>Middle Name:</td>
				<td><form:input path="middleName" size="30"
						pattern='([A-Z]{1}[a-z]{1,30})' /></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required"
						pattern='([A-Z]{1}[a-z]{1,30})' /></td>
			</tr>

			<tr>
				<td>Home Phone:</td>
				<td><form:input path="homePhone" size="30"
						pattern="^(?:(?:\+?1\s*(?:[.-]\s*)?)?(?:\(\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\s*\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\s*(?:[.-]\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\s*(?:[.-]\s*)?([0-9]{4})(?:\s*(?:#|x\.?|ext\.?|extension)\s*(\d+))?$" />
				</td>
			</tr>

			<tr>
				<td>Mobile Phone:</td>
				<td><form:input path="mobilePhone" size="30"
						required="required"
						pattern="^(?:(?:\+?1\s*(?:[.-]\s*)?)?(?:\(\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\s*\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\s*(?:[.-]\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\s*(?:[.-]\s*)?([0-9]{4})(?:\s*(?:#|x\.?|ext\.?|extension)\s*(\d+))?$" /></td>

			</tr>

			<tr>
				<td>Office Phone:</td>
				<td><form:input path="officePhone" size="30"
						pattern="^(?:(?:\+?1\s*(?:[.-]\s*)?)?(?:\(\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\s*\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\s*(?:[.-]\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\s*(?:[.-]\s*)?([0-9]{4})(?:\s*(?:#|x\.?|ext\.?|extension)\s*(\d+))?$" /></td>

			</tr>

			<tr>
				<td>Email(Primary):</td>
				<td><form:input path="email1" size="30" required="required"
						pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.(([0-9]{1,3})|([a-zA-Z]{2,3})|(aero|coop|info|museum|name))$" /></td>
			</tr>

			<tr>
				<td>Email(Secondary):</td>
				<td><form:input path="email2" size="30"
						pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.(([0-9]{1,3})|([a-zA-Z]{2,3})|(aero|coop|info|museum|name))$" /></td>
			</tr>

			<tr>
				<td>User Name:</td>
				<td><input type="text" name="username" id="queryString"
					pattern="[a-zA-Z0-9]{5,15}" size="30" onkeyup="ajaxEvent()"
					required="true" /></td>
				<td><div id="coursediv"></div></td>
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
				<td><form:input path="city" size="30" required="required"
						pattern='([A-Z]{1}[a-z]{1,30})' /></td>
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
				<td><form:input path="zipcode" size="30" required="required"
						pattern="([0-9]{5})" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" id="button" /></td>
			</tr>

		</table>
	</form:form>

</body>
</html>