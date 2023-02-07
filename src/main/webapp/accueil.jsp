<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<h1>Accueil</h1>
	<% Utilisateur u = (Utilisateur)session.getAttribute("utilisateur"); %>
	<%= u.getMail() %>
	<a href="CtrlListeAppel">ici</a>
</body>
</html>