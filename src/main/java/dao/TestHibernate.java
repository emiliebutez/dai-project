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

			Utilisateur u = new Utilisateur("emiliebutez.eb@ut-capitole.fr", "123","Butez", "Emilie", Statut.Etudiant, true, 21801546L);
			Utilisateur u2 = new Utilisateur("nicolas.galceran@ut-capitole.fr","123","Galceran","Nicolas",Statut.Etudiant,true,21874534L);
			Utilisateur u3 = new Utilisateur("malik.belaiba@ut-capitole.fr","123","Belaiba","Malik",Statut.Etudiant,true,21854534L);
			Utilisateur u4 = new Utilisateur("amine.saghir@ut-capitole.fr","123","Saghir","Amine",Statut.Etudiant,true,21709745L);
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
	public static List<String> listchck(){
		List<String> lstk = new ArrayList<String>();

		try (Session session = HibernateUtil.
				getSessionFactory().getCurrentSession()) 
		{Transaction t = session.beginTransaction();


		}
		return lstk;
	}
	public static List<LigneAbsence> recuperationAbs(String email) throws ParseException {

		try (Session session = HibernateUtil.
				getSessionFactory().getCurrentSession()) {
			/*----- Ouverture d'une transaction -----*/
			Transaction t = session.beginTransaction();
			// Liste des abscence d'un etudiant "
			Query Liste = session.createQuery("Select new model.LigneAbsence(u.nom, u.prenom, a.id,s.debut, s.fin, c.nom, g.nom, a.justificatif)" +
					"from model.Utilisateur u, model.Absence a, model.SessionCours s, model.Cours c, model.Groupe g "+
					"where u.id = a.utilisateur.id " +
					"and a.sessionCours.id = s.id " +
					"and s.cours.id = c.id " +
					"and s.groupe.id = g.id "+
					"and u.mail = :email " 
					+
					"and a.validation = false"
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
	public static ArrayList<String> rejectgetmail(String[] lstIdChk) throws ParseException {
		try (Session session = HibernateUtil.
				getSessionFactory().getCurrentSession()) {
			/*----- Ouverture d'une transaction -----*/
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("From model.Absence a "
					+"where a.id IN :ids ");
			query.setParameterList("ids", lstIdChk);
			ArrayList<String>emaillst = new ArrayList<String>();
			for (Object abs : query.list())
				emaillst.add(((Absence)abs).getUtilisateur().getMail());

			t.commit();
			return emaillst;
		}
	}
	public static void validerJust(String [] lstIdChk,String action) throws ParseException {
		try (Session session = HibernateUtil.
				getSessionFactory().getCurrentSession()) {
			/*----- Ouverture d'une transaction -----*/
			System.out.println(action);
			Transaction t = session.beginTransaction();
			if (action.contains("OK")){
				System.out.println("ifXXX");

				Query query = session.createQuery("From model.Absence a "
						+"where a.id IN :ids ");
				query.setParameterList("ids", lstIdChk);
				for (Object abs : query.list()) {
					((Absence)abs).setValidation(true);
					System.out.println("XXX");

				}}

			else if (action.contains("KO")) {
				System.out.println("AHAHA");

				Query query = session.createQuery("From model.Absence a "
						+"where a.id IN :ids ");
				query.setParameterList("ids", lstIdChk);
				for (Object abs : query.list()) {
					((Absence)abs).setJustificatif(null);
					System.out.println("XXX");


				}

			}
			t.commit();}
	}
	/**
	 * Programme de test.
	 * @throws ParseException 
	 */

	public static void main (String[] args) throws ParseException
	{

		//TestHibernate.creationUtilisateur();
		//TestHibernate.validerJust(String[]);




		String[] lst = new String[2];
		lst[0]="1";
		lst[1]="2";
		TestHibernate.validerJust(lst,"OK");


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
			Query Liste = session.createQuery("Select new model.LigneAbsence(u.nom, u.prenom, a.id,s.debut, s.fin, c.nom, g.nom, a.justificatif) " +
					"from model.Utilisateur u, model.Absence a, model.SessionCours s, model.Cours c, model.Groupe g "+
					"where u.id = a.utilisateur.id " +
					"and a.sessionCours.id = s.id " +
					"and s.cours.id = c.id " +
					"and s.groupe.id = g.id "
					+"and a.justificatif is not null "
					+ "and a.validation = false "
					//                   
					);


			List<LigneAbsence> lst = Liste.list();
			return lst;
		}		


	}



} /*----- Fin de la classe TestHibernate -----*/
