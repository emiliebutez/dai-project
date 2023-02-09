<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Utilisateur"%>
<%@ page import="java.util.List"%>
<%@ page import="model.LigneAbsence"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" >
<title>Validation des justificatifs</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <script type="text/javascript" src="js.js" defer></script>
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
	          <a class="nav-link text-white" href='ValidationController'>Valider un justificatif</a>
	        </li>
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
<form action="Tabtruevalidation"method="get">
<table class="table"id="table1">
  <thead>
  <tr>
    <th scope="col"></th>
      <th scope="col">Absences</th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
  </tr>
  </thead>
  <thead>
    <tr>
     <th scope="col">Prenom</th>
      <th scope="col">Nom</th>
      <th scope="col">Cours</th>
      <th scope="col">Debut</th>
      <th scope="col">Fin</th>
      <th scope="col">Groupe</th>
      <th scope="col">Justificatifs</th>
      <th scope="col"></th>
      
      
    </tr>
  </thead>
  
  <tbody>
   <% 
        for (LigneAbsence labs : (List<LigneAbsence>)request.getAttribute("listeAbs") ) {
        	out.println("<tr><td>"+labs.getPrenom()+"</td>");
        	out.println("<td>"+labs.getNom()+"</td>");
        	out.println("<td>" + labs.getNomCours() +"</td>");
        	out.println("<td>" + labs.getDtdebut() +"</td>");
        	out.println("<td>" + labs.getDtfin() +"</td>");
        	out.println("<td>" + labs.getNomGroupe()+"</td>");
        	out.println("<td><a href = http://"+labs.getJustificatif()+">Voir justificatif</a></td>");
        	out.println("<td><input type=\"checkbox\" value= "+ labs.getAbsid() +" name=\"cb_abs\"></input></td></tr>");
        } 
      %>
  </tbody>
</table>

<button type="submit" class="btn btn-success" name="btn" id="btn" value="OK"Style="margin-left: 100%;margin-top: 40px">Valider</button>
<button type="submit" class="btn btn-danger" name="btn" id="btn" value="KO"Style="margin-left: 100%;margin-top: 40px">Rejeter</button>
</form>
</div>
</body>
</html>