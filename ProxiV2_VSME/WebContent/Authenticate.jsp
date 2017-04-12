<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Authenticate</title>
</head>
<body>

	<c:if test="${sessionScope.attemptsCount > 0}">
		Echec authentification conseiller<br />
		Attention déjà 
		<c:out value="${sessionScope.attemptsCount}"></c:out>
		tentative(s) de connections<br />
	</c:if>

	<c:choose>
		<c:when test="${sessionScope.attemptsCount > 3}">
			Trop de tentatives, veuillez réessayer plus tard<br />
		</c:when>
		<c:otherwise>
			<form method="post"
				action="GestionConseiller?action=interfaceConseiller">
				<fieldset id="section-1">
					<legend>Authentification Conseiller</legend>
					<label for="id">saisir votre identifiant :</label> <input
						type="text" name="id" id="id" /><br /> <label for="pwd">saisir
						votre mot de passe :</label> <input type="password" name="pwd" id="pwd" /><br />
				</fieldset>
				<input type="submit" name="validauthenticate" value="valider" /><br />
			</form>
		</c:otherwise>
	</c:choose>
	<a href="index.html">retour accueil</a>

</body>
</html>