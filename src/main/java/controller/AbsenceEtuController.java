package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import model.Utilisateur;
import model.LigneAbsence;

/**
 * Servlet implementation class AbsenceEtuController
 */
public class AbsenceEtuController extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbsenceEtuController() {
    
    }
      

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		javax.servlet.http.HttpSession session = request.getSession(false);
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		String email = u.getMail();
		
		/*----- Lecture de la requête en UTF-8 -----*/
		request.setCharacterEncoding("UTF-8");
		/*----- Type de la réponse -----*/
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter()){
		
		/*----- Ecriture de la page XML -----*/
		out.println("<?xml version=\"1.0\"?>");
		out.println("<lstAbsence>");
		

		/*----- Récupération des paramètres -----*/
		String mois = request.getParameter("mois");
		try {
			/*----- Lecture de liste de mots dans la BD -----*/
			System.out.println(mois + " " + email);
			List<LigneAbsence> lstabs = TestHibernate.afficherAbsEtu(mois, email);
			
//			OffsetDateTime dt1=OffsetDateTime.parse("2023-02-20T12:00+01:00");
//			OffsetDateTime dt2=OffsetDateTime.parse("2023-02-20T12:00+01:00");
//			
//			LigneAbsence abs1 = new LigneAbsence("nicolas", "nfer", 1L,dt1,dt2, "DAI", "G1", "k", false);
//			List<LigneAbsence> lstabs2 = new ArrayList<>();
//			lstabs2.add(abs1);
			
			for (LigneAbsence abs : lstabs) {
				out.println("<Absence><cours><![CDATA[" + abs.getNomCours() + "]]></cours>");
				out.println("<debut><![CDATA[" + abs.getDtdebut().getDayOfMonth() + "/" + abs.getDtdebut().getMonthValue() + "/" + abs.getDtdebut().getYear() + " " + abs.getDtdebut().getHour() + ":" + abs.getDtdebut().getMinute()+ "]]></debut>");
				out.println("<fin><![CDATA[" + abs.getDtfin().getDayOfMonth() + "/" + abs.getDtfin().getMonthValue() + "/" + abs.getDtfin().getYear() + " " + abs.getDtfin().getHour() + ":" + abs.getDtfin().getMinute()+ "]]></fin>");
				out.println("<groupe><![CDATA[" + abs.getNomGroupe() + "]]></groupe>");
				out.println("<validation><![CDATA[" + abs.getValidation() + "]]></validation></Absence>");
			}
			}
		catch (Exception e)
		{
		out.println("<lstAbsence>Erreur - " + e.getMessage() + "</lstAbsence>");
		}
		
		out.println("</lstAbsence>");
		}
	}
	
}	



