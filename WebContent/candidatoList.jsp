<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- CSS only -->
<link rel="stylesheet" href="resources/css/table.css">
<title>Candidatos</title>
</head>
<body>


	<div class="row">
		<div class="container">
			<div class="table-title">
				<h3>Candidatos</h3>
			</div>

			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/newCandidato" class="btn btn-success">
					Agregar</a>
			</div>

			<br>

			<table class="table-fill">
				<thead>
					<tr>
						<th class="text-left">ID</th>
						<th class="text-left">Documento</th>
						<th class="text-left">Nombre</th>
						<th class="text-left">Apellido</th>
						<th class="text-left">Eleccion</th>
						<th class="text-left">Numero</th>
						<th class="text-left">Opciones</th>
					</tr>
				</thead>

				<tbody class="table-hover">

					<c:forEach var="usuario" items="${listCandidato}">
						<tr>

							<td class="text-left"><c:out value="${usuario.id}"></c:out></td>

							<td class="text-left"><c:out value="${usuario.documento}"></c:out></td>

							<td class="text-left"><c:out value="${usuario.nombre}"></c:out></td>

							<td class="text-left"><c:out value="${usuario.apellido}"></c:out></td>
							<td class="text-left"><c:out value="${usuario.eleccion}"></c:out></td>
							<td class="text-left"><c:out value="${usuario.numero}"></c:out></td>

							<td class="text-left"><a href="editCandidato?id=<c:out value = '${usuario.id}' />">Editar
							</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteCandidato?id=<c:out value='${usuario.id}' />">Eliminar</a></td>
					</c:forEach>

				</tbody>

			</table>
		</div>
	</div>
</body>
</html>