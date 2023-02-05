package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnexionDao;
import model.Utilisateur;
import services.AuthenticationService;

/**
 * Servlet implementation class ConnexionController
 */
public class ConnexionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthenticationService authenticationService = new AuthenticationService();
	ConnexionDao connexionDao = new ConnexionDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = authenticationService.authenticate(request, response);
		if (utilisateur == null) {
			request.setAttribute("msg_erreur", "Email ou mot de passe incorrects");
			request.getRequestDispatcher("index").forward(request, response);
		} else {
			response.sendRedirect("accueil");
		}
	}

}
