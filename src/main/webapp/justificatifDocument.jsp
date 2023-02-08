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
<title>justificatifAbscence</title>
</head>
<body>
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
        	out.println("<td>" + labs.getDtdebut() +"</td>");
        	out.println("<td>" + labs.getDtfin() +"</td>");
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