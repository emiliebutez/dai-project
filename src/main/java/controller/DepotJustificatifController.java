package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
	public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "C://Justif/";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepotJustificatifController() {
        
    }

	/**
	 * @see doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	
			

		}
	

	/**
	 * @see doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = request.getSession(false);
		Utilisateur u = (Utilisateur)session.getAttribute("utilisateur");
		String nomPrenom = u.getNom() + u.getPrenom();
		String url;
		int id = 1;
		Part filepart = request.getPart("justificatif");
		String[] lstIdChk = (String[])request.getParameterValues("cb_abs");
		
		File dossier = new File("C://Justif/"); 
		
		try { 
			System.out.println(lstIdChk);
			if(lstIdChk != null) {
				// si le directory n'existe pas le creer
				if (!dossier.exists()) {
					boolean res = dossier.mkdir();
				}
				//Créer une copie du PDF
				//on recupere le pdf
				String nomfichier = getNomFichier(filepart);
				System.out.println(nomfichier);
				if (nomfichier!= null) {
						ecrireFichier(filepart, nomfichier, CHEMIN_FICHIERS );
						String chemin = "localhost:8006/m2-dai/justif/" + nomfichier;
						// Enregistre le liens d'acces du fichier en BDD 
						TestHibernate.ajoutJustificatif(lstIdChk,chemin);
						//envoyer un mail a la scolarité
						Mail.envoyerMail(nomPrenom);
						//redirection
						url = "JustificatifController";
						request.setAttribute("msg_info", "Le justificatif a bien été déposé.");
				}else {
				url = "JustificatifController";
				request.setAttribute("msg_erreur", "veuillez déposer un fichier");}
			}else {
			url = "JustificatifController";
			request.setAttribute("msg_erreur", "veuillez cocher au moins une absence");}
			}
		catch (Exception e) {
			{
				url = "JustificatifController";
				request.setAttribute("msg_erreur", "veuillez déposer un fichier");
				}	
			}
		// Chainage.
					request.getRequestDispatcher(url).forward(request, response);
	}
	
	/**
	 * Ecriture du fichier
	 * @param part
	 * @param nomFichier
	 * @param chemin
	 * @throws IOException
	 */
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
		/**
		 * Recuperer le nom du part
		 * @param part
		 * @return
		 */
	 private static String getNomFichier( Part part ) {
	        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	            if ( contentDisposition.trim().startsWith( "filename" ) ) {
	                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	            }
	        }
	        return null;
	    }

}
