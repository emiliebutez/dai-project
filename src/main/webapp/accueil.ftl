<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Accueil</title>
	<script>document.getElementsByTagName("html")[0].className += " js";</script>
  <link rel="stylesheet" href="assets/css/style.css">
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
          <a class="nav-link active text-white" aria-current="page" href="accueil">Accueil</a>
        </li>
        <#if Session.utilisateur.statut == "Etudiant">
	        <li class="nav-item">
	          <a class="nav-link text-white" href='JustificatifController'>Déposer un justificatif</a>
	        </li>
			<li class="nav-item">
          		<a class="nav-link text-white" href="CtrlProfil?type_action=profil">Profil</a>
			</li>
        </#if>
      </ul>
      <form action="Deconnexion" method="get">
		            <div>
		             
		                <button class="btn btn-danger" type="submit">Déconnexion</button>
		            </div>
	</form>
    </div>
  </div>
</nav>

<input type="date" name="week" id="week" onChange="selectNewDate(event)">

<div class="cd-schedule cd-schedule--loading margin-top-lg margin-bottom-lg js-cd-schedule">
    <div class="cd-schedule__timeline">
      <ul>
        <li><span>08:00</span></li>
        <li><span>08:30</span></li>
        <li><span>09:00</span></li>
        <li><span>09:30</span></li>
        <li><span>10:00</span></li>
        <li><span>10:30</span></li>
        <li><span>11:00</span></li>
        <li><span>11:30</span></li>
        <li><span>12:00</span></li>
        <li><span>12:30</span></li>
        <li><span>13:00</span></li>
        <li><span>13:30</span></li>
        <li><span>14:00</span></li>
        <li><span>14:30</span></li>
        <li><span>15:00</span></li>
        <li><span>15:30</span></li>
        <li><span>16:00</span></li>
        <li><span>16:30</span></li>
        <li><span>17:00</span></li>
      </ul>
    </div> <!-- .cd-schedule__timeline -->
   
  
    <div class="cd-schedule__events">
	<#assign seq = ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche']>
	<#assign index = 0 >
	<ul>
		<#list 0..4 as i>
				<li class="cd-schedule__group">
          		<div class="cd-schedule__top-info"><span>${seq[index]}</span></div>
				<#assign index += 1 >
				<ul class="coursJour coursJourColumn">
				<#list sessionsCours.getJournee(i) as rendu>
				
					<li class="cours coursRadius" style="height: ${rendu.h}px; top: ${rendu.y}px; width: ${rendu.w}%; left: ${rendu.x}%">
					<a href="${(Session.utilisateur.statut == "Enseignant") ? then('CtrlListeAppel?idSession=' + rendu.sessionCours.id, '')}">
						<em>${rendu.sessionCours.cours.nom}</em>
						<p>
							<span>${rendu.sessionCours.debut.hour?string["00"]}:${rendu.sessionCours.debut.minute?string["00"]}</span>
							➔
							<span>${rendu.sessionCours.fin.hour?string["00"]}:${rendu.sessionCours.fin.minute?string["00"]}</span>
						</p>
						
							<p class="margin10">
							<span>Enseignant: ${rendu.sessionCours.enseignant.nom} ${rendu.sessionCours.enseignant.prenom}</span>
							<br>
							<span class="margin">Groupe: ${rendu.sessionCours.groupe.nom} - ${rendu.sessionCours.groupe.promo.nom}</span>
							</p>
					</a>
					</li>
					
					
				</#list>
				</ul>
				</li>
			
		</#list>
		</ul>
	</div>
	</div>
	</div>
	
	<script>
	setupInputDate();
	
	function selectNewDate(event) {
	    const date = !!event ? new Date(event.target.value).toISOString() : new Date().toISOString();
	    const href = new URL(window.location.href);
	    href.searchParams.set('date', date);
		window.location.href = href;
	}
	
	function setupInputDate(event) {
	    const href = new URL(window.location.href);
	    const dateParam = href.searchParams.get('date');
	    if (!dateParam) {
	        selectNewDate(null)
	    }
	    
	    const date = new Date(dateParam);
	    const dateSelector = document.getElementById('week');
	    dateSelector.valueAsDate  = date;
	}
	</script>
</body>

<style>
* {
  margin: 0;
  padding: 0;
}

.semaine {
  display: flex;
  gap: 1em;
}

ul {
  list-style-type: none;
}

.cours {
 overflow: hidden;
 text-overflow: ellipsis;
position: absolute;
  background: #B6D8F2;
  z-index: 3;
 left: -1px;
 margin-right: 0;
 border: solid;
border-width: thin;
}

@media (max-width: 64rem) {
	.coursRadius {
	  border-radius: 16px;
	}
}

@media (max-width: 64rem) {
.coursJour {
	display: flex !important;
	flex-direction: row !important;
	gap : 1em;
}

.cours {
    position: relative;
    top: unset !important;
    left: unset !important;
    width: fit-content !important;
    height: fit-content !important;
}
}

.coursJourColumn {
	position: relative;
	display: block;
}

a {
	box-shadow: inset 0 -3px 0 rgba(#000, .2);
    text-decoration: none;
    color : black;
    display: block;
    height: 100%;
    padding: var(--space-sm);
    box-shadow: inset 0 -3px 0 rgba(#000, .2);
}

.margin {
 margin-bottom: 0;
}

.margin10 {
 margin-bottom: 10px;
}

a:link, a:visited,  a:hover, a:active
{
    color: black;
    text-decoration: none;
}


</style>
</html>