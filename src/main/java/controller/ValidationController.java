package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestHibernate;
import model.Utilisateur;

/**
 * Servlet implementation class ValidationController
 */
public class ValidationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = request.getSession(false);
		String url;
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		String email = u.getMail();
    	try {
			url = "validerJust.jsp";
			request.setAttribute("listeAbs", TestHibernate.hqlabsSc(email));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
