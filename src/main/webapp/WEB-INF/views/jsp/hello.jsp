<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Zoo Babies</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Zoo Babies</a>
	</div>
  </div>
</nav>
 
<div class="jumbotron">
  <div class="container">
	<h1>${title}</h1>
	<p>
		<c:if test="${not empty message}">
			${message}
		</c:if>
 
		<c:if test="${empty message}">
			Welcome!
		</c:if>
    </p>
    <p/>
	<div class="row">
		<div class="col-md-4">
			<a class="btn btn-primary btn-lg" href="about" role="button">Learn more about our company</a>
		</div>
	
	<% 
		Boolean showAlreadyLoggedIn = false;
		CookieHandler p = new CookieHandler("permissions");
		if (p.checkForCookie(request)) {
			Cookie c = p.getCookie();
			Permissions userPermissions = new Permissions(c.getValue());
			if (userPermissions.getUser()) {
				showAlreadyLoggedIn = true;
			}
		}
		if (showAlreadyLoggedIn) {
	%>
	<div class="col-md-4">
	</div>
	<div class="col-md-4">
		<a class="btn btn-primary btn-lg" href="myAccount" role="button">Go to your account.</a>
	</div>
	
	<% } %>	
	</div>
	</div>
</div>
 
<div class="container">
 
  <div class="row">
	<div class="col-md-4">
		<h2>Login</h2>
		<p>To upload and comment on pictures</p>
		<p>
			<a class="btn btn-default" href="login" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Register</h2>
		<p>Create a new account to upload and comment on pictures</p>
		<p>
			<a class="btn btn-default" href="register" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Public</h2>
		<p>Go to the public area without an account</p>
		<p>
			<a class="btn btn-default" href="public" role="button">View details</a>
		</p>
	</div>
  </div>
 
 
  <hr>
  <footer>
		<p>&copy; Zoo Babies 2016</p>
  </footer>
</div>
 
<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 
</body>
</html>