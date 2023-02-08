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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
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
          <a class="nav-link active text-white" aria-current="page" href="#">Accueil</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white">Disabled</a>
        </li>
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
	<form action="EnregistrementAbsenceRetardController" method="GET">
		<table class="table text-center align-middle">   
	    	<tr>
	    		<th colspan="3">Liste des élèves</th> 
	    	</tr>
			<tr>
				<th rowspan="2">Photo</th>
				<th rowspan="2">Nom</th>
				<th rowspan="2">Prenom</th>
				<th colspan="3"></th>
			</tr>
			<tr>
				<th class="absence">Absent</th>
				<th class="absence">Retard</th>
				<th class="absence">Présent</th>
			</tr>
				
				<%
				Set<Utilisateur> eleves = (Set<Utilisateur>)session.getAttribute("eleves");
				for (Utilisateur eleve : eleves) {
				%>
					<tr> 
							  <td><img src="images?id=<%=eleve.getId()%>"></td>
							  <td><%=eleve.getNom()%></td>
							  <td><%=eleve.getPrenom()%></td>
							  <td> <input type="checkbox" name="absence" value="<%=eleve.getId()%>" > </td>
							  <td> <input type=checkbox name="retard"value="<%=eleve.getId()%>"></td>
							  <td> <input type="checkbox" name="présent"> </td>
							 </tr>
			  <%
				}
				%>
		</table>
		<input type="submit" value="Enregistrer" class="btn btn-primary"/>
		<a class="btn">Valider</a>
	</form>	
	

</body>
</html>