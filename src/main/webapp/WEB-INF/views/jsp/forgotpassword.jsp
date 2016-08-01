<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Zoo Babies Login</title>
 
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
	</br></br>
	<h1>Forgot Your Password?</h1>
	<p>
		<c:if test="${not empty message}">
			<font color="red">
			${message}
			</font>
		</c:if>
	</p>	
	<form:form id="fm1" modelAttribute="usernameForm" method="post" action="forgotpassword">
	<div class="row">
		<div class="col-md-4">
			<p> Username: </p>
		</div>
		<div class="col-md-4">
			<p> <form:input type="text" name="username" path="username" value="" /> </p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
		<p> 
			<button class="btn btn-primary btn-lg" type="submit" value="Submit" >Login</button> 
		</p>
		</div>
	</div>
    </form:form>
	</br></br>

	
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