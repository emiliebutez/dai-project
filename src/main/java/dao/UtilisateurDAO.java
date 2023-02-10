package dao;

import org.hibernate.*;

import model.Utilisateur;

public class UtilisateurDAO {

	public static Utilisateur updatePhoto(Long id,byte[] newPhoto) {
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			Transaction t = session.beginTransaction();
			
			Utilisateur user = (Utilisateur) session.get(Utilisateur.class, id);
			user.setPhoto(newPhoto);
			session.update(user);
			t.commit();
			return user;
		}
	}
	
	
}