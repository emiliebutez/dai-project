<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.Statut"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
  	<link rel="stylesheet" href="assets/css/listeAppel.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-dark text-white">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand text-white" href="#">Projet DAI</a>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active text-white" aria-current="page" href="accueil">Accueil</a>
        </li>
        <%
				Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
				if (utilisateur.getStatut() == Statut.Etudiant) {
					
				%>
	        <li class="nav-item">
	          <a class="nav-link text-white" href='JustificatifController'>Déposer un justificatif</a>
	        </li>
			<li class="nav-item">
          		<a class="nav-link text-white" href="CtrlProfil?type_action=profil">Profil</a>
			</li>
        <% } %>
      </ul>
      <form action="Deconnexion" method="get">
		            <div>
		             
		                <button class="btn btn-danger" type="submit">Déconnexion</button>
		            </div>
	</form>
    </div>
  </div>
</nav>

	<h1>Consulter votre profil :</h1>
	<br>

	<table>
		<tr>
			<th>Photo</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Adresse mail</th>
			<th>Alternant</th>
			<th>Numéro Etudiant</th>
		</tr>
		<tr>
			<%
			Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");

			String txt = "";

			if (user.getEstAlternant() == true)
				txt = "Oui";
			else {
				txt = "Non";
			}

			out.print("<td><img src=\"images?id=" + user.getId() + "\"></td>" + "<td>" + user.getNom()  +"</td>" + "<td>"
					+ user.getPrenom() + "</td>" + "<td>" + user.getMail() + "</td>" + "<td>" + txt + "</td>" + "<td>"
					+ user.getNumEtudiant() + "</td>");
			%>

		</tr>
	</table>


	<form action="CtrlProfil" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="userID" value="<%= user.getId() %>" />
		<input type="file" name="file" /> 
		<input type="submit" value="Changez votre photo de profil" />
	</form>

</body>
</html>