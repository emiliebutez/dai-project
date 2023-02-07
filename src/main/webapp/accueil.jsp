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
<div id="content">
	<h1>Accueil</h1>
	<% Utilisateur u = (Utilisateur)session.getAttribute("utilisateur"); %>
	<%= u.getMail() %>
	<form>
	    <input type=button name=print value="Print" id="btn-one"">
	</form>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>

		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"
			integrity="sha512-BNaRQnYJYiPSqHHDb58B0yaPfCu+Wgds8Gp/gU33kqBtgNS4tSPHuGibyoeqMV/TJlSKda6FXzoEyYGjTe+vXA=="
			crossorigin="anonymous"
			referrerpolicy="no-referrer"
		></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>

		
	<a href="CtrlListeAppel">ici</a>
</body>
</html>
<script src="pdf.js"></script>