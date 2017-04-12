<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Modifier client</title>
</head>
<body>
	<h1>Modifier client</h1>
	<form method="post"
		action="GestionConseiller?action=interfaceConseiller">
		<fieldset id="section-1">
			<legend>Modifier le client n°[<c:out value="${client.idClient}"></c:out>]</legend>
			<c:if test="${!empty requestScope.validerModifierclientdefaut}">
			<c:out value="">le formulaire contient des erreurs, veuillez recommencer la saisie<br /></c:out>
			</c:if>
			<label for="nom">nom :</label><input type="text" name="nom" id="nom" value='<c:out value="${client.nom}"></c:out>'/><br />
			<label for="prenom">prenom :</label><input type="text" name="prenom" id="prenom" value='<c:out value="${client.prenom}"></c:out>' /><br />
			<label for="email">email :</label><input type="text" name="email" id="email" value='<c:out value="${client.email}"></c:out>' /><br />
			<label for="adresse">adresse :</label><input type="text" name="adresse" id="adresse" value='<c:out value="${clientadresse.adresse}"></c:out>' /><br />
			<label for="codepostal">code postal :</label><input type="text" name="codepostal" id="codepostal" value='<c:out value="${clientadresse.codePostale}"></c:out>' /><br />
			<label for="ville">ville :</label><input type="text" name="ville" id="ville" value='<c:out value="${clientadresse.ville}"></c:out>' /><br />

		</fieldset>
		<input type="submit" name="validerModifierclient" value="valider" /><br />
	</form>
</body>
</html>