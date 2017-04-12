<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ajouter client</title>
</head>
<body>
	<h1>Ajouter client</h1>
	<form method="post" action="GestionConseiller?action=interfaceConseiller">
		<fieldset id="section-1">
			<legend>Ajouter un client</legend>
			<c:if test="${!empty requestScope.validerajouterclientdefaut}">
				<c:out
					value="${requestScope.validerajouterclientdefaut} le formulaire contient des erreurs, veuillez recommencer la saisie">
				</c:out>
				<br />
			</c:if>
			<label for="nom">nom :</label><input type="text" name="nom" id="nom" /><br />
			<label for="prenom">prenom :</label><input type="text" name="prenom"
				id="prenom" /><br /> <label for="email">email :</label><input
				type="text" name="email" id="email" /><br /> <label
				for="telephone">téléphone :</label><input type="text"
				name="telephone" id="telephone" /><br /> <label for="adresse">adresse
				:</label><input type="text" name="adresse" id="adresse" /><br /> <label
				for="codepostal">code postal :</label><input type="text"
				name="codepostal" id="codepostal" /><br /> <label for="ville">ville
				:</label><input type="text" name="ville" id="ville" /><br /> <input
				type="radio" name="typeclient" id="particulier"
				value="particulier" checked="checked" />client particulier<br /> <input
				type="radio" name="typeclient" id="entreprise"
				value="entreprise" />client entreprise<br />

		</fieldset>
		<input type="submit" name="validerajouterclient" value="valider" /><br />
	</form>
</body>
</html>