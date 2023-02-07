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

<body>

<div class="container">
<form action="">
<table class="table">
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
        	out.println("<td></td>");
        	out.println("<td><input type=\"checkbox\" value= "+ labs.getAbsid() +" name=\"cb\"></input></td></tr>");
        } 
      %>
  </tbody>
</table>
<button type="submit" class="btn btn-success"Style="margin-left: 100%;margin-top: 40px">Valider</button>
</form>
</div>
</body>
</html>