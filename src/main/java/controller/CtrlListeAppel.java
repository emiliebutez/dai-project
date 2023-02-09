package controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EtudiantSessionDao;
import model.Groupe;
import model.SessionCours;
import model.Utilisateur;
import services.EnregistrementAbsenceService;
import services.SessionService;

/**
 * CtrlListeAppel
 */
public class CtrlListeAppel extends HttpServlet {
	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Long id = Long.parseLong(request.getParameter("idSession"));
		
		SessionService sessionService = new SessionService();
		SessionCours sessionCours = sessionService.retrouverSession(id);
		request.getSession(true).setAttribute("sessionCours", sessionCours);
		
		System.out.println(sessionCours.getCours().getNom());
		
		EtudiantSessionDao.miseSessionSession(id);
		SessionCours s = EtudiantSessionDao.recupererSessionDonnee(id);
		EtudiantSessionDao.miseSessionGroupe(s);
		Groupe grp = EtudiantSessionDao.recupererGroupe(s.getGroupe().getId());
		Set<Utilisateur> eleves = grp.getEtudiantsGroupe();
		for (Utilisateur eleve: eleves) {
			System.out.println(eleve);
		}
		
		//if (eleves.size() == 0) {
			//request.getRequestDispatcher("/accueil.jsp").forward(request, response);
		//} else {
			// Si ok mise en session et envoie vers la page de liste d'appel
		request.getSession(true).setAttribute("eleves", eleves);
		EnregistrementAbsenceService absenceService = new EnregistrementAbsenceService();
		request.getSession(true).setAttribute("absences", absenceService.recupererAbsence(id));
		
		request.getRequestDispatcher("/listeAppel.jsp").forward(request, response);
		//}        
	}

	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}