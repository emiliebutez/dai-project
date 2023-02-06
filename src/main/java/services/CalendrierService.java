package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

import dao.HibernateUtil;
import model.SessionCours;
import model.Utilisateur;

public class CalendrierService {
	
	@Transactional
	public List<SessionCours> chercherSessionsCours () {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        
        Date firstDayOfWeek = new Date(calendar.getTime().getTime());
        
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date firstDayOfNextWeek = new Date(calendar.getTime().getTime());
        
        List<SessionCours> sessionsCours = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        sessionsCours = session.createQuery("FROM model.SessionCours s WHERE s.debut >= :borneDebut and s.fin < :borneFin AND ")
            		.setParameter("borneDebut", firstDayOfWeek)
            		.setParameter("borneFin", firstDayOfNextWeek).list();

            return sessionsCours;
        }
	}

}
