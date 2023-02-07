package controller;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cours;
import model.SessionCours;
import model.Statut;
import model.Utilisateur;
import services.CalendrierService;
import services.Week;

/**
 * Servlet implementation class AccueilController
 */
public class AccueilController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		if (u == null || u.getMail().isEmpty()) {
			response.sendRedirect("index");
			return;
		}
		
		CalendrierService calendrier = new CalendrierService();
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
		Statut statut = utilisateur.getStatut();
		List<List<SessionCours>> sessionsCours = null;
		
		switch(statut) {
		
		case Etudiant : 
			sessionsCours = calendrier.chercherSessionsCoursEtudiant(request, response);
			request.setAttribute("sessionsCours", new Week(sessionsCours));
			break;
		case Enseignant :
			sessionsCours = calendrier.chercherSessionsCoursEnseignant(request, response);
			request.setAttribute("sessionsCours", new Week(sessionsCours));
			break;
		case Scolarite :
			break;
		}
		
		request.getRequestDispatcher("/accueil.ftl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected List<List<SessionCours>> AjoutPause(List<List<SessionCours>> list) {
		Cours coursPause = new Cours("Pause");
		ZoneOffset zone = ZoneOffset.of("+02:00");
		OffsetDateTime dateDebutJournee = OffsetDateTime.of(2023, 2, 6, 8, 0, 0, 0, zone);
		OffsetDateTime dateFin = OffsetDateTime.of(2023, 2, 6, 9, 30, 0, 0, zone);
		SessionCours pause = new SessionCours();
		pause.setCours(coursPause);
//		for ( int i = 0; i< list.size(); i++) {
//			for (int y = 0; i<list.get(i).size(); i++) {
//				if (list.get(i).get(y).getDebut() >  dateDebutJournee) {
//					list.get(i).add(pause);
//				}
//			}
//		}
		
		return list;
	}

}
