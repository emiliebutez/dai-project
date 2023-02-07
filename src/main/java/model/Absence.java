package model;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "absences")
public class Absence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	Utilisateur utilisateur;
	
	@ManyToOne
	SessionCours sessionCours;

	@Column(name = "Justificatif")

	private String justificatif;
	@Column(name = "validation")
	private boolean validation = false ;
	
	
	public Absence() {
		
		
	}
	
	public Absence(Long id) {
		this.id=id;
	}
 
	public Absence(Utilisateur utilisateur, SessionCours sessionCours, String just,boolean validation) {
		this.utilisateur = utilisateur;
		this.sessionCours = sessionCours;
		this.justificatif=just; 
		this.validation = validation ;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public SessionCours getSessionCours() {
		return sessionCours;
	}

	public void setSessionCours(SessionCours sessionCours) {
		this.sessionCours = sessionCours;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Absence other = (Absence) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
