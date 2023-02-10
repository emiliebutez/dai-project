package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import model.Groupe;

/**
 * Servlet implementation class dropetudiantcontroller
 */
public class Dropetudiantcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dropetudiantcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter())
			{
			/*----- Ecriture de la page XML -----*/
			out.println("<?xml version=\"1.0\"?>");
			out.println("<liste_etudiant>");

			/*----- Récupération des paramètres -----*/
			String promo = request.getParameter("promo");

			try {
				/*----- Lecture de liste de mots dans la BD -----*/
				List<Groupe> lgroupe = TestHibernate.getgroupe(promo) ;

				for (Groupe grp : lgroupe)
					out.println("<etd>" + grp.getNom() + "</etd>");
				}
			catch (Exception ex)
				{
				out.println("<etd>Erreur - " + ex.getMessage() + "</etd>");
				}

			out.println("</liste_etudiant>");
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
