<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Nest Application</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Nest Application</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<p>
			Authorize Page
		</p>
	</div>
</div>

<div class="container">

	<form name="" method="post" action="/authorize">
		<div class="row">
		<div class="col-md-4">
			<h2>Nest Id</h2>
			<p><input type="text" name="auth" class="input-group"/></p>
			<p>
				<input class="btn btn-default" type="submit">
			</p>
		</div>
	</div></form>


	<hr>
	<footer>
	</footer>
</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>