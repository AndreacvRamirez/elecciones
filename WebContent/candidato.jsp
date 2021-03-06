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
<title>${candidato == null ? "Registrar" : "Actualizar"}
	Candidato</title>
</head>
<body>

	<div class="container col-md-5 pt-5">

		<div class="card">

			<div class="card-body">

				<c:if test="${candidato != null}">
					<form action="candidato?accion=updateCandidato" method="post">
				</c:if>

				<c:if test="${candidato == null}">
					<form action="candidato?accion=insertCandidato" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${candidato != null}">Editar Candidato</c:if>
						<c:if test="${candidato == null}">Agregar Candidato</c:if>
					</h2>
				</caption>

				<fieldset class="form-group">
					<label>ID</label> <input type="text"
						value="<c:out value='${candidato.id}'/>" class="form-control"
						name="id" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Documento</label> <input type="text"
						value="<c:out value='${candidato.documento}'/>"
						class="form-control" name="documento" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Nombre</label> <input type="text"
						value="<c:out value='${candidato.nombre}'/>" class="form-control"
						name="nombre" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Apellido</label> <input type="text"
						value="<c:out value='${candidato.apellido}'/>"
						class="form-control" name="apellido" required="required">
				</fieldset>


				<fieldset class="form-group">
					<label>Elecci?n</label> <select class="form-select" name="eleccion">
						<option selected
							value="${candidato != null ? candidato.eleccion : ""}">${candidato != null ? candidato.eleccion : "Seleccione Una"}</option>
						<c:forEach var="dd" items="${listEleccion}">
							<option value='${dd.id}'><c:out value='${dd.nombre}' /></option>
						</c:forEach>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Numero</label> <input type="text"
						value="<c:out value='${candidato.numero}'/>" class="form-control"
						name="numero">
				</fieldset>
				<button type="submit" class="btn btn-success">Enviar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>