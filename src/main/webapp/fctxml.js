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
					texte += "<td>" + l_abs[i].getElementsByTagName("cours")[0].childNodes[0].nodeValue + + "</td>"+
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
	
	/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
	document.getElementById("mois").addEventListener("change",afficherTableau);
	});	
	