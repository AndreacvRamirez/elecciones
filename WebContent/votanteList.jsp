<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- CSS only -->

<link rel="stylesheet" href="resources/css/table.css">
<title>Votantes</title>
<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
</head>
<body>

	<div class="row">
		<div class="container">
			<div class="table-title">
				<h3>Votantes</h3>
			</div>
			<div class="container text-left">
				<a class="boton_personalizado" href="<%=request.getContextPath()%>/votante?accion=newVotante" class="btn btn-success">
					Agregar</a>
			</div>

			<table class="table-fill">
				<thead>
					<tr>
						<th class="text-left">ID</th>
						<th class="text-left">Nombre</th>
						<th class="text-left">Email</th>
						<th class="text-left">Documento</th>
						<th class="text-left">Tipo Documento</th>
						<th class="text-left">Eleccion</th>
						<th class="text-left">Opciones</th>
					</tr>
				</thead>

				<tbody class="table-hover">

					<c:forEach var="usuario" items="${listVotante}">
						<tr>
							<td class="text-left"><c:out value="${usuario.id}"></c:out></td>
							<td class="text-left"><c:out value="${usuario.nombre}"></c:out></td>
							<td class="text-left"><c:out value="${usuario.email}"></c:out></td>
							<td class="text-left"><c:out value="${usuario.documento}"></c:out></td>
							<td class="text-left"><c:out value="${usuario.tipo_documento}"></c:out></td>
							<td class="text-left"><c:out value="${usuario.eleccion}"></c:out></td>

							<td class="text-left"><a href="votante?accion=showUpdateVotante&&votante=<c:out value = '${usuario.id}' />">Editar
							</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
								href="votante?accion=deleteVotante&&votante=<c:out value='${usuario.id}' />">Eliminar</a></td>
					</c:forEach>

				</tbody>

			</table>
		</div>
	</div>
</body>
</html>