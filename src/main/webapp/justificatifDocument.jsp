<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="java.util.List"%>
<%@ page import="model.LigneAbsence"%>
<%@ page import="model.Statut"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<title>Justifier absences</title>
</head>
<body>
<form action="Deconnexion" method="get">
		            <div style= "position: absolute; top: 10px; right: 10px;" >
		             
		                <button class="btn btn-danger" type="submit">Déconnexion</button>
		            </div>
</form>
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
                <a class="nav-link text-white" href='voiAbsEtudiant.jsp'>Voir mes absences</a>
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
<div class="container">
    <% Utilisateur u = (Utilisateur)session.getAttribute("utilisateur"); %>
    <% out.print("<p>" + u.getNom() + " " + u.getPrenom() + "</p>");%>
    <form method="post" enctype="multipart/form-data" action="DepotJustificatifController">
    <table class="table">
      <thead>
      <tr>
        <th scope="col"></th>
          <th scope="col">Absences</th>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col"></th>
          <th scope="col"></th>
      </tr>
      </thead>
      <thead>
        <tr>
          <th scope="col">Cours</th>
          <th scope="col">Debut</th>
          <th scope="col">Fin</th>
          <th scope="col">Groupe</th>
          <th scope="col">Justificatifs</th>
        </tr>
      </thead>
      
      <tbody>
      <% 
        for (LigneAbsence labs : (List<LigneAbsence>)request.getAttribute("listeAbs") ) {
        	
        	out.println("<tr><td>" + labs.getNomCours() +"</td>");
        	out.println("<td>" + labs.getDtdebut().getDayOfMonth() + "/" + labs.getDtdebut().getMonthValue() + "/" + labs.getDtdebut().getYear() + " " + labs.getDtdebut().getHour() + ":" + labs.getDtdebut().getMinute() +"</td>");
        	out.println("<td>" + labs.getDtfin().getDayOfMonth() + "/" + labs.getDtfin().getMonthValue() + "/" + labs.getDtfin().getYear() + " " + labs.getDtfin().getHour() + ":" + labs.getDtdebut().getMinute() +"</td>");
        	out.println("<td>" + labs.getNomGroupe()+"</td>");

        	if (labs.getJustificatif() != null) {
        	out.println("<td><a href = http://"+labs.getJustificatif()+">Voir justificatif</a></td>");
        	} else {
        		out.println("<td> Aucun justificatif déposé </td>");
        	}

        	out.println("<td><input type=\"checkbox\" value= "+ labs.getAbsid() +" name=\"cb_abs\"></input></td></tr>");

        } 
      
      %>
      
      </tbody>
    </table>
    
    <input type="File" id="justificatif" name="justificatif"/>

    <button type="submit" value="upload" class="btn btn-success">Valider</button>
    <% 
    	String messageInfo = (String)request.getAttribute("msg_info"); 
    	String messageErreur = (String)request.getAttribute("msg_erreur"); 
    %>
    
    <p><%= messageInfo != null ? messageInfo : ""  %></p>
    <p><%=messageErreur != null ? messageErreur : ""  %></p>

    </form>
    </div>
    </body>
</body>
</html>