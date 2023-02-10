package services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	@Transactional
	public List<Utilisateur> retrouverEtudiants (Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
//        	List<Utilisateur> queryResult = session.createQuery("SELECT u "
//        															+"FROM Utilisateur u,  SessionCours s  "
//																	+ "JOIN u.groupes g "
//																	+ "WHERE s.groupe.id = g.id "
//																	+ "and s.id = :idSession "
//																	+ "ORDER BY u.nom", Utilisateur.class)
//																	.setParameter("idSession", id).list();
        	
        	List<Utilisateur> queryResult = session.createQuery("SELECT u "
					+"FROM Utilisateur u "
					+ "JOIN u.groupes g "
					+ "JOIN g.sessionsCours s "
					+ "WHERE s.id = :idSession "
					+ "ORDER BY u.nom", Utilisateur.class)
					.setParameter("idSession", id).list();
        	return queryResult;
		}
	}
	
}
