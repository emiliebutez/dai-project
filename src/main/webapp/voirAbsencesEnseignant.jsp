<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulter Absences</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
<div class="container">
<table>
<tr>
<td>
<label for="promo">Promo</label>
</td>
<td>

<select name="promo" id="promo" style="margin-top: 3%; margin-left: 3%" onclick="getgroupe()">
 <option value="">--veuillez choisir une option--</option>
       <option value="1">Miage 2023</option>
       <option value="2">Miage 2022</option>
</select>
<td>
</tr>
<tr>
<td>
<label for="groupe">Groupe</label>
</td>
<td>
<select name="groupe" id="groupe" style="margin-top: 3%; margin-left: 3%"onclick="getcour()">
    <option value="">--veuillez choisir une option--</option>
    
    
</select>
</td>
</tr>

<tr>
<td>
<label for="cour">Cour</label>
</td>
<td>
<select name="cour" id="cour" style="margin-top: 3%; margin-left: 3%"onclick="getetudiant()">
    <option value="">--veuillez choisir une option--</option>
    
       </select>
    <br>
    </td>
</tr>
<tr>
<td>
<label for="etudiant">Etudiant</label>
</td>
<td>
<select name="etudiant" id="etudiant" style="margin-top: 3%; margin-left: 3%">
    <option value="">--veuillez choisir une option--</option>
   
    
</select>
</td>
</tr>
</table>
<br>
    
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
   
  </tbody>
</table>













</div>
<script type="text/JavaScript" src="fctxml2.js"></script>
</body>
</html>