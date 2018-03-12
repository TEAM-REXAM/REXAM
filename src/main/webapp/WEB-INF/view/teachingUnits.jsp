
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script>
	$("button").click(function() {
		$("#tu_list").slideToggle();
	});
</script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">Rexam</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Index</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li class="active"><a href="/showTeachingUnits">Liste des
							UE</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">

		<div class="starter-template">
			<h1>Rexam</h1>
			<ul class="list-group">

				<c:forEach items="${disciplines}" var="discipline">

					<li class="list-group-item">
						<button>
							<c:out value="${discipline}" />
						</button>
					</li>

					<ul class="tu_list">
						<c:forEach items="${disciplines}" var="tu">

						</c:forEach>
					</ul>


				</c:forEach>

			</ul>

		</div>
	</div>
</body>
</html>