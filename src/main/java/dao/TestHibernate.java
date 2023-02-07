package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Absence;
import model.LigneAbsence;
import model.Statut;
import model.Utilisateur;

/**
 * Classe de test pour Hibernate.
 */
public class TestHibernate
{
	
	public static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
	
	/**
	 * Ajout utilisateur en base.
	 * @throws ParseException
	 */
	public static void creationUtilisateur () throws ParseException {
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			Transaction t = session.beginTransaction();
			
			Utilisateur u = new Utilisateur("emiliebutez.eb@ut-capitole.fr", "123","butez", "emilie", Statut.Etudiant, true, 21801546L);
			Utilisateur u2 = new Utilisateur("nicolasgalceran@ut-capitole.fr","123","Galceran","nicolas",Statut.Etudiant,true,21874534L);
			Utilisateur u3 = new Utilisateur("malikbelaiba@ut-capitole.fr","123","Belaiba","Malik",Statut.Etudiant,true,21854534L);
			Utilisateur u4 = new Utilisateur("aminesaghir@ut-capitole.fr","123","Saghir","Amine",Statut.Etudiant,true,21709745L);
			Utilisateur u5 = new Utilisateur("alainberro@ut-capitole.fr","123","Berro","Alain",Statut.Enseignant,true,21898455L);
			Utilisateur u6 = new Utilisateur("scolarité_miage@ut-capitole.fr","123","Scolarite","miage",Statut.Scolarite,true,21874534L);
			session.save(u2);
			session.save(u);
			session.save(u3);
			session.save(u4);
			session.save(u5);
			session.save(u6);
			
			t.commit();
		}
	}
	
	/**
	 * Recuperation des données liées au absence d'un etudiant. 
	 * @throws ParseException
	 * @return lst
	 */
	public static List<LigneAbsence> recuperationAbs(String email) throws ParseException {
			try (Session session = HibernateUtil.
	                getSessionFactory().getCurrentSession()) {
			 /*----- Ouverture d'une transaction -----*/
	            Transaction t = session.beginTransaction();
	         // Liste des abscence d'un etudiant "
	            Query Liste = session.createQuery("Select new model.LigneAbsence(a.justificatif, u.nom, u.prenom, a.id,s.debut, s.fin, c.nom, g.nom)" +
	                    "from model.Utilisateur u, model.Absence a, model.SessionCours s, model.Cours c, model.Groupe g "+
	            		"where u.id = a.utilisateur.id " +
	                    "and a.sessionCours.id = s.id " +
	            		"and s.cours.id = c.id " +
	                    "and s.groupe.id = g.id "+
	                    "and u.mail = :email " 
//	                    +
//	                    "and a.validation = false"
	                    );
	            
	            Liste.setParameter("email",email);
	            List<LigneAbsence> lst = Liste.list();
	            return lst;
		 }		
	}
	/**
	 * Programme de test.
	 * @throws ParseException 
	 */

	public static void main (String[] args) throws ParseException
		{
			TestHibernate.creationUtilisateur();
		
			
		
		}

	public static void affichage (List l) {
		System.out.println("------");
		for(int i=0; i<l.size(); i++) {
			for(Object o : (Object[])l.get(i))
				System.out.println(o + " ");
			System.out.println();
		}
	}
	public static List<LigneAbsence> hqlabsSc(String  email){
	
		try (Session session = HibernateUtil.
                getSessionFactory().getCurrentSession()) {
		 /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
         // Liste des abscence d'un etudiant "
            Query Liste = session.createQuery("Select new model.LigneAbsence(a.justificatif, u.nom, u.prenom, a.id,s.debut, s.fin, c.nom, g.nom)" +
                    "from model.Utilisateur u, model.Absence a, model.SessionCours s, model.Cours c, model.Groupe g "+
            		"where u.id = a.utilisateur.id " +
                    "and a.sessionCours.id = s.id " +
            		"and s.cours.id = c.id " +
                    "and s.groupe.id = g.id "
            		//+"and a.justificatif is not null"
                  //  "and u.mail = :email " 
//                    +
//                    "and a.validation = false"
                    );
            
            //Liste.setParameter("email",email);
            List<LigneAbsence> lst = Liste.list();
            return lst;
	 }		
	
			
		}
	public String getNomUsql(int id) {
		try (Session session = HibernateUtil.
                getSessionFactory().getCurrentSession()) {
		 /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Query nom = session.createSQLQuery("SELECT utilisateurs.nom \r\n"
				+ "FROM utilisateurs , absences\r\n"
				+ "WHERE utilisateurs.id = absences.utilisateur_id;");
		return nom.toString();
}

	}


} /*----- Fin de la classe TestHibernate -----*/
