package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.TestHibernate;
import model.Mail;
import model.Utilisateur;

/**
 * Servlet implementation class DepotJustificatifController
 */
public class DepotJustificatifController extends HttpServlet {
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepotJustificatifController() {
        
    }

	/**
	 * @see doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = request.getSession(false);
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		String nomPrenom = u.getNom() + u.getPrenom();
		String url;
		int idjust = 1;
		
		String[] lstIdChk = (String[])request.getAttribute("cb_abs");
		
		File dossier = new File("C:\\Justif"); 
		
		try {
			// si le directory n'existe pas le creer
			if (dossier.exists()) {}
			else {
			 boolean res = dossier.mkdir();
			}
			//Créer une copie du PDF
			Part filepart = request.getPart("justificatif");
			
			FileSystem fs = FileSystems.getDefault();
			
			File file = new File("C:\\Justif\\justificatif_" + nomPrenom + "_" + idjust + ".pdf");
			
			InputStream is = filepart.getInputStream();
			
			Files.copy(is, file.toPath(),StandardCopyOption.REPLACE_EXISTING);
			String chemin = file.toPath().toString();
			
//	        // Enregistre le liens d'acces du fichier en BDD 
			TestHibernate.ajoutJustificatif(lstIdChk,chemin);
//			//envoyer un mail a la scolarité
			Mail.envoyerMail(nomPrenom);
			//redirection
			
			}
		catch (Exception e) {
				
			}
		}
	

	/**
	 * @see doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
