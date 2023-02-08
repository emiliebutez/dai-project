package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import model.Absence;
import model.SessionCours;
import model.Utilisateur;
public class EnregistrementAbsenceService {

	@Transactional
	public void enregistrementListAbsence (List<Long> listIdUtilisateur, Long idSession) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Transaction t = session.beginTransaction();
        	for (Long id : listIdUtilisateur) {
        		Utilisateur eleve = session.get(Utilisateur.class, id);
        		SessionCours sessionCours = session.get(SessionCours.class, idSession);
        		
        		
        		Absence absence = new Absence(eleve, sessionCours);
        		
        		session.save(absence);
        	}
        	t.commit();
        }
	}
	
	@Transactional
	public void supprimerAbsence (Long idSession) {
		List<Absence> listAbsence = recupererAbsence(idSession);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Transaction t = session.beginTransaction();
        	for (Absence absence : listAbsence) {
        		session.delete(absence);
        	}
        	t.commit();
        }
	}
	
	@Transactional
	public List<Absence> recupererAbsence (Long idSession) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	List<Absence> queryResult = session.createQuery("FROM Absence a "
								+ "WHERE a.sessionCours.id = :idSession ", Absence.class).setParameter("idSession", idSession).list();
        	
        	return queryResult;
        }
	}
}
