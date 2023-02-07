package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.TestHibernate;
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
		javax.servlet.http.HttpSession session = request.getSession(false);
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		String nomPrenom = u.getNom() + u.getPrenom();
		String url;
		int idjust = 1;
		
		String[] lstIdChk = (String[])request.getAttribute("cb_abs");
		String action = request.getParameter("btn");
		
		
		try {
			// si le directory n'existe pas le creer
			
			TestHibernate.validerJust(lstIdChk, action);
//			//envoyer un mail a la scolarité
			Mail.envoyerMail(nomPrenom);
			//redirection
			
			}
		catch (Exception e) {
				
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
