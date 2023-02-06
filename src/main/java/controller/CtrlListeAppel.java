package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EtudiantSessionDao;

/**
 * CtrlListeAppel
 */
public class CtrlListeAppel extends HttpServlet {
	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//EtudiantSessionDao.chargerDonnees();
        request.setAttribute("idTestSession", 1);
        request.getRequestDispatcher("/listeAppel.jsp").forward(request, response);
	}

	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
