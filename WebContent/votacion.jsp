<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/card.css"
	rel="stylesheet" type="text/css">
<title><c:out
		value="${votanteValidado ? votante.documento : '404'}" /></title>
</head>
<body>
	<c:choose>
		<c:when test="${votanteValidado}">
			<div class="cards">
				<c:forEach var="usuario" items="${listCandidato}" varStatus="loop">
					<div class="card"
						style="width: 500px; height: 400px; background-image: url(https://picsum.photos/400/1200?random=${loop.index + 1})">
						<div class="card__img"></div>
						<a href="#" class="card_link">
							<div class="card__img--hover"></div>
						</a>
						<div class="card__info">
							<span class="card__category">Candidato:</span>
							<h3 class="card__title">
								<c:out value="${usuario.nombre} ${usuario.apellido}"></c:out>
							</h3>
							<h3 class="card__title">
								<c:out value="#${usuario.numero}"></c:out>
							</h3>
							<span class="card__by"><a
								href="voto?accion=votar&&candidato=${usuario.id}&&usuario=${votante.documento}&&eleccion=${eleccionID}"
								class="card__author" title="author">Votar</a></span>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
	</c:choose>

</body>
</html>