<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- CSS only -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>Votantes</title>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark">
			<div>
				<a href="#" class="navbar-brand">Gestion Votante</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Usuarios </a></li>
			</ul>
		</nav>
	</header>

	<div class="row">
		<div class="container">
			<h3 class="text-center">Candidatos</h3>
			<hr>

			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/newVotante" class="btn btn-success">
					Agregar</a>
			</div>

			<br>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Email</th>
						<th>Documento</th>
						<th>Tipo Documento</th>
						<th>Eleccion</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="usuario" items="${listVotante}">
						<tr>
							<td><c:out value="${usuario.id}"></c:out></td>
							<td><c:out value="${usuario.nombre}"></c:out></td>
							<td><c:out value="${usuario.email}"></c:out></td>
							<td><c:out value="${usuario.documento}"></c:out></td>
							<td><c:out value="${usuario.tipo_documento}"></c:out></td>
							<td><c:out value="${usuario.eleccion}"></c:out></td>

							<td><a href="showUpdateVotante?votante=<c:out value = '${usuario.id}' />">Editar
							</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteVotante?votante=<c:out value='${usuario.id}' />">Delete</a></td>
					</c:forEach>

				</tbody>

			</table>
		</div>
	</div>
</body>
</html>