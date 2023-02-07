package dao;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.*;

public class EtudiantSessionDao {
	
	public static void miseSessionSession(Long id){
		try (Session session = HibernateUtil.getSessionFactory().openSession())
        {        	
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            SessionCours sessionC = (SessionCours) session.createQuery("FROM model.SessionCours u WHERE u.id = :id")
            		.setParameter("id", id)
            		.uniqueResult();
            
            session.save(sessionC);
        	t.commit(); // Commit et flush automatique de la session.
        	
        }
    }
	
	public static void miseSessionGroupe(SessionCours sessionC){
		try (Session session = HibernateUtil.getSessionFactory().openSession())
        {        	
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Groupe groupe = (Groupe) session.createQuery("FROM model.Groupe grp WHERE grp.id = :id")
            		.setParameter("id", sessionC.getGroupe().getId())
            		.uniqueResult();
            
            session.save(groupe);
        	t.commit(); // Commit et flush automatique de la session.
        	
        }
    }
	
	public static SessionCours recupererSessionDonnee (Long id){
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            SessionCours s = session.get(SessionCours.class, id);
            
            t.commit(); // Commit et flush automatique de la session.
            return s;
        }
    }
	
	public static Groupe recupererGroupe (Long id){
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Groupe grp = session.get(Groupe.class, id);
            
            t.commit(); // Commit et flush automatique de la session.
            return grp;
        }
    }
	
	public static void chargerDonnees() throws ParseException {
		try (Session session = HibernateUtil.getSessionFactory().openSession())
        {  
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Cours courTest = new Cours("courTest");
            Utilisateur samyTest = new Utilisateur("samy.test@test.com", "test", "test", "samy", Statut.Enseignant, false,
        			null);
            
            ZoneOffset zone = ZoneOffset.of("+02:00");
			OffsetDateTime dateDebut = OffsetDateTime.of(2023, 2, 6, 8, 0, 0, 0, zone);
			OffsetDateTime dateFin = OffsetDateTime.of(2023, 2, 6, 9, 30, 0, 0, zone);
            SessionCours sessionCourTest = new SessionCours(dateDebut, dateFin, samyTest, courTest);
            
            Promo promoTest = new Promo("promoTest");
            Groupe groupeTest= new Groupe("groupeTest");
            
            Utilisateur samyTest1 = new Utilisateur("samy1.test@test.com", "test", "test", "samy1", Statut.Etudiant, false,
        			22007246L);
            Utilisateur samyTest2 = new Utilisateur("samy2.test@test.com", "test", "test", "samy2", Statut.Etudiant, true,
            		22007247L);
            Utilisateur samyTest3 = new Utilisateur("samy3.test@test.com", "test", "test", "samy3", Statut.Etudiant, false,
            		22007248L);
            Utilisateur samyTest4 = new Utilisateur("samy4.test@test.com", "test", "test", "samy4", Statut.Etudiant, true,
            		22007249L);
            
            
            groupeTest.addEtudiant(samyTest1);
            groupeTest.addEtudiant(samyTest2);
            groupeTest.addEtudiant(samyTest3);
            groupeTest.addEtudiant(samyTest4);
            samyTest1.addGroupe(groupeTest);
            samyTest2.addGroupe(groupeTest);
            samyTest3.addGroupe(groupeTest);
            samyTest4.addGroupe(groupeTest);
            
            courTest.addSessionCours(sessionCourTest);
            samyTest.addSessionsCours(sessionCourTest);
            
            groupeTest.addSession(sessionCourTest);
            sessionCourTest.setGroupe(groupeTest);
            
            groupeTest.setPromo(promoTest);
            promoTest.addGroupe(groupeTest);
            
            session.save(courTest);
            session.save(samyTest);
            session.save(sessionCourTest);
            session.save(promoTest);
            session.save(groupeTest);
            session.save(samyTest1);
            session.save(samyTest2);
            session.save(samyTest3);
            session.save(samyTest4);
            
            t.commit(); // Commit et flush automatique de la session.
        }
	}
	
	
	
	public static void main (String[] args) throws ParseException{
		EtudiantSessionDao.miseSessionSession(2L);
		SessionCours s = EtudiantSessionDao.recupererSessionDonnee(2L);
		System.out.println(s);
		EtudiantSessionDao.miseSessionGroupe(s);
		Groupe grp = EtudiantSessionDao.recupererGroupe(s.getGroupe().getId());
		for (Utilisateur eleve: grp.getEtudiantsGroupe()) {
			System.out.println(eleve);
		}
	}
	
}
