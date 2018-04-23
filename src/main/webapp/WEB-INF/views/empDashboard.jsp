<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Dashboard</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<form>
<button formaction="${contextPath}/emp/tb">Transfer Balance</button>
<button formaction="${contextPath}/emp/csw">Withdraw Balance</button>
<button formaction="${contextPath}/emp/csd">Deposit Funds</button>
<button formaction="${contextPath}/emp/cs">Customer Summary</button>
<button formaction="${contextPath}/emp/cc">Customer Creation</button>
</form>
</body>
</html>