<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" >
<title>Voir abscence Etudiant</title>
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
<%Utilisateur u = (Utilisateur)session.getAttribute("utilisateur"); %>
<% out.println("<h1 class=\"display-1\">" +  u.getNom() + " " + u.getPrenom()  + "</h1>");%>
<div class="dropdown">
  <label for="mouth-select">Mois :</label>
  
  <select name="mois" id="mois">
    <option value="">Selectionner un mois ...</option>
    <option value="01">Janvier</option>
    <option value="02">Fevrier</option>
    <option value="03">Mars</option>
    <option value="04">Avril</option>
    <option value="05">Mai</option>
    <option value="06">Juin</option>
    <option value="07">Juillet</option>
    <option value="08">Aout</option>
    <option value="09">Septembre</option>
    <option value="10">Octobre</option>
    <option value="11">Novembre</option>
    <option value="12">Decembre</option>
</select>
<h3 class="display-1"> Liste abscence : </h3>
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
        </tr>
      </thead>
      
      <tbody>
      
      </tbody>
    </table>
</div>


</div>
<script type="text/JavaScript" src="js/fctxml.js"></script>
</body>
</html>