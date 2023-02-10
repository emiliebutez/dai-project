<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.SessionCours"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Statut"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" >
<title>Accueil</title>
</head>
<body>

<% Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
Statut s1 =Statut.Etudiant;//**if(u.getStatut() == s1){*/%>
<div class=”page-header-image” style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/67.jpg'); background-position: 50%; background-size: cover; min-height: 100vh; max-height: 999px; overflow: hidden; width: 100%;
		z-index: 1;">
	 
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
	<%
	String msg_erreur = (String)request.getAttribute("msg_erreur");
	String msg_info = (String)request.getAttribute("msg_info");
	if (msg_erreur != null ) { 
	%>
		<div class="form-text"><%= msg_erreur %></div>
	<% } %>
	<% if (msg_info != null ) { 
	%>
		<div class="form-text"><%= msg_info %></div>
	<% } %>
</div>
</body>
</html>