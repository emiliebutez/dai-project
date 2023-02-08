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
}
