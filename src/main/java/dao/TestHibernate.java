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
			
			Utilisateur u = new Utilisateur("emiliebutez.eb@gmail.com", "123","butez", "emilie", Statut.Etudiant, true, 21801546L);

			
			session.save(u);
			
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
	            Query Liste = session.createQuery("Select new model.LigneAbsence(u.nom, u.prenom, a.id,s.debut, s.fin, c.nom, g.nom)" +
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
	 * 
	 * @throws ParseException
	 */
	public static void ajoutJustificatif(String[] lstIdChk, String justificatifPath) throws ParseException {
		try (Session session = HibernateUtil.
                getSessionFactory().getCurrentSession()) {
		 /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("From model.Absence a "
            								+"where a.id IN :ids ");
            query.setParameterList("ids", lstIdChk);
            for (Object abs : query.list())
            	((Absence)abs).setJustificatif(justificatifPath);
            t.commit();
        }
	}
	/**
	 * Programme de test.
	 * @throws ParseException 
	 */

	public static void main (String[] args) throws ParseException
		{
		String[] lst = new String[1];
		lst[0]="1";
		TestHibernate.ajoutJustificatif(lst,"TEST/document");
			
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
            Query Liste = session.createQuery("Select new model.LigneAbsence(u.nom, u.prenom, a.id,s.debut, s.fin, c.nom, g.nom)" +
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
