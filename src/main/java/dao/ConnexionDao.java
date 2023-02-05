package dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.istack.Nullable;

import model.Utilisateur;

public class ConnexionDao {
	
	@Transactional
	public @Nullable Utilisateur chercherUtilisateur(String email, String mdp) {
		System.out.println("pouet pouet");
		Utilisateur utilisateur = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // get an user object
            utilisateur = (Utilisateur) session.createQuery("FROM model.Utilisateur u WHERE u.mail = :email and u.mdp = :mdp")
            		.setParameter("email", email)
            		.setParameter("mdp", mdp)
            		.uniqueResult();

            return utilisateur;
        }
	}
	
}
