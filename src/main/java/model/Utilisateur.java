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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "adresse_mail")
	private String mail;
	
	@Column(name = "mot_de_passe")
	private String mdp;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "statut")
	private Statut statut;
	
	@Column(name= "alternant")
	private Boolean estAlternant;
	
	@Column(name = "numero_etudiant")
	private Long numEtudiant;
	
	//Relations 
	@OneToMany(mappedBy="enseignant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SessionCours> sessionCoursEnseigne = new HashSet<>();
	
	// Relations.
	@ManyToMany(mappedBy = "etudiantsGroupe")
	private Set<Groupe> groupes = new HashSet<>();
	
	@ManyToMany
	@JoinTable (name = "retards",
		joinColumns = @JoinColumn(name = "code_utilisateur"), 
		inverseJoinColumns = @JoinColumn(name = "code_sessionCours"))
	private Set<Utilisateur> sessionsCours = new HashSet<>();

	public Utilisateur() {
	}

	public Utilisateur(String mail, String mdp, Statut statut) {
		this.mail = mail;
		this.mdp = mdp;
		this.statut = statut;
	}

	public String getMail() {
		return mail;
	}

	public String getMdp() {
		return mdp;
	}
	
	public Statut getStatut() {
		return this.statut;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mail, mdp, statut);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(mail, other.mail)
				&& Objects.equals(mdp, other.mdp)
				&& Objects.equals(statut, other.statut);
	}
}