package dao;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.*;

public class EtudiantSessionDao {

	public static void miseSessionDonnee (Long id){
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
        {        	
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Query a = session.createQuery("SELECT u.id, u.nom, u.prenom "
            							+ "FROM model.Groupe g, model.Utilisateur u "
            							+ "WHERE g.id = u.etudiantsGroupe"
            							+ "AND g.id = :id")
            		.setParameter("id", id);
            
            List<Utilisateur> eleves = a.list();
            
            for (Utilisateur u: eleves) {
            	System.out.println(u);
            }
            
            //session.save(courTest);
        	t.commit(); // Commit et flush automatique de la session.
        	
        }
    }
	
	public static void miseSession(){
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
        {        	
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            SessionCours courTest = (SessionCours) session.createQuery("FROM model.SessionCours u WHERE u.id = :id")
            		.setParameter("id", 2)
            		.uniqueResult();
            
            session.save(courTest);
        	t.commit(); // Commit et flush automatique de la session.
        	
        }
    }
	
	public static Cours recupererSessionDonnee (Long id){
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Cours c = session.get(Cours.class, id);
            
            t.commit(); // Commit et flush automatique de la session.
            return c;
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
		EtudiantSessionDao.miseSession();
		Cours c = EtudiantSessionDao.recupererSessionDonnee(1L);
		EtudiantSessionDao.miseSessionDonnee(5L);
	}
	
}
