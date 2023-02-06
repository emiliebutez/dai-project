<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<h1>Accueil</h1>
	<div class="semaine">
		<#list 0..6 as i>
			<ul class="cours">
				<#list sessionsCours[i] as sessionCours>
					<li>
						<h2>${sessionCours.cours.nom}</h2>
						<p>
							<span>${sessionCours.debut.hour?string["00"]}:${sessionCours.debut.minute?string["00"]}</span>
							➔
							<span>${sessionCours.fin.hour?string["00"]}:${sessionCours.fin.minute?string["00"]}</span>
						</p>
						
						<p class="corps-cours">
							<p>Enseignant: ${sessionCours.enseignant.nom} ${sessionCours.enseignant.prenom}</p>
							<p>Groupe: ${sessionCours.groupe.nom}</p>
						</p>
					</li>
				</#list>
			</ul>
		</#list>
	</div>
</body>


<style>
* {
  margin: 0;
  padding: 0;
}

.semaine {
  display: flex;
  gap: 1em;
  background: yellow;
}

ul {
  list-style-type: none;
}
.cours {
  display: flex;
  flex-direction: column;
  gap: 1em;
}

.cours li  {
  background: grey;
  border-radius: 16px;
  padding: 8px;
}
</style>
</html>