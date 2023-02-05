package model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "adresse_mail")
	private String mail;
	
	@Column(name = "mot_de_passe")
	private String mdp;
	
	@Column(name = "statut")
	private Statut statut;

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