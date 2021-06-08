<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet" type="text/css">
<title>Validar Votante</title>
</head>
<body>

	<div class="login-wrap">
		<div class="login-html">
			<div class="card-body">
				<form action="votante" method="post">
					<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
						for="tab-1" class="tab">Validar Datos</label> <input id="tab-2" style="display:none"
						type="radio" name="tab" class="sign-up"><label for="tab-2" style="display:none"
						class="tab">Registrarse</label>

					<div class="login-form">
						<div class="sign-in-htm">
							<div class="group">
								<label class="label">Tipo Documento</label> 
								<select class="form-select" name="tipo">
									<option selected>Seleccione Una</option>
									<c:forEach var="dd" items="${tipos}">
										<option value='${dd.id}'><c:out
												value='${dd.descripcion}' /></option>
									</c:forEach>
								</select>
							</div>

							<div class="group">
								<label class="label">Documento</label> 
								<input type="text" name="documento"  class="form-control" required="required">
							</div>

							<div class="group">
								<label class="label">Nombre</label> 
								<input type="text" class="form-control" name="nombre" required="required">
							</div>

							<div class="group">
								<label class="label">Email</label> <input type="text" name="email"
									required="required" class="form-control">
							</div>
							<div class="group" style="display:none">
								<input type="text" name="accion" value="validarVotante">
							</div>
							<div class="group">
							<button type="submit" class="btn btn-success">Enviar</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>