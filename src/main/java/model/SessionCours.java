package model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class SessionCours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "debut")
	private OffsetDateTime debut;
	
	@Column(name = "fin")
	private OffsetDateTime fin;
	
	@ManyToOne
	@JoinColumn(name ="id_enseignant")
	private Utilisateur enseignant;
	
	// Relations 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="id_cours")
	private Cours cours;
	
	@ManyToMany(mappedBy = "sessionsCours", fetch = FetchType.EAGER)
	private Set<Utilisateur> etudiants = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="id_groupe")
	private Groupe groupe;
	
	@Column(name = "appel_termine")
	private boolean appelTermine;
	
	public SessionCours() {
	}
	
	public SessionCours(OffsetDateTime debut, OffsetDateTime fin, Utilisateur enseignant, Cours cours, Groupe groupe) {
		this.debut = debut;
		this.fin = fin;
		this.enseignant = enseignant;
		this.cours = cours;
		this.groupe = groupe;
	}
	
	public SessionCours(OffsetDateTime debut, OffsetDateTime fin, Utilisateur enseignant, Cours cours) {
		this.debut = debut;
		this.fin = fin;
		this.enseignant = enseignant;
		this.cours = cours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getDebut() {
		return debut;
	}

	public void setDebut(OffsetDateTime debut) {
		this.debut = debut;
	}

	public OffsetDateTime getFin() {
		return fin;
	}

	public void setFin(OffsetDateTime fin) {
		this.fin = fin;
	}

	public Utilisateur getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Utilisateur enseignant) {
		this.enseignant = enseignant;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Set<Utilisateur> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(Set<Utilisateur> etudiants) {
		this.etudiants = etudiants;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public boolean isAppelTermine() {
		return appelTermine;
	}

	public void setAppelTermine(boolean appelTermine) {
		this.appelTermine = appelTermine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(debut, fin, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionCours other = (SessionCours) obj;
		return Objects.equals(debut, other.debut) && Objects.equals(fin, other.fin) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SessionCours [debut=" + debut + ", fin=" + fin + ", enseignant=" + enseignant + ", cours=" + cours
				+ ", etudiants=" + etudiants + ", groupe=" + groupe + "]";
	}
	
	
}
