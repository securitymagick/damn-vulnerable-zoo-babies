<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.securitymagick.domain.Permissions" %>
<%@ page import="com.securitymagick.web.cookie.CookieHandler" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Public Zoo Babies Posts</title>
 
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
	<h1>${post.title}</h1>
	<p>
		<c:if test="${not empty message}">
			<font color="red">
			${message}
			</font>
		</c:if>
	</p>
	<br/><img src="/zoo-babies-1.0-SNAPSHOT/resources/core/images/${post.imageName}"/>
	<br/><h3> Posted by ${post.author} <h3>
	<br/>
	<br/>
	<h1> Comments </Comments>
	<c:forEach items="${post.comments}" var="comment">
		<p> ${comment} </p>
    </c:forEach>
		<% 
		Boolean showAddComment = false;
		CookieHandler p = new CookieHandler("permissions");
		if (p.checkForCookie(request)) {
			Cookie c = p.getCookie();
			Permissions userPermissions = new Permissions(c.getValue());
			if (userPermissions.getUser()) {
				showAddComment = true;
			}
		}
		if (showAddComment) {
	%>
	<h2>Add a new Comment </h2>
	<form:form id="fm1" modelAttribute="postComment" method="post" action="post">
	<div class="row">
		<div class="col-md-4">
			<p> Comment: </p>
		</div>
		<div class="col-md-4">
			<p> <form:input type="text" name="comment" path="comment" value="" /> </p>
		</div>
	</div>
	<form:input type="hidden" name="postid" path="postid" value="${post.id}" />
    <form:input type="hidden" name="username" path="username" value="${user}" />	
	
	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
		<p> 
			<button class="btn btn-primary btn-lg" type="submit" value="Submit" >Add Comment</button> 
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