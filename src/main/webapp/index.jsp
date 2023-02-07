<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
	<div class=”wrapper”>
	 <div class=”page-header-image” style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/67.jpg'); background-position: 50%; background-size: cover; min-height: 100vh; max-height: 999px; overflow: hidden; width: 100%;
		z-index: 1;">
		<div class="vh-100 d-flex justify-content-center align-items-center">
		    <div class="col-md-4 p-5 shadow-sm border rounded-3" style="background-color:white">
		        <h2 class="text-center mb-4 text-primary">Identification</h2>
		        <form action="ConnexionController" method="post">
		            <div class="mb-3">
		                <label for="exampleInputEmail1" class="form-label"></label>
		                <input name="email" type="email" class="form-control border border-primary" id="exampleInputEmail1" placeholder ="Adresse e-mail" aria-describedby="emailHelp" required>
		            </div>
		            <div class="mb-3">
		                <label for="exampleInputPassword1" class="form-label"></label>
		                <input name="mdp" type="password" class="form-control border border-primary" placeholder="Mot de passe" id="exampleInputPassword1" required>
		            </div>
		            <div class="d-grid">
		                <button class="btn btn-primary" type="submit">Se connecter</button>
		            </div>
		            <%
							String msg_erreur = (String)request.getAttribute("msg_erreur");
	                		if (msg_erreur != null ) {
						%>
		            <div class="form-text"><%= msg_erreur %></div>
		            
           		<% 			} %>
		        </form>
		        </div>
		    </div>
		</div>
	</div>
</body>
</html>