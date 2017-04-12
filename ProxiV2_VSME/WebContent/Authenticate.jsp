<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<title>Authentification conseiller</title>
</head>
<body>
	<section class="row">

<<<<<<< HEAD
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1>ProxiBanqueV2</h1>
		<hr />
	</div>
	</section>
	<section class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<h2>Authentification conseiller</h2>
		<c:if test="${sessionScope.attemptsCount > 0}">
=======
	<c:if test="${sessionScope.attemptsCount > 0}">
>>>>>>> branch 'master' of https://github.com/serviceshuios/Proxi2-Groupe4.git
		Echec authentification conseiller<br />
		Attention déjà 
		<c:out value="${sessionScope.attemptsCount}"></c:out>
		tentative(s) de connections<br />
<<<<<<< HEAD
		</c:if>
=======
	</c:if>
>>>>>>> branch 'master' of https://github.com/serviceshuios/Proxi2-Groupe4.git

<<<<<<< HEAD
		<c:choose>
			<c:when test="${sessionScope.attemptsCount > 3}">
=======
	<c:choose>
		<c:when test="${sessionScope.attemptsCount > 3}">
>>>>>>> branch 'master' of https://github.com/serviceshuios/Proxi2-Groupe4.git
			Trop de tentatives, veuillez réessayer plus tard<br />
<<<<<<< HEAD
			</c:when>
			<c:otherwise>
				<form method="post"
					action="GestionConseiller?action=interfaceConseiller">
					<fieldset id="section-1">
						<legend>Page de connection</legend>
						<label for="id">saisir votre identifiant :</label> <input
							type="text" name="id" id="id" /><br /> <label for="pwd">saisir
							votre mot de passe :</label> <input type="password" name="pwd" id="pwd" /><br />
					</fieldset>
					<input type="submit" name="validauthenticate" value="valider" /><br />
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	</section>
	<section class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<br />
		<a href="index.html">retour accueil</a>
	</div>
	</section>
=======
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

>>>>>>> branch 'master' of https://github.com/serviceshuios/Proxi2-Groupe4.git
</body>
</html>