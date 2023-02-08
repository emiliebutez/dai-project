package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Absence;
import model.Mail;
import model.SessionCours;
import model.Utilisateur;
import services.EnregistrementAbsenceService;
import services.EnregistrementRetardService;
import services.SessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Servlet implementation class EnregistrementAbsenceRetard
 */
public class EnregistrementAbsenceRetardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrementAbsenceRetardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] statuts = (String[])request.getParameterValues("statut");
		SessionCours sessionCours = (SessionCours)request.getSession().getAttribute("sessionCours");
		Long idSession = sessionCours.getId();
		
		List<Long> listAbsence = new ArrayList<>();
		List<Long> listRetard = new ArrayList<>();
		
		if ( statuts != null) {
			for (int i = 0; i < statuts.length; i++) {
				 String[] statutIdEleve = statuts[i].split(":");
				 String statusEleve = statutIdEleve[0];
				 Long idEleve = Long.parseLong(statutIdEleve[1]);
				 
				 switch (statusEleve) {
					 case "absent" : 	listAbsence.add(idEleve); 	break;
					 case "retard" : 	listRetard.add(idEleve); 		break;
				 }
			}
		}
		
		List<Utilisateur> eleves = (List<Utilisateur>)request.getSession().getAttribute("eleves");
		
		EnregistrementAbsenceService absenceService = new EnregistrementAbsenceService();
		absenceService.supprimerAbsence(idSession);
		if (!listAbsence.isEmpty()) {
			
			absenceService.enregistrementListAbsence(listAbsence, idSession);
			
			if(request.getParameter("validation") != null){
				envoiMail(sessionCours);
			}
		}
		
		EnregistrementRetardService retardService = new EnregistrementRetardService();
		retardService.supprimerRetard(eleves, idSession);
		
		if (!listRetard.isEmpty()) {
			retardService.enregistrementListRetard(listRetard, idSession);
		} 
		
		if(request.getParameter("validation") != null){
			SessionService sessionService = new SessionService();
			sessionService.appelValide(sessionCours);
		}
		
		String link = "/CtrlListeAppel?idSession=" + idSession;
		request.getRequestDispatcher(link).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void envoiMail(SessionCours sessionCours) {
		EnregistrementAbsenceService absenceService = new EnregistrementAbsenceService();
		List<Absence> absences = absenceService.recupererAbsence(sessionCours.getId());
		Mail mailService = new Mail();
		
		for(Absence absence : absences) {
			mailService.envoyerMailAbsenceEtudiants(absence.getUtilisateur().getMail(), sessionCours);
		}
	}

}
