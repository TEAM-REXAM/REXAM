<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="fr">
<head>
<%@ page contentType="text/html; charset=UTF-8"%>
<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/cupertino/jquery-ui.css">
</head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-confirmation/1.0.7/bootstrap-confirmation.min.js"></script>


<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>

	<c:choose>
		<c:when test="${role.equals('admin')}"><%@include
				file="menu_admin.jsp"%></c:when>
		<c:otherwise><%@include file="menu_student.jsp"%></c:otherwise>

	</c:choose>

	<div class="container">

		<div class="starter-template">
			<div class="page-header">
				<h1>Rexam</h1>

				<h2>
					Liste des UE par discipline
					<c:if test="${role.equals('admin')}">(ayants des inscriptions) </c:if>
					:
				</h2>
			</div>
			<c:if test="${not empty msg}">
				<div class="alert alert-${alert} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<c:choose>
				<c:when test="${ empty disciplines}">
					<div class="alert alert-danger">Aucun résultat trouvé !</div>
				</c:when>
				<c:otherwise>
					<div id="accordion">
						<c:forEach items="${disciplines}" var="discipline" varStatus="i">
							<h3>

								<c:out value="${discipline}" />
							</h3>

							<div>
								<table id="tu${i.index}" class="unitsTable table table-hover">
									<thead>
										<tr>
											<th>Nom</th>
											<th>Nb crédit</th>
											<th>Épreuves</th>
											<c:if test="${!role.equals('admin')}">
												<th>Actions</th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tuList}" var="tu">
											<c:if test="${discipline==tu.discipline }">
												<tr>
													<td><c:out value="${tu.name}" /></td>
													<td><c:out value="${tu.creditValue}" /></td>
													<td><a href="/rexam/showExams?code=${tu.code }">Détail
															des épreuves</a></td>
													<c:if test="${!role.equals('admin')}">
														<td><a data-toggle="confirmation"
															data-title="Confirmer l'inscription ?"
															data-btn-ok-label="Confirmer"
															data-btn-ok-class="btn-info"
															data-btn-cancel-label="Retour"
															href="/rexam/registration?code=${tu.code }"><button
																	class="btn-primary">S'inscrire</button></a></td>
													</c:if>
												</tr>

											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:forEach>

					</div>
				</c:otherwise>
			</c:choose>
		</div>

	</div>
	<script>
		$(function() {
			$("#accordion").accordion({
				collapsible : true,
				active : false,
				heightStyle : "content"

			});
		});

		$('[data-toggle=confirmation]').confirmation({
			rootSelector : '[data-toggle=confirmation]'
		});
	</script>
</body>
</html>