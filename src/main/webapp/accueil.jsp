<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur"%>
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
<div class="container" style="">
<div class=”wrapper”>
	 
	<% out.println("<h1>  Bienvenue "+u.getPrenom()+"</h1>");%>
	out.println(u);%>
	
	<% 
		String html ="<ul class=\'list-group'>"+
		"<li class='list-group-item'><a href='JustificatifController'>Déposer un justificatif</a></li>"+
		"<li class='list-group-item'><a href='ValidationController'>Valider un justificatif</a> </li>"+
						"</ul>";	
	 %>
	

	<% out.println(html); %>
	<div class="msg_erreur">${msg_erreur}</div>
</div>
	</div>
	</div>
</body>
</html>