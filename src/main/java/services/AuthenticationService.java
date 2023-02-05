package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnexionDao;
import model.Utilisateur;

public class AuthenticationService {
	private ConnexionDao connexionDao = new ConnexionDao();
	
	public Utilisateur authenticate(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        
        Utilisateur utilisateur = connexionDao.chercherUtilisateur(email, mdp);
        if (utilisateur == null) {
        	return null;
        }
        
        request.getSession(true).setAttribute("utilisateur", utilisateur);
        return utilisateur;
    }
	
	
}
