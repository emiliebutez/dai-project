package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import model.Statut;
import model.Utilisateur;

/**
 * CtrlListeAppel
 */
@WebServlet("/CtrlListeAppel")
public class CtrlListeAppel extends HttpServlet {
	/**
	 * doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
        {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Utilisateur samyTest = new Utilisateur("samy.test@test.com", "test", Statut.Enseignant);
            
            t.commit(); // Commit et flush automatique de la session.
        }
	}

	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
