<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
   	pageEncoding="UTF-8"%>
<%@ page import="java.util.Set"%>
<%@ page import="model.Utilisateur"%>
   	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste d'appel</title>
<link rel="stylesheet" href="listeAppel.css">
</head>
<body>

	<h1>Liste d'appel</h1>
	<form action="" method="GET">
		<table>   
	    	<tr>
	    		<th colspan="3">Liste des élèves</th> 
	    	</tr>
			<tr>
				<th rowspan="2">Photo</th>
				<th rowspan="2">Nom</th>
				<th rowspan="2">Prenom</th>
				<th colspan="2">Absence</th>
			</tr>
			<tr>
				<th class="absence">Absent</th>
				<th class="absence">Retard</th>
			</tr>
	            
			<%
				Set<Utilisateur> eleves = (Set<Utilisateur>)session.getAttribute("eleves");
				for (Utilisateur eleve : eleves) {
					out.write("<tr>" + 
							  "<td>" + "<img src=\"images?id=" + eleve.getId() + "\">" + "</td>" +
							  "<td>" + eleve.getNom() + "</td>" +
							  "<td>" + eleve.getPrenom() + "</td>" +
							  "<td> <input type=\"checkbox\" name=\"absence\" value=\""+ eleve.getId() + "\"> </td>" +
							  "<td> <input type=\"checkbox\" name=\"retard\" value=\""+ eleve.getId() + "\"> </td>" +
							  "</tr>");
				}
				%>
		</table>
		<input type="button" value="Enregistrer"/>
		<input type="submit" value="Valider"/>
	</form>	
	

</body>
</html>