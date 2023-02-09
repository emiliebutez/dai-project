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
<label for="Promo">Promo</label>
</td>
<td>

<select name="Promo" id="Promo" style="margin-top: 3%; margin-left: 3%">
 <option value="">--veuillez choisir une option--</option>
       <option value="goldfish">Goldfish</option>
</select>
<td>
</tr>
<tr>
<td>
<label for="Groupe">Groupe</label>
</td>
<td>
<select name="Groupe" id="Groupe" style="margin-top: 3%; margin-left: 3%">
    <option value="">--veuillez choisir une option--</option>
    <option value="dog">Dog</option>
    
</select>
</td>
</tr>
<tr>
<td>
<label for="Etudiant">Etudiant</label>
</td>
<td>
<select name="Etudiant" id="Etudiant" style="margin-top: 3%; margin-left: 3%">
    <option value="">--veuillez choisir une option--</option>
    <option value="dog">Dog</option>
    <option value="cat">Cat</option>
    
</select>
</td>
</tr>
<tr>
<td>
<label for="Cours">Cour</label>
</td>
<td>
<select name="Cour" id="Cour" style="margin-top: 3%; margin-left: 3%">
    <option value="">--veuillez choisir une option--</option>
    <option value="dog">Dog</option>
    <option value="cat">Cat</option>
    <option value="hamster">Hamster</option>
    </select>
    <br>
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
</body>
</html>