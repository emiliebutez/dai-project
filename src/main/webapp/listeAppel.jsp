<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Absence"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.SessionCours"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste d'appel</title>
<link rel="stylesheet" href="listeAppel.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-dark text-white">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
				aria-controls="navbarTogglerDemo03" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<a class="navbar-brand text-white" href="#">Projet DAI</a>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active text-white"
						aria-current="page" href="accueil">Accueil</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href='JustificatifController'>Déposer un justificatif</a></li>
				</ul>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<form action="EnregistrementAbsenceRetardController" method="GET">
		<table class="table text-center align-middle">
			<tr>
			<% SessionCours sessionCours = (SessionCours)request.getSession().getAttribute("sessionCours"); %>
				<th colspan="3">Fiche d'appel du groupe <%=sessionCours.getGroupe().getNom() %> du cours : <%=sessionCours.getCours().getNom() %> du <%=sessionCours.getDebut().getDayOfMonth() + "/" + sessionCours.getDebut().getMonthValue() + "/" + sessionCours.getDebut().getYear() + " à " + sessionCours.getDebut().getHour() + ":" + sessionCours.getDebut().getMinute()%></th>
			</tr>
			<tr>
				<th rowspan="2">Photo</th>
				<th rowspan="2">Nom</th>
				<th rowspan="2">Prenom</th>
				<th colspan="3"></th>
			</tr>
			<tr>
				<th colspan="3" class="absence">Statut</th>
			</tr>

							<%
				List<Utilisateur> eleves = (List<Utilisateur>)session.getAttribute("eleves");
				List<Absence> absences = (List<Absence>)session.getAttribute("absences");
				int index = 3;
				for (Utilisateur eleve : eleves) {
					
				%>
					<tr> 
							  <td><img src="images?id=<%=eleve.getId()%>"></td>
							  <td><%=eleve.getNom()%></td>
							  <td><%=eleve.getPrenom()%></td>
	  						<% boolean absent = absences.stream().anyMatch(a -> a.getUtilisateur().equals(eleve)); %>
							<% boolean retard = !absent && eleve.getSessionsCours().contains(sessionCours); %>
							<% boolean present = !(absent || retard); %> 
							<% index += 1; %>
							<td> <input class="checkbox" id="<%=index%>" type="checkbox" name="statut" data-name="Absent(e)" value="absent:<%=eleve.getId()%>" <%= (sessionCours.isAppelTermine() ? "disabled" : "") %> <%= (absent ? "checked" : "") %> onclick="selectOnlyThis(this.id, 'premier')"> </td>
							<% index += 1; %>
							<td> <input class="checkbox" id="<%=index%>" type=checkbox name="statut" data-name="Retard" value="retard:<%=eleve.getId()%>" <%= (sessionCours.isAppelTermine() ? "disabled" : "") %> <%= (retard ? "checked" : "") %> onclick="selectOnlyThis(this.id, 'deuxieme')"></td>
						  	<% index += 1; %>
						  	<td><input class="checkbox" id="<%=index%>" type="checkbox" data-name="Présent(e)" <%= (sessionCours.isAppelTermine() ? "disabled" : "") %> <%= (present ? "checked" : "") %> onclick="selectOnlyThis(this.id), 'troisieme'"> </td>

					 </tr>
			  <%
				}
				%>
		</table>
		<% if(!sessionCours.isAppelTermine()) { %>
			<div class="form-check form-switch">
			<input class="form-check-input" name="validation" type="checkbox"
				id="flexSwitchCheckDefault"> 
				<label class="form-check-label" for="flexSwitchCheckDefault">Valider l'appel (Attention cette opération est irréversible vous ne pourrez plus modifier la liste)</label>
			</div>
			<input type="submit" value="Enregistrer" class="btn btn-primary" />
			<% } else { 
			String link = "PdfController?idSession=" + sessionCours.getId();
    	response.sendRedirect(link);



			%>
				<a href="<%=link%>">Télécharger</a>
			<% } %>
	</form>
	
		<script> 
		function selectOnlyThis(id, ordre) {
			let debut = parseInt(id);
			
			if (ordre === "deuxieme") {
				debut = id - 1;
			} else if (id % 3 === 0) {
				debut = id - 2;
			} 
			
			let fin = debut + 3; 
			for (var i = debut ;i < fin; i++) {
		    	if (i != id) {
		    		document.getElementById(i).checked = false;
		    	} 
		    }
		    //document.getElementById(id).checked = true;
   		}
		</script>

</body>
</html>