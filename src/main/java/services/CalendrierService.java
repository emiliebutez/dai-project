package services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Session;

import dao.HibernateUtil;
import model.SessionCours;
import model.Utilisateur;

public class CalendrierService {
	
	@Transactional
	public List<List<SessionCours>> chercherSessionsCoursEtudiant (HttpServletRequest request, HttpServletResponse response) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        
        Date firstDayOfWeek = new Date(calendar.getTime().getTime());
        
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date firstDayOfNextWeek = new Date(calendar.getTime().getTime());
        
        Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
        Long utilisateurIdSession = utilisateur.getId();
        
        List<SessionCours> sessionsCours = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//        sessionsCours = session.createQuery("FROM model.SessionCours s, model.Groupe g, model.Utilisateur u "
//        															+ "WHERE s.debut >= :borneDebut "
//        															+ "AND s.fin < :borneFin "
//        															+ "AND s.groupe.id = g.id "
//        															+ "AND  u.groupes.id = g.id "
//        															+ "AND g.etudiantsGroupe.id = u.id "
//        															+ "AND u.id = :idEtudiant ")
//            		.setParameter("borneDebut", firstDayOfWeek)
//            		.setParameter("borneFin", firstDayOfNextWeek)
//            		.setParameter("idEtudiant", 1L)
//            		.list();
        	
          Map<Integer, List<SessionCours>> queryResult = session.createQuery("SELECT s "
        		  													+ "FROM SessionCours s, Groupe g "
          															+ "JOIN g.etudiantsGroupe u "
																	+ "WHERE s.debut >= :borneDebut "
																	+ "AND s.fin < :borneFin "
																	+ "AND s.groupe.id = g.id  "
																	+ "AND u.id = :idEtudiant "
																	+ "ORDER BY s.debut", SessionCours.class)
					.setParameter("borneDebut", firstDayOfWeek)
					.setParameter("borneFin", firstDayOfNextWeek)
					.setParameter("idEtudiant", utilisateurIdSession)
					.stream()
					.collect(Collectors.groupingBy(s  -> ((SessionCours) s).getDebut().getDayOfWeek().getValue() - 1));
          
          List<List<SessionCours>> result = new ArrayList<>();
          for (int i = 0; i < 7; ++i) {
        	  result.add(queryResult.getOrDefault(i, new ArrayList<>()));
          }
          
          return result;
        }
	}
	
//	@Transactional
//	public List<SessionCours> chercherSessionsCoursEnseignant (HttpServletRequest request, HttpServletResponse response) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.clear(Calendar.SECOND);
//        calendar.clear(Calendar.MINUTE);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
//        
//        Date firstDayOfWeek = new Date(calendar.getTime().getTime());
//        
//        calendar.add(Calendar.DAY_OF_MONTH, 7);
//        Date firstDayOfNextWeek = new Date(calendar.getTime().getTime());
//        
//        Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
//        Long utilisateurIdSession = utilisateur.getId();
//        
//        List<SessionCours> sessionsCours = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//        	
//        	sessionsCours = session.createQuery("SELECT s FROM SessionCours s, Utilisateur u WHERE s.debut >= :borneDebut and s.fin < :borneFin and u.id = s.enseignant.id and u.id = :idEnseignants", SessionCours.class)
//            		.setParameter("borneDebut", firstDayOfWeek)
//            		.setParameter("borneFin", firstDayOfNextWeek).setParameter("idEnseignants", utilisateurIdSession).list();
//        	
//        	//List<SessionCours> result = sessionsCours.stream().filter(x -> x.getEnseignant().getId().equals(utilisateurIdSession)).collect(Collectors.toList());
//        	
//        return sessionsCours;
//        }
//	}
}
