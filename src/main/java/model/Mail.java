package model;

import java.util.Properties;
import javax.mail.*;  
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;

public class Mail {
	/**
	   * Host du mail.
	   */
	  private static final String HOST = "smtp-mail.outlook.com";
	  /**
	   * Adresse de la boite mail d'envoi.
	   */
	  private static final String USER = "devAgile@outlook.fr";
	  /**
	   * Mot de passe de la boite mail d'envoi.
	   */
	  private static final String MDP = "A123456789b";
	  
	  /**
	   * 
	   * @param to
	   */
	  public final static void envoyerMail(String nomPrenomU) {
		  String to = "xfourix@gmail.com";
	   Properties props = new Properties();
	   props.put("mail.smtp.host", HOST);
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   Authenticator auth = new Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(USER, MDP);
	      }
	    };
	    Session session = Session.getInstance(props, auth);
	    
	    try {
	     MimeMessage message = new MimeMessage(session);
	     message.setFrom(new InternetAddress(USER));
	     message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	     message.setSubject("Justificatif a valider");
	     message.setText("Vous avez un nouveau justificatif a valider : "
	             + "Justificatif de " + nomPrenomU
	             + ".");
	     
	    Transport.send(message);
	   
	     } catch (MessagingException e) {e.printStackTrace();}
	 }
	  public final static void envoyerMail2(String email) {
		  String to = email;
	   Properties props = new Properties();
	   props.put("mail.smtp.host", HOST);
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   Authenticator auth = new Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(USER, MDP);
	      }
	    };
	    Session session = Session.getInstance(props, auth);
	    
	    try {
	     MimeMessage message = new MimeMessage(session);
	     message.setFrom(new InternetAddress(USER));
	     message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	     message.setSubject("Justificatif a valider");
	     message.setText("Votre justificatif a été rejeté veuillez deposer un nouveau : "
	             + "Justificatif de " 
	             + ".");
	     
	    Transport.send(message);
	   
	     } catch (MessagingException e) {e.printStackTrace();}
	 }
	  
	  public void envoyerMailAbsenceEtudiants(String email, SessionCours sessionCours) {
		  String to = email;
	   Properties props = new Properties();
	   props.put("mail.smtp.host", HOST);
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   Authenticator auth = new Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(USER, MDP);
	      }
	    };
	    Session session = Session.getInstance(props, auth);
	    
	    try {
	     MimeMessage message = new MimeMessage(session);
	     message.setFrom(new InternetAddress(USER));
	     message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	     message.setSubject("Absence");
	     message.setText("Vous avez étez absent au cours de  "
	    		 + sessionCours.getCours().getNom()
	             + ". Veuillez déposer un justificatif sur l'application pour justifier de votre absence" 
	             + ".");
	     
	    Transport.send(message);
	   
	     } catch (MessagingException e) {e.printStackTrace();}
	 }
	  
	  public static void main(String[] args) {
		  Mail mail = new Mail();
		  String name = "emilie";
		  mail.envoyerMail(name);
		}
}
