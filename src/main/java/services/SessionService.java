package services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import model.SessionCours;
import model.Utilisateur;

public class SessionService {
	
	@Transactional
	public SessionCours retrouverSession (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	return session.get(SessionCours.class, id);
        }
	}
	
	public void appelValide(SessionCours sessionCours) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Transaction t = session.beginTransaction();
        	sessionCours.setAppelTermine(true);
        	session.update(sessionCours);
        	t.commit();
        }
	}
}
