package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.TestHibernate;
import model.Absence;
import model.Mail;
import model.Utilisateur;

/**
 * Servlet implementation class Tabtruevalidation
 */
public class Tabtruevalidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Tabtruevalidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("balaldkedkl");
		javax.servlet.http.HttpSession session = request.getSession(false);
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		String nomPrenom = u.getNom() + u.getPrenom();

		String[] lstIdChk = (String[])request.getParameterValues("cb_abs");

		String action = request.getParameter("btn");


		try {
			for(String s : lstIdChk) {
				System.out.println(s);}
			System.out.println("gfkdjgfd");
			// si le directory n'existe pas le creer

			TestHibernate.validerJust(lstIdChk, action);
			
			ArrayList<String> lstmail = new ArrayList<>();
			lstmail = TestHibernate.rejectgetmail(lstIdChk);
			for (String eml : lstmail) {
				Mail.envoyerMail(eml);
			}


		}
		catch (Exception e) {
			String url = "accueil";
			request.setAttribute("msg_erreur", e.getMessage());
			}
		// Chainage.
		String url = "accueil";
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
