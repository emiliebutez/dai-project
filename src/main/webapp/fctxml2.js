/**
 * 
 */
/**
 * 
 */

 /**
  * Cette fonction Affiche les différents tableaux en fonction du mois choisi
  */
  function afficherTableau ()
  {
	  console.log("[Value] : " + document.getElementById("mois").value);
	  if (document.getElementById("mois").value !== "")
	  {
		  // Objet XMLHttpRequest.
		var xhr = new XMLHttpRequest();
		var param = "mois=" + encodeURIComponent(document.getElementById("mois").value);
		console.log(document.getElementById("mois").value);
		
		xhr.open("POST","AbsenceEtuController?" + param);
		// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
		xhr.onload = function()
		{
			// Si la requête http s'est bien passée.
			if (xhr.status === 200)
				{
				// Réponse du serveur.
				var l_abs = xhr.responseXML.getElementsByTagName("Absence");
				var cours = xhr.responseXML.getElementsByTagName("cours");
				var debut = xhr.responseXML.getElementsByTagName("debut");
				var fin = xhr.responseXML.getElementsByTagName("fin");
				var groupe = xhr.responseXML.getElementsByTagName("groupe");
				
				// On construit les 'options' de notre liste déroulante.
				var texte = "<thead>"+
      							"<tr><th scope='col'></th>"+
        							"<th scope='col'>Absences</th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
      							"</tr></thead><thead><tr>"+
          							"<th scope='col'>Cours</th>"+
          							"<th scope='col'>Debut</th>"+
          							"<th scope='col'>Fin</th>"+
          							"<th scope='col'>Groupe</th>"+
          							"<th scope='col'>Absence justifié </th>"+
        						"</tr></thead><tbody>";
        		for (var i=0; i<l_abs.length; i++){
					texte += "<td>" + l_abs[i].getElementsByTagName("cours")[0].childNodes[0].nodeValue + "</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("debut")[0].childNodes[0].nodeValue + "</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("fin")[0].childNodes[0].nodeValue + "</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("groupe")[0].childNodes[0].nodeValue + "</td>" +
							  "<td>" + (l_abs[i].getElementsByTagName("validation")[0].childNodes[0].nodeValue ? "Non" : "Oui") + "</td>" +
							 "</tr>"
					 "</tr>"
				}
				texte+="</tbody>"
				// Elément html que l'on va mettre à jour.
				var elt = document.getElementById("table1");
				elt.innerHTML = texte;
				}
		};
		//console.log(xhr.responseXML);
	// Envoi de la requête.
		xhr.send(param);
	}
	else
		{
		// Elément html que l'on va mettre à jour.
		var elt = document.getElementById("table1");
		elt.innerHTML = "";
		}
	}
	function getgroupe()
	{
		// Récupération de la valeur de la recherche saisie par l'utilisateur
    var recherche = document.getElementById("promo").value;
;
    // Création d'un objet XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Préparation de la requête
    xhr.open("GET", "Dropgroupcontroller?promo=" + recherche);

    // Gestion de la réponse
    xhr.onload = function() {
        if (xhr.status == 200) {
            // Récupération de la réponse sous forme de document XML
            var xml = xhr.responseXML;

            // Récupération de la liste des mots correspondants
            var mots = xml.getElementsByTagName("grp");
            
            // Affichage des mots dans la div "zoneaff"
            var html = "<option value=''>--veuillez choisir une option--</option> " ;
            for (var i = 0; i < mots.length; i++) {
                html += "<option value='"+mots[i].textContent+"'>" + mots[i].textContent + "</option>";
            }
            
            document.getElementById("groupe").innerHTML = html;
        }}
        xhr.send();
        afficherTableaubis ();
        
        };
        function getcour()
	{
		// Récupération de la valeur de la recherche saisie par l'utilisateur
    var recherche = document.getElementById("groupe").value;
;
    // Création d'un objet XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Préparation de la requête
    xhr.open("GET", "Dropcourcontroller?groupe=" + recherche);

    // Gestion de la réponse
    xhr.onload = function() {
        if (xhr.status == 200) {
            // Récupération de la réponse sous forme de document XML
            var xml = xhr.responseXML;

            // Récupération de la liste des mots correspondants
            var mots = xml.getElementsByTagName("crs");
            
            // Affichage des mots dans la div "zoneaff"
            var html = "<option value=''>--veuillez choisir une option--</option> " ;
            for (var i = 0; i < mots.length; i++) {
                html += "<option value='"+mots[i].textContent+"'>" + mots[i].textContent + "</option>";
            }
            
            document.getElementById("cour").innerHTML = html;
        }}
        xhr.send();
        afficherTableaubis ();
        
        };
