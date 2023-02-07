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
</head>
<body>

	<h1>Test</h1>
	<%
		Set<Utilisateur> eleves = (Set<Utilisateur>)session.getAttribute("eleves");
		out.write("<ul>");
		for (Utilisateur eleve : eleves) {
			out.write("<li>" + eleve + "</li>");
		}
		out.write("</ul>");
	%>

</body>
</html>