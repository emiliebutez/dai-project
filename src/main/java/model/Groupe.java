package model;

import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "groupes")
public class Groupe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	// Relations 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="id_promo")
	private Promo promo;
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable (name = "composer",
		joinColumns = @JoinColumn(name = "code_groupe"), 
		inverseJoinColumns = @JoinColumn(name = "code_utilisateurs"))
	private Set<Utilisateur> etudiantsGroupe = new HashSet<>();
	
	@OneToMany(mappedBy="groupe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SessionCours> sessionsCours = new HashSet<>();
	
	public Groupe() {
		
	}
	
	public Groupe(String nom) {
		this.nom = nom;
	}

	public Groupe(String nom, Promo promo) {
		this.nom = nom;
		this.promo = promo;
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

	public Promo getPromo() {
		return promo;
	}
	
	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	
	public void addEtudiant(Utilisateur etudiant) {
		this.etudiantsGroupe.add(etudiant);
	}
	
	public void addSession (SessionCours session) {
		this.sessionsCours.add(session);
	}

	public Set<Utilisateur> getEtudiantsGroupe() {
		return etudiantsGroupe;
	}

	public void setEtudiantsGroupe(Set<Utilisateur> etudiantsGroupe) {
		this.etudiantsGroupe = etudiantsGroupe;
	}

	public Set<SessionCours> getSessionsCours() {
		return sessionsCours;
	}

	public void setSessionsCours(Set<SessionCours> sessionsCours) {
		this.sessionsCours = sessionsCours;
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
		Groupe other = (Groupe) obj;
		return Objects.equals(id, other.id) && Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Groupe [id=" + id + ", nom=" + nom + "]";
	}
	
	
}
