package dao;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.*;

public class EtudiantSessionDao {

	public static Set<Utilisateur> chargerEtudiantsSession (long id){
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
        {        	
        	SessionCours s = session.load(SessionCours.class, id);
        	Set<Utilisateur> etudiants = new HashSet<>();
        	for (Utilisateur eleve: s.getGroupe().getEtudiantsGroupe()) {
        		etudiants.add(eleve);
        	}
                	
        	return etudiants;
        }
    	
    }
	
	public static void chargerDonnees() throws ParseException {
		try (Session session = HibernateUtil.getSessionFactory().openSession())
        {  
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			/*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Cours courTest = new Cours(1L, "courTest");
            Utilisateur samyTest = new Utilisateur("samy.test@test.com", "test", "test", "samy", Statut.Enseignant, false,
        			null);
            
            OffsetDateTime debutCours = OffsetDateTime.parse("2023-02-06T08:30:00");
            OffsetDateTime finCours = OffsetDateTime.parse("2023-02-06T09:30:00");
            SessionCours sessionCourTest = new SessionCours(1L, debutCours, finCours, samyTest, courTest);
            
            Promo promoTest = new Promo(1L, "promoTest");
            Groupe groupeTest= new Groupe(1L, "groupeTest");
            
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
		EtudiantSessionDao.chargerDonnees();
	}
	
}
