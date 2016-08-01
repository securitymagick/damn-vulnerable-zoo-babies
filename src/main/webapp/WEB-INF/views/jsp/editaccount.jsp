<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Edit User Account</title>
 
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
 
  <div class="container">
	<br/><br/>
	<h1>${user.username}</h1>
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
	<br/>
	<br/>
	<form:form id="fm1" modelAttribute="userToEdit" method="post" action="editaccount">
	<form:input type="hidden" name="id" path="id" value="${user.id}" />
	<div class="row">
		<div class="col-md-4">
			<p> Favorite Zoo Baby: </p>
		</div>
		<div class="col-md-4">
			<p> <form:input type="text" name="favorite" path="favorite"  value="${user.favorite}" /> </p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<p> Password: </p>
		</div>
		<div class="col-md-4">
			<p> <form:input type="text" name="password" path="password"  value="${user.password}" /> </p>
		</div>
	</div>	
	<div class="row">
		<div class="col-md-4">
			<p> User Status </p>
		</div>
		<div class="col-md-4">
			<p> <form:input type="text" name="isUser" path="isUser"  value="${user.isUser}" /> </p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<p> Admin Status </p>
		</div>
		<div class="col-md-4">
			<p> <form:input type="text" name="isAdmin" path="isAdmin"  value="${user.isAdmin}" /> </p>
		</div>
	</div>	
	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
		<p> 
			<button class="btn btn-primary btn-lg" type="submit" value="Submit" >Update</button> 
		</p>
		</div>
	</div>	
	</form:form>
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