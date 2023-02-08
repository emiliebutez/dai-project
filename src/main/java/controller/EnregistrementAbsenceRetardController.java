package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import services.EnregistrementAbsenceService;
import services.EnregistrementRetardService;

import java.util.ArrayList;
import java.util.List;

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
		Long idSession = (Long)request.getSession().getAttribute("session");
		
		if ( checkboxAbsence != null) {
			List<Long> listCheckboxAbsence = new ArrayList<>();
			
			for (int i = 0; i < checkboxAbsence.length; i++) {
				listCheckboxAbsence.add(Long.parseLong(checkboxAbsence[i]));
			}
			EnregistrementAbsenceService absenceService = new EnregistrementAbsenceService();
			absenceService.enregistrementListAbsence(listCheckboxAbsence, idSession);
		}
		
		if (checkboxRetard != null) {
			List<Long> listcheckboxRetard = new ArrayList<>();		
			
			for (int i = 0; i < checkboxRetard.length; i++) {
				listcheckboxRetard.add(Long.parseLong(checkboxRetard[i]));
			}
			EnregistrementRetardService retardService = new EnregistrementRetardService();
			retardService.enregistrementListRetard(listcheckboxRetard, idSession);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
