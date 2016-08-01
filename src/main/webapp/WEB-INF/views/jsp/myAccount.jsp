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
		<a class="navbar-brand" href="/../${pageContext.request.contextPath}">Zoo Babies</a>
	</div>
  </div>
</nav>
 
<div class="jumbotron">
  <div class="container">
	<h1>${title}</h1>
	<p>
		<c:if test="${not empty message}">
			<font color="red">
			${message}
			</font>
		</c:if>
	</p>	
	<% 
		Boolean showAdmin = false;
		CookieHandler p = new CookieHandler("permissions");
		if (p.checkForCookie(request)) {
			Cookie c = p.getCookie();
			Permissions userPermissions = new Permissions(c.getValue());
			if (userPermissions.getAdmin()) {
				showAdmin = true;
			}
		}
		if (showAdmin) {
	%>
    <p>
		<a class="btn btn-primary btn-lg" href="admin" role="button">Administration Menu</a>
	</p>
		<% } %>
	</div>
</div>
 
<div class="container">
 
  <div class="row">
	<div class="col-md-4">
		<h2>Add</h2>
		<p>Upload new pictures of Zoo Babies!</p>
		<p>
			<a class="btn btn-default" href="addZooBaby" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Update</h2>
		<p>Change your account setting and preferences</p>
		<p>
			<a class="btn btn-default" href="updateAccount" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Public</h2>
		<p>Go to the public area to see others latest posts</p>
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