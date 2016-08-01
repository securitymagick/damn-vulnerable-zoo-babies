<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Zoo Babies Admin Panel</title>
 
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
		<a class="btn btn-primary btn-lg" href="logs" role="button">View Logs</a>
	</p>
		
	</div>
</div>
 
<div class="container">
 
  <div class="row">
	<div class="col-md-4">
		<h2>Posts</h2>
		<p>Approve / Delete posts</p>
		<p>
			<a class="btn btn-default" href="posts" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Comments</h2>
		<p>Approve / Delete comments</p>
		<p>
			<a class="btn btn-default" href="comments" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Accounts</h2>
		<p>Update user accounts</p>
		<p>
			<a class="btn btn-default" href="accounts" role="button">View details</a>
		</p>
	</div>
  </div>
 <% } %>
 
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