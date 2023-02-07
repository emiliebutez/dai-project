package services;

import org.hibernate.Session;

import com.sun.istack.Nullable;

import dao.HibernateUtil;
import model.Utilisateur;

public class UtilisateurService {
	public @Nullable Utilisateur getUtilisateur(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return  (Utilisateur) session.createQuery("FROM model.Utilisateur u WHERE u.id = :id")
		 		.setParameter("id", id)
		 		.uniqueResult();
		}
	}
}
