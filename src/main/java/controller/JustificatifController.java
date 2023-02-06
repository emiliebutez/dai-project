package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import model.Utilisateur;

/**
 * Servlet implementation class JustificatifController
 */
public class JustificatifController extends HttpServlet {
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public JustificatifController() {
        
        
    }

	/**
	 * @see #doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = request.getSession(false);
		String url;
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		String email = u.getMail();
    	try {
			url = "justificatifDocument";
			request.setAttribute("listeAbs", TestHibernate.recuperationAbs(email));
			}
		catch (Exception e)
			{
			url = "accueil";
			request.setAttribute("msg_erreur", e.getMessage());
			}
		// Chainage.
			request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see #doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
