<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<title>Virement compte à compte</title>
</head>
<body>
	<section class="row">

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1>ProxiBanqueV2</h1>
		<hr />
	</div>
	</section>
	<section class="row">

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
<h2>Virement compte à compte</h2>

		<table class="table table-bordered table-striped table-condensed">
			<tr>
				<th>&nbsp;</th>
				<th>numéro de compte</th>
				<th>type de Compte</th>
				<th>date d'ouverture</th>
				<th>solde</th>

			</tr>
			<c:forEach var="compte" items="${listecomptesclient}">
				<tr>
					<td><input type="radio" name="idclientform" id="idclientform"
						value="<c:out value="${compte.idCompte}"></</c:out>" /></td>
					<td><c:out value="${compte.numeroCompte}"></</c:out></td>
					<td><c:out value="${compte.typeCompte}"></</c:out></td>
					<td><c:out value="${compte.dateOuverture}"></</c:out></td>
					<td><c:out value="${compte.solde}"></</c:out></td>

				</tr>
			</c:forEach>
		</table>
	</div>
	</section>
	<section class="row">

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<br /> <a href="GestionConseiller?action=interfaceConseiller">interface
			Conseiller</a> <br /> <a href="index.html">retour accueil</a>
	</div>
	</section>
</body>
</html>