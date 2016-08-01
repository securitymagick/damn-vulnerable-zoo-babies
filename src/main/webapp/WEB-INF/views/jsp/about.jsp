<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>About Zoo Babies</title>
 
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
	<h1>About Us</h1>
	<p>
		<c:if test="${not empty message}">
			${message}
		</c:if>
	</p>
	<p> Zoo Babies was founded by Rick Potter in 2015.  Rick's grandfather Merryweather Potter is of course the well known anthropologist who focused on early civilizations in the African Saharah.  Rick accompanied his grandfather on one of his trips to Africa when he was just 12 years old, and rescued a baby white rhino, whose mother had been killed by poachers on that first trip.  This spurred his love of Zoology and of baby animals.  Rick currently hold a doctrate in Zoology and is the founder of the Rick Potter Foundation, which focuses on stopping poachers in the African Saharah.  As a side project Rick like to share his love Zoo Babies with the world through this website.

    </p>
   
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