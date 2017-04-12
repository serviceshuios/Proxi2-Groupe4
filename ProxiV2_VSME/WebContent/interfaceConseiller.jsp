<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Interface Conseiller</title>
</head>
<body>
	<H1>Interface Conseiller</H1>
	<c:out value="${sessionScope.login}"></c:out>
	<br />
	<a href="GestionConseiller?action=Deconnection">Déconnection</a>
	<br />
	<c:if test="${!empty requestScope.resultatvalidation}">
		<c:out value="${requestScope.resultatvalidation}">
		</c:out>
		<c:remove var="requestScope.resultatvalidation" />
		<br />
	</c:if>
	<form method="post" action="GestionConseiller?action=AjouterClient">
		<input type="submit" name="ajouterclient" value="ajouter un client" /><br />
	</form>



</body>
</html>