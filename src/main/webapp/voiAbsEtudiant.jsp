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
    <option value="">Janvier</option>
    <option value="">Fevrier</option>
    <option value="">Mars</option>
    <option value="">Avril</option>
    <option value="">Mai</option>
    <option value="">Juin</option>
    <option value="">Juillet</option>
    <option value="">Aout</option>
    <option value="">Septembre</option>
    <option value="">Octobre</option>
    <option value="">Novembre</option>
    <option value="">Decembre</option>
</select>
</div>


</div>
</body>
</html>