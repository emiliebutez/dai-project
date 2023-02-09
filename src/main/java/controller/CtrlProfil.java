package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtilisateurDAO;
import jdk.internal.org.jline.utils.InputStreamReader;
import model.Utilisateur;

/**
 * Servlet implementation class CtrlProfil
 */
@MultipartConfig
public class CtrlProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession(true).getAttribute("utilisateur");
		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("modifProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] picture = request.getPart("file").getInputStream().readAllBytes();
		Long id = Long.parseLong(new String(request.getPart("userID").getInputStream().readAllBytes()));
		
		Utilisateur utilisateur = UtilisateurDAO.updatePhoto(id, picture);
		request.getSession(true).setAttribute("utilisateur", utilisateur);
		response.sendRedirect(getServletInfo());
	}

}
