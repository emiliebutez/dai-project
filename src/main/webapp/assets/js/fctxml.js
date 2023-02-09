/**
 * 
 */

 /**
  * Cette fonction Affiche les différents tableaux en fonction du mois choisi
  */
  function afficherTableau ()
  {
	  if (document.getElementById("mois").value !== "")
	  {
		  // Objet XMLHttpRequest.
		var xhr = new XMLHttpRequest();
		var param = "mois=" + encodeURIComponent(document.getElementById("mois").value);
		
		xhr.open("POST","AbsenceEtuController");
		// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
		xhr.onload = function()
		{
			// Si la requête http s'est bien passée.
			if (xhr.status === 200)
				{
				// Réponse du serveur.
				var l_nuplet = xhr.responseXML.getElementsByTagName("Absence");
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
        						"</tr></thead><tbody>";
        		for (var i=0; i<l_nuplet.length; i++){
					texte += "<tr><td>" + cours[i].firstChild.nodeValue + "</td>"+
							 "<td>" + debut[i].firstChild.nodeValue + "</td>"+
							 "<td>" + fin[i].firstChild.nodeValue + "</td>"+
							 "<td>" + groupe[i].firstChild.nodeValue + "</td>"+
							 "</tr>"
				}
				// Elément html que l'on va mettre à jour.
				var elt = document.getElementById("table1");
				elt.innerHTML = texte;
				}
		};
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