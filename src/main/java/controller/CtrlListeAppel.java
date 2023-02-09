package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Long id = Long.parseLong(request.getParameter("idSession"));
		
		SessionService sessionService = new SessionService();
		SessionCours sessionCours = sessionService.retrouverSession(id);
		request.getSession(true).setAttribute("sessionCours", sessionCours);

		List<Utilisateur> eleves = sessionService.retrouverEtudiants(id);
		
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
