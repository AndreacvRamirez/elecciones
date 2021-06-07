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
<title>Votante | ${votante == null ? "Registrar" : "Actualizar"}</title>
</head>
<body>

	<div class="container col-md-5 pt-5">

		<div class="card">

			<div class="card-body">

				<c:if test="${votante != null}">
					<form action="/votante?accion=updateVotante"  method="post">
				</c:if>

				<c:if test="${votante == null}">
					<form action="/votante?accion=insertVotante"  method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${votante != null}">Editar</c:if>
						<c:if test="${votante == null}">Agregar</c:if>
					</h2>
				</caption>

				<fieldset class="form-group" style="display: none">
					<label>ID</label> <input type="text"
						value="<c:out value='${votante.id}'/>" class="form-control"
						name="id" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Estamento</label> <select class="form-select"
						name="estamento">
						<option selected value="${votante != null ? "" : 1}">Seleccione
							Una</option>
						<c:forEach var="dd" items="${estamentos}">
							<option value='${dd.id}'>"${dd.descripcion}"</option>
						</c:forEach>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Tipo Documento</label> <select class="form-select"
						name="tipo">
						<option selected
							value="${votante != null ? votante.tipo_documento : ""}">${votante != null ? votante.tipo_documento : "Seleccione Una"}</option>
						<c:forEach var="dd" items="${tipos}">
							<option value='${dd.id}'>"${dd.descripcion}"</option>
						</c:forEach>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Documento</label> <input type="text"
						value="<c:out value='${votante.documento}'/>" class="form-control"
						name="documento" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Nombre</label> <input type="text"
						value="<c:out value='${votante.nombre}'/>" class="form-control"
						name="nombre" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${votante.email}'/>" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Proceso</label> <select class="form-select" name="procesos">
						<option selected value="${votante != null ? votante.eleccion : ""}">${votante != null ? votante.eleccion : "Seleccione Una"}</option>
						<c:forEach var="dd" items="${procesos}">
							<option value='${dd.id}'>"${dd.nombre}"</option>
						</c:forEach>
					</select>
				</fieldset>

				<button type="submit" class="btn btn-success">Enviar</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>