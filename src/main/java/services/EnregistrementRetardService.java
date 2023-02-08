package services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import model.Absence;
import model.SessionCours;
import model.Utilisateur;

public class EnregistrementRetardService {

	@Transactional
	public void enregistrementListRetard (List<Long> listIdUtilisateur, Long idSession) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Transaction t = session.beginTransaction();
        	for (Long id : listIdUtilisateur) {
        		Utilisateur eleve = session.get(Utilisateur.class, id);
        		SessionCours sessionCours = session.get(SessionCours.class, idSession);
        		
        		eleve.getSessionsCours().add(sessionCours);
        		sessionCours.getEtudiants().add(eleve);
        		
        		session.save(eleve);
        		session.save(sessionCours);
        	}
        	t.commit();
        }
	}
	
	@Transactional
	public void supprimerRetard (Set<Utilisateur> listUtilisateur, Long idSession) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Transaction t = session.beginTransaction();
        	for (Utilisateur utilisateur : listUtilisateur) {
        		
        		Utilisateur eleve = session.get(Utilisateur.class, utilisateur.getId());
        		SessionCours sessionCours = session.get(SessionCours.class, idSession);
        		
        		eleve.getSessionsCours().remove(sessionCours);
        		sessionCours.getEtudiants().remove(eleve);
        		
        		session.save(eleve);
        		session.save(sessionCours);
        	}
        	t.commit();
        }
	}
}
