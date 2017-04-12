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
	<form method="post" action="GestionConseiller?action=InterfaceConseiller">
		<input type="submit" name="ajouterclient" value="ajouter un client" /><br />
</form>
		<table>
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
					<td><input
				type="radio" name="typeclient" id="entreprise"
				value="<c:out value="${cli.idClient}"></</c:out>" /></td>
					<td><c:out value="${cli.idClient}"></</c:out></td>
					<td><c:out value="${cli.typeClient}"></</c:out></td>
					<td><c:out value="${cli.nom}"></</c:out></td>
					<td><c:out value="${cli.prenom}"></</c:out></td>
					<td><c:out value="${cli.email}"></</c:out></td>


				</tr>
			</c:forEach>
		</table>
		<input type="submit" name="modifierclient" value="modifier un client" /><br />
		<input type="submit" name="voircomptesclient" value="voir comptes client" /><br />
		<input type="submit" name="effectuervirement" value="effectuer virement" /><br />
	</form>



</body>
</html>