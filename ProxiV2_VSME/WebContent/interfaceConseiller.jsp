<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<title>Interface Conseiller</title>
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

		<h2>Interface Conseiller</h2>
		
		<c:out value="${sessionScope.login}"></c:out>
		<br /> <a href="GestionConseiller?action=Deconnection">Déconnection</a>
		<hr />
		<c:out value="${requestScope.resultatvalidation}"></c:out>
	</div>
	</section>
	<section class="row">

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<form method="post"
			action="GestionConseiller?action=interfaceConseiller">
			<input type="submit" name="ajouterclient" value="ajouter un client" /><br /><br />

			<table class="table table-bordered table-striped table-condensed">
				<tr>
					<th>&nbsp;</th>
					<th>id</th>
					<th>type de Client</th>
					<th>nom</th>
					<th>prenom</th>
					<th>email</th>

				</tr>
				<c:forEach var="cli" items="${listeclients}">
					<tr>
						<td><input type="radio" name="idclientform" id="idclientform"
							value="<c:out value="${cli.idClient}"></</c:out>" /></td>
						<td><c:out value="${cli.idClient}"></</c:out></td>
						<td><c:out value="${cli.typeClient}"></</c:out></td>
						<td><c:out value="${cli.nom}"></</c:out></td>
						<td><c:out value="${cli.prenom}"></</c:out></td>
						<td><c:out value="${cli.email}"></</c:out></td>

					</tr>
				</c:forEach>
			</table>
			<table>
				<tr>
					<td>non fonctionnel<br/><input type="submit" name="modifierclient"
						value="modifier un client" /></td>
					<td>&nbsp;<br/><input type="submit" name="voircomptesclient"
						value="voir comptes client" /></td>
					<td>non fonctionnel<br/><input type="submit" name="effectuervirementphase1"
						value="effectuer virement" /></td>
				</tr>
			</table>

		</form>
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