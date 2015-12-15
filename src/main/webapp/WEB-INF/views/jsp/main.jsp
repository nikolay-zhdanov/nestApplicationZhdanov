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
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
		</p>
	</div>
</div>

<div class="container">

	<form action="/values" method="post" name="nest">

	<div class="row">
		<div class="col-md-4">
			<h2>Fun</h2>
			<p>
				Off<input type="radio" name="fun" value="off" <c:if test="${'off' eq fun}"> checked </c:if>>
				On<input type="radio" name="fun" value="on" <c:if test="${'on' eq fun}"> checked </c:if>>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Low temperature</h2>
			<p>
				<input type="number" name="low_temp" value="${low_temp}">
			</p>
		</div>
		<div class="col-md-4">
			<h2>High temperature</h2>
			<p>
				<input type="number" name="high_temp" value="${high_temp}">
			</p>
		</div>
		<input type="submit" class="btn btn-success">
	</div>
		<input type="hidden" name="auth" value="${auth}">
	</form>


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