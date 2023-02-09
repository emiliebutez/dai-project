package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtilisateurDAO;
import model.Utilisateur;

/**
 * Servlet implementation class CtrlModifPhoto
 */
public class CtrlModifPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Long id = Long.parseLong(request.getParameter("userID"));
		//byte[] newPhoto = new byte[(int)request.getParameter("newName")]
		String newPhoto = request.getParameter("newName");
		Utilisateur utilisateur = UtilisateurDAO.updatePhoto(id, newPhoto);
		session.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("modifProfil.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
