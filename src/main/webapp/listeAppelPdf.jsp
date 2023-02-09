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
	
	<script>
	document.addEventListener('DOMContentLoaded', function () {

	    const specialElementHandlers = {
	        '#editor': function (element, renderer) {
	            return true;
	        }
	    };
		
		document.querySelector('#content').addEventListener('click', event => {
			const pdf = new jsPDF('p', 'pt', 'a4');
			pdf.addHTML(document.getElementById("pdf"), function() {
				pdf.setFont("helvetica");
				pdf.setFontType("bold");
				pdf.setFontSize(900);
			  	pdf.save('web.pdf');
			});
		});
	});
	</script>
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
	<div id="pdf" style="background: white;">
	<form action="EnregistrementAbsenceRetardController" method="GET">
		<table class="table text-center align-middle">
		<% SessionCours sessionCours = (SessionCours)request.getSession().getAttribute("sessionCours"); %>
			<tr>
				<th colspan="3">Fiche d'appel du groupe <%=sessionCours.getGroupe().getNom() %>  du cours : <%=sessionCours.getCours().getNom() %> du <%=sessionCours.getDebut().getDayOfMonth() + "/" + sessionCours.getDebut().getMonthValue() + "/" + sessionCours.getDebut().getYear() + " à " + sessionCours.getDebut().getHour() + ":" + sessionCours.getDebut().getMinute()%></th>
			</tr>
			<tr>
				<th rowspan="2">Photo</th>
				<th rowspan="2">Nom</th>
				<th rowspan="2">Prenom</th>
				<th colspan="3"></th>
			</tr>
			<tr>
				<th class="absence">Statut</th>
			</tr>

							<%
				List<Utilisateur> eleves = (List<Utilisateur>)session.getAttribute("eleves");
				List<Absence> absences = (List<Absence>)session.getAttribute("absences");
				
				for (Utilisateur eleve : eleves) {
					
				%>
					<tr> 
							  <td><img src="images?id=<%=eleve.getId()%>"></td>
							  <td><%=eleve.getNom()%></td>
							  <td><%=eleve.getPrenom()%></td>
	  						<% boolean absent = absences.stream().anyMatch(a -> a.getUtilisateur().equals(eleve)); %>
							<td><p><%= (absent ? "Absent(e)" : "Présent(e)") %></p></td>
					 </tr>
			  <%
				}
				%>
		</table>
	</form>
	</div>
	<button id="content" class="btn btn-primary" >telecharger</button>
	
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>

		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"
			integrity="sha512-BNaRQnYJYiPSqHHDb58B0yaPfCu+Wgds8Gp/gU33kqBtgNS4tSPHuGibyoeqMV/TJlSKda6FXzoEyYGjTe+vXA=="
			crossorigin="anonymous"
			referrerpolicy="no-referrer"
		></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>

</body>
</html>