function getetudiant()
	{
		// Récupération de la valeur de la recherche saisie par l'utilisateur
    var recherche = document.getElementById("cour").value;
;
    // Création d'un objet XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Préparation de la requête
    xhr.open("GET", "Dropetudiantcontroller?groupe=" + recherche);

    // Gestion de la réponse
    xhr.onload = function() {
        if (xhr.status == 200) {
            // Récupération de la réponse sous forme de document XML
            var xml = xhr.responseXML;

            // Récupération de la liste des mots correspondants
            var mots = xml.getElementsByTagName("etd");
            
            // Affichage des mots dans la div "zoneaff"
            var html = "<option value=''>--veuillez choisir une option--</option> " ;
            for (var i = 0; i < mots.length; i++) {
                html += "<option value\""+mots[i].textContent+"\">" + mots[i].textContent + "</option>";
            }
            
            document.getElementById("etudiant").innerHTML = html;
        }}
        xhr.send();
        afficherTableaubis ();
        
        };
 
	function afficherTableaubis ()
  {
	 // console.log("[Value] : " + document.getElementById("mois").value);
	  
		  // Objet XMLHttpRequest.
		var xhr = new XMLHttpRequest();
		//var param = "mois=" + encodeURIComponent(document.getElementById("mois").value);
		var promo = document.getElementById("promo").value;
		var groupe = document.getElementById("groupe").value;
		var etudiant = document.getElementById("etudiant").value;
		var cours = document.getElementById("cour").value;
		console.log(document.getElementById("promo").value);
		
		xhr.open("POST","EnseignantconsultationControleur?" + promo, groupe, etudiant, cours);
		// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
		xhr.onload = function()
		{
			// Si la requête http s'est bien passée.
			if (xhr.status === 200)
				{
				// Réponse du serveur.
				var l_abs = xhr.responseXML.getElementsByTagName("Absence");
				var cours = xhr.responseXML.getElementsByTagName("cour");
				var debut = xhr.responseXML.getElementsByTagName("debut");
				var fin = xhr.responseXML.getElementsByTagName("fin");
				var groupe = xhr.responseXML.getElementsByTagName("groupe");
				
				// On construit les 'options' de notre liste déroulante.
				var texte = "<thead>"+
      							"<tr><th scope='col'></th>"+
        							"<th scope='col'>Absences</th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
      							"</tr></thead><thead><tr>"+
          							"<th scope='col'>Cours</th>"+
          							"<th scope='col'>Debut</th>"+
          							"<th scope='col'>Fin</th>"+
          							"<th scope='col'>Groupe</th>"+
          							"<th scope='col'>Absence justifié </th>"+
        						"</tr></thead><tbody>";
        		for (var i=0; i<2; i++){
					texte += "<td>" + l_abs[i].getElementsByTagName("cours")[0].childNodes[0].nodeValue + "blabla</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("debut")[0].childNodes[0].nodeValue + "blabla</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("fin")[0].childNodes[0].nodeValue + "</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("groupe")[0].childNodes[0].nodeValue + "</td>" +
							  "<td>" + (l_abs[i].getElementsByTagName("validation")[0].childNodes[0].nodeValue ? "Non" : "Oui") + "</td>" +
							 "</tr>"
					 "</tr>"
				}
				texte+="</tbody>"
				// Elément html que l'on va mettre à jour.
				var elt = document.getElementById("table1");
				elt.innerHTML = texte;
				
				var texte = "<thead>"+
      							"<tr><th scope='col'></th>"+
        							"<th scope='col'>Absences</th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
          							"<th scope='col'></th>"+
      							"</tr></thead><thead><tr>"+
          							"<th scope='col'>Cours</th>"+
          							"<th scope='col'>Debut</th>"+
          							"<th scope='col'>Fin</th>"+
          							"<th scope='col'>Groupe</th>"+
          							"<th scope='col'>Absence justifié </th>"+
        						"</tr></thead><tbody>";
        		for (var i=0; i<2; i++){
					texte += "<td>" + l_abs[i].getElementsByTagName("cours")[0].childNodes[0].nodeValue + "</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("debut")[0].childNodes[0].nodeValue + "</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("fin")[0].childNodes[0].nodeValue + "</td>"+
							 "<td>" + l_abs[i].getElementsByTagName("groupe")[0].childNodes[0].nodeValue + "</td>" +
							  "<td>" + (l_abs[i].getElementsByTagName("validation")[0].childNodes[0].nodeValue ? "Non" : "Oui") + "</td>" +
							 "</tr>"
					 "</tr>"
				}
				texte+="</tbody>"
				// Elément html que l'on va mettre à jour.
				var elt = document.getElementById("table1");
				elt.innerHTML = texte;
				
		};
		//console.log(xhr.responseXML);
	// Envoi de la requête.
		xhr.send(param);
	}
//	else
//		{
//		// Elément html que l'on va mettre à jour.
//		var elt = document.getElementById("table1");
//		elt.innerHTML = "zeebiii";
//		}
	}
	
	/**
 * Lancement après le chargement du DOM.
 */

	