package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        		
        		System.out.println(absence.getSessionCours().getId());
        		System.out.println(absence.getUtilisateur().getMail());
        		session.save(absence);
        	}
        	t.commit();
        }
	}
}
