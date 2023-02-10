package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import model.Utilisateur;
import services.UtilisateurService;

public class ImageController extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(ImageController.class);
	private UtilisateurService utilisateurService = new UtilisateurService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("image/jpeg");
		try (ServletOutputStream output = resp.getOutputStream()) {
			try (ByteArrayOutputStream byteOutput = new ByteArrayOutputStream()) {
				Utilisateur utilisateur = utilisateurService.getUtilisateur(Long.parseLong(req.getParameter("id")));
				if (utilisateur != null && utilisateur.getPhoto() != null) {
					byteOutput.write(utilisateur.getPhoto());
					byteOutput.writeTo(output);
				}
			}
		}
	}
}
