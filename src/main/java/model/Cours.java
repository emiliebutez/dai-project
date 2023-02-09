package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Cours {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	//Relations 
	@OneToMany(mappedBy="cours", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SessionCours> sessionCours = new HashSet<>();
	
	public Cours() {
		
	}
	
	public Cours(String nom) {
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void addSessionCours(SessionCours session) {
		this.sessionCours.add(session);
	}

	public Set<SessionCours> getSessionCours() {
		return sessionCours;
	}

	public void setSessionCours(Set<SessionCours> sessionCours) {
		this.sessionCours = sessionCours;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cours other = (Cours) obj;
		return Objects.equals(id, other.id) && Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Cours [id=" + id + ", nom=" + nom + "]";
	}

	
	
	
}
