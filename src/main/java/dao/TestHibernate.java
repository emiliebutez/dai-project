package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			
			Utilisateur u = new Utilisateur("emiliebutez.eb@gmail.com", "123", Statut.Etudiant);
			
			session.save(u);
			
			t.commit();
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

} /*----- Fin de la classe TestHibernate -----*/
