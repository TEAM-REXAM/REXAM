
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<%@ page contentType="text/html; charset=UTF-8"%>
<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script>
	$("h2").click(function() {
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


					<li class="active"><a href="/showTeachingUnits">Liste des
							UE</a></li>
				</ul>

				<form class="navbar-form navbar-right" action="/action_page.php">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search...">
					</div>
				</form>


			</div>
		</div>
	</nav>
	<div class="container">

		<div class="starter-template">
			<h1>Rexam</h1>

			<h2>Liste des UE par discipline :</h2>
			<c:forEach items="${disciplines}" var="discipline">

				<h3>
					<c:out value="${discipline}" />
				</h3>

				<table class="table table-hover">
					<tr>
						<th>Nom</th>
						<th>Nb crédit</th>
						<th>Épreuves</th>
						<th>Actions</th>
					</tr>
					<c:forEach items="${teachingUnits}" var="tu">
						<c:if test="${discipline==tu.discipline }">
							<tr>
								<td><c:out value="${tu.name}" /></td>
								<td><c:out value="${tu.creditValue}" /></td>
								<td><a href="#">Détail des épreuves</a></td>
								<td><button>S'inscrire</button></td>
							</tr>

						</c:if>
					</c:forEach>
				</table>
			</c:forEach>

		</div>
	</div>
</body>
</html>