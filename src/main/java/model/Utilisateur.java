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
import javax.persistence.Lob;
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
	
	@Lob
	@Column(name = "photo", columnDefinition = "mediumblob")
	private byte[] photo;
	
	//Relations 
	@OneToMany(mappedBy="enseignant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SessionCours> sessionCoursEnseigne = new HashSet<>();
	
	@ManyToMany(mappedBy = "etudiantsGroupe")
	private Set<Groupe> groupes = new HashSet<>();
	
	@ManyToMany
	@JoinTable (name = "retards",
		joinColumns = @JoinColumn(name = "code_utilisateur"), 
		inverseJoinColumns = @JoinColumn(name = "code_sessionCours"))
	private Set<SessionCours> sessionsCours = new HashSet<>();

	public Utilisateur() {
	}

	public Utilisateur(String mail, String mdp, String nom, String prenom, Statut statut, Boolean estAlternant,
			Long numEtudiant) {
		super();
		this.mail = mail;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.statut = statut;
		this.estAlternant = estAlternant;
		this.numEtudiant = numEtudiant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public void addSessionsCours(SessionCours session) {
		this.sessionCoursEnseigne.add(session);
	}
	
	public  void addGroupe(Groupe groupe) {
		this.groupes.add(groupe);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Boolean getEstAlternant() {
		return estAlternant;
	}

	public void setEstAlternant(Boolean estAlternant) {
		this.estAlternant = estAlternant;
	}

	public Long getNumEtudiant() {
		return numEtudiant;
	}

	public void setNumEtudiant(Long numEtudiant) {
		this.numEtudiant = numEtudiant;
	}

	public Set<SessionCours> getSessionCoursEnseigne() {
		return sessionCoursEnseigne;
	}

	public void setSessionCoursEnseigne(Set<SessionCours> sessionCoursEnseigne) {
		this.sessionCoursEnseigne = sessionCoursEnseigne;
	}

	public Set<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Set<SessionCours> getSessionsCours() {
		return sessionsCours;
	}

	public void setSessionsCours(Set<SessionCours> sessionsCours) {
		this.sessionsCours = sessionsCours;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
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

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", mail=" + mail + ", nom=" + nom + ", prenom=" + prenom + ", statut=" + statut
				+ "]";
	}
	
	
}