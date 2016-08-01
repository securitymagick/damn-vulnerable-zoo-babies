<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Add a New Zoo Baby</title>
 
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
	</br></br><
	<p>
		<c:if test="${not empty message}">
			</br></br>
			<font color="red">
			${message}
			</font>
		</c:if>
	</p>
	<h1>Add New Zoo Baby</h1>

	<c:if test="${not empty part1}">
	<form id="fm1" enctype="multipart/form-data" method="post" action="addZooBaby?uploadfile=yes">
	<div class="row">
		<div class="col-md-4">
			<p> Select Image file to upload: </p>
		</div>
		<div class="col-md-4">
			<p> <input type="file" name="file"  value="" /> </p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
		<p> 
			<button class="btn btn-primary btn-lg" type="submit" value="Submit" >Upload</button> 
		</p>
		</div>
	</div>
    </form>
	</c:if>
	<c:if test="${not empty part2}">
	<br/><img src="/zoo-babies-1.0-SNAPSHOT/resources/core/images/${imageName}"/>
	<form:form id="fm2" modelAttribute="zooBabyForm" method="post" action="addZooBaby?createpost=yes">
	<div class="row">
		<div class="col-md-4">
			<p> Enter Fun Descriptive Title: </p>
		</div>
		<div class="col-md-4">
			<p> <form:input type="text" name="title" path="title"  value="" /> </p>
			    <form:input type="hidden" name="author" path="author" value="${user}" />
				 <form:input type="hidden" name="imageName" path="imageName" value="${imageName}" />
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
		<p> 
			<button class="btn btn-primary btn-lg" type="submit" value="Submit" >Submit</button> 
		</p>
		</div>
	</div>
    </form:form>
	</c:if>
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