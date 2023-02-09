package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class PdfController
 */
public class PdfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("idSession"));
		
		SessionService sessionService = new SessionService();
		SessionCours sessionCours = sessionService.retrouverSession(id);
		
		System.out.println(sessionCours.getCours().getNom());
		
		EtudiantSessionDao.miseSessionSession(id);
		SessionCours s = EtudiantSessionDao.recupererSessionDonnee(id);
		EtudiantSessionDao.miseSessionGroupe(s);
		Groupe grp = EtudiantSessionDao.recupererGroupe(s.getGroupe().getId());
		List<Utilisateur> eleves = new ArrayList<>(grp.getEtudiantsGroupe());
		
		eleves.sort((u1, u2) -> u1.getNom().compareTo(u2.getNom()));
		
		request.getRequestDispatcher("/listeAppelPdf.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
