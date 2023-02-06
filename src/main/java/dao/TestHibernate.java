package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
			
			Utilisateur u = new Utilisateur("emiliebutez.eb@gmail.com", "123","emilie","Butez",Statut.Etudiant ,true, 21705534L);
			
			session.save(u);
			
			t.commit();
		}
	}
	
	/**
	 * Programme de test.
	 * @throws ParseException 
	 */
	
	
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
