package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
      

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

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
		out.println("Absence");

		/*----- Récupération des paramètres -----*/
		String mois = request.getParameter("mois");
		try {
			/*----- Lecture de liste de mots dans la BD -----*/
			List<LigneAbsence> lstabs = TestHibernate.afficherAbsEtu(mois, email);
			for (LigneAbsence abs : lstabs) {
				out.println("<cours><![CDATA[" + abs.getNomCours() + "]]></cours>");
				out.println("<debut><![CDATA[" + abs.getDtdebut() + "]]></debut>");
				out.println("<fin><![CDATA[" + abs.getDtfin() + "]]></fin>");
				out.println("<groupe><![CDATA[" + abs.getNomGroupe() + "]]></groupe>");
			}
			}
		catch (ClassNotFoundException | SQLException ex)
		{
		out.println("<lstAbsence>Erreur - " + ex.getMessage() + "</lstAbsence>");
		}
		out.println("Absence");
		out.println("</lstAbsence>");
		}
	}
	
	}


