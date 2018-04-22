<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>obsidian.com</title>
<style>
#top_nav li:hover ul {
	display: block;
}

#top_nav {
	display: block;
	position: relative;
	text-align: center;
	border: 1px solid rgb(145, -206, -250);
	font: bold 18px Sans-serif;
	height: 40px;
	width: 1000px;
	margin: 0px auto;
}

#top_nav ul {
	margin: 0px;
	padding: 0px;
	display: inline-block;
}

#top_nav li {
	position: relative;
	float: left;
	list-style-type: none;
}

#top_nav ul:after {
	content: ".";
	display: block;
	height: 0px;
	clear: both;
	visibility: hidden;
}

#top_nav li a {
	text-decoration: none;
	direction: block;
	color: black;
	border-right: 1px solid rgb(230, 100, 0);
	padding: 10px 25px;
}

#top_nav ul ul {
	position: absolute;
	display: none;
	left: 0px;
	background: rgb(250, 150, 0);
}

#top_nav ul ul li {
	border: 1px solid rgb(230, 100, 0);
	width: 99%;
}

#top_nav ul ul li a {
	border-right: none;
}
</style>
</head>
<center>
	<body>
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<div id="top_nav">
			<ul>
				<li><a href="#">HOME</a></li>
				<li><a href="#">SERVICES</a>
					<ul>
						<li><a href="#">Loans</a></li>
						<li><a href="#">Deposits</a></li>
						<li><a href="#">Collateral</a></li>
						<li><a href="#">Syndication</a></li>
					</ul></li>
				<li><a href="#">Login</a>
					<ul>
						<li><a href="${contextPath}/customer/login.htm">Customer</a></li>
						<li><a href="${contextPath}/employee/login.htm">Employee</a></li>

					</ul></li>
				<li><a href="#">ABOUT US</a></li>
				<li><a href="#">CONTACT US</a></li>
				<li><a href="#">PRIVACY POLICY</a></li>
			</ul>
		</div>

	</body>
	<body>
		<p>
			<mark> Obsidian</mark>
			enables transformation in banking services through its holistic suite
			of solutions. A market-ready universal retail banking platform, each
			solution in the suite runs as a scalable component, fully integrated
			with existing business models and enterprise and technology
			infrastructures.
		</p>
		<figure>
			<img
				src="/resources/obs1.jpg"
				width="500px" height="500px" align='centre'>
			<figcaption>Fig. Obsidian logo</figcaption>

		</figure>

		&nbsp;
		<br> &nbsp;
		<br> &nbsp;
		<br> &nbsp;
		<br> &nbsp;
		<br>

		<footer> Copyright &copy 2018 All Right Reserved:
			obsidian.com </footer>

	</body>

</center>
</html>