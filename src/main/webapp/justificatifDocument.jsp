<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="java.util.List"%>
<%@ page import="model.LigneAbsence"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" >
<title>Justifier absences</title>
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
        <li class="nav-item">
          <a class="nav-link text-white" href='JustificatifController'>Déposer un justificatif</a>
        </li>
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
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

    </form>
    </div>
    </body>
</body>
</html>