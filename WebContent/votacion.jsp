<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
		<c:choose>
	  		<c:when test="${votanteValidado}">
				<c:forEach var="usuario" items="${listCandidato}">
					<h1>"${usuario.nombre}"</h1>
					<a>Votar</a>
				</c:forEach>
			</c:when>
		</c:choose>
	
</body>
</html>