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
		String[] checkboxAbsence = (String[])request.getParameterValues("absence");
		String[] checkboxRetard = (String[])request.getParameterValues("retard");
		SessionCours sessionCours = (SessionCours)request.getSession().getAttribute("sessionCours");
		Long idSession = sessionCours.getId();
		
		Set<Utilisateur> eleves = (Set<Utilisateur>)request.getSession().getAttribute("eleves");
		
		EnregistrementAbsenceService absenceService = new EnregistrementAbsenceService();
		absenceService.supprimerAbsence(idSession);
		if ( checkboxAbsence != null) {
			List<Long> listCheckboxAbsence = new ArrayList<>();
			
			for (int i = 0; i < checkboxAbsence.length; i++) {
				listCheckboxAbsence.add(Long.parseLong(checkboxAbsence[i]));
			}
			absenceService.enregistrementListAbsence(listCheckboxAbsence, idSession);
			
			if(request.getParameter("validation") != null){
				envoiMail(sessionCours);
			}
		}
		
		EnregistrementRetardService retardService = new EnregistrementRetardService();
		retardService.supprimerRetard(eleves, idSession);
		
		if (checkboxRetard != null) {
			List<Long> listcheckboxRetard = new ArrayList<>();		
			
			for (int i = 0; i < checkboxRetard.length; i++) {
				listcheckboxRetard.add(Long.parseLong(checkboxRetard[i]));
			}
			retardService.enregistrementListRetard(listcheckboxRetard, idSession);
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
