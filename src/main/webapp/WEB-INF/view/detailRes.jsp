<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<%@ page contentType="text/html; charset=UTF-8"%>
<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

	<%@include file="menu_student.jsp"%>
	<div class="container">

		<div class="starter-template">
			<div class="page-header">
				<h1>Rexam</h1>

				<h2>
					<c:out value="Détail des notes -- ${tuname}" />
				</h2>
			</div>

			<div class="exam_details">

				<table id="exam_table" class="unitsTable table table-hover">
					<thead>
						<tr>
							<th>Epreuves</th>
							<th>Notes</th>
							<th>Poids</th>
							<th>Date d'obtention</th>
						</tr>
					</thead>

					<c:forEach items="${detailRes}" var="line">

						<tr>
							<td><c:out value="${line.typeExam}" /></td>
							<td><c:out value="${line.score}" /></td>
							<td><c:out value="${line.weight}" /></td>
							<td><c:out value="${line.dateObt}" /></td>
						</tr>

					</c:forEach>

				</table>
			</div>

		</div>
	</div>
</body>
</html>