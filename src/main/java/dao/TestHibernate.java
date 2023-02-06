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
	            Query Liste = session.createQuery("Select new model.LigneAbsence(s.debut, s.fin, c.nom, g.nom)" +
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
			
			System.out.println(TestHibernate.recuperationAbs("ZFZEFZE@gmail.com"));
		}

	public static void affichage (List l) {
		System.out.println("------");
		for(int i=0; i<l.size(); i++) {
			for(Object o : (Object[])l.get(i))
				System.out.println(o + " ");
			ArrayList
			System.out.println();
		}
	}
	public static List<String> hqlabsSc(){
	
	/*----- Ouverture de la session -----*/
	try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
		{
		/*----- Ouverture d'une transaction -----*/
		session.beginTransaction();

		/**
		 * Liste des employés ayant fait plus de 2 demandes.
		 */

		/*----- Requête HQL, retour sous forme d'une liste de tableau d'objets  -----*/
		List liste1 = session.createSQLQuery("select * " +
										  		"From `absences`as a " +
										  		"where a.justificatif is NOT null").list();
		System.out.println("----- HQL 11a -----");
		
		return liste1;}
	
	
	}

	public static void main (String[] args) throws ParseException
 
	{	
		//TestHibernate.creationUtilisateur();
		TestHibernate.affichage(hqlabsSc());;
	}


} /*----- Fin de la classe TestHibernate -----*/
