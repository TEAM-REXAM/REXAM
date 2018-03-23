<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
   prefix="form"
   uri="http://www.springframework.org/tags/form"%>
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
					<li class=""><a href="/">Index</a></li>


					<li class=""><a href="/showTeachingUnits">Liste des
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
	<h3><c:if test="${!results.examResults.isEmpty()}"><c:out value="    Code de l'epreuve : ${results.examResults.get(0).exam.code}"></c:out></c:if></h3>
	<div class="container">

		<div class="starter-template">
				<table class="table table-hover">
					<tr>
						<th>Prénom</th>
						<th>Nom</th>
						<th>Note</th>
						<th>Date d'obtention</th>
					</tr>
					
					  <form:form method="POST" modelAttribute="results" action="editResults">
						<c:forEach items="${results.examResults}" varStatus="resStat" var ="res">
							<tr>
								<td><c:out value="${res.studentYear.student.firstname}" /></td>
								<td><c:out value="${res.studentYear.student.lastname}" /></td>
								<td><form:input path="examResults[${resStat.index}].score"/></td>
								<td><form:input path="examResults[${resStat.index}].dateObtened" type ="date"/></td>
							</tr>
						</c:forEach>
               <tr><td style="border-top:none;"><input type="submit" value="Confirmer" id="form_button" /></td></tr>
						</form:form>
				</table>
		</div>
	</div>
</body>
</html>