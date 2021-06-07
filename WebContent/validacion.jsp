<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand">Gestión Votante</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"> Votantes </a></li>
			</ul>
		</nav>
	</header>

	<br>

	<div class="container col-md-5">

		<div class="card">

			<div class="card-body">

				<form action="validarVotante" method="get">
				
				<fieldset class="form-group">
					<label>Tipo Documento</label> <select class="form-select" name="tipo">
						<option selected>Seleccione Una</option>
						<c:forEach var="dd" items="${tipos}">
							<option value='${dd.id}'>"${dd.descripcion}"</option>
						</c:forEach>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Documento</label> <input type="text"
						name="documento" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Nombre</label> <input type="text"
						class="form-control" name="nombre" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						name="email" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Enviar</button>

				</form>
			</div>
		</div>
	</div>
	
</body>
</html>