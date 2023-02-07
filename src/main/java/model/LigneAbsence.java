package model;

import java.io.File;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.swing.text.Document;

import model.Absence;

public class LigneAbsence {
	//Propriété
	Long absid;
	OffsetDateTime dtdebut;
	OffsetDateTime dtfin;
	String nomCours;
	String nomGroupe;
	String nom;
	String prenom;
	String file;
	
	public LigneAbsence() {
		
	}
	
	/**
	 * Constructeur
	 * @param dtdebut
	 * @param dtfin
	 * @param nomCours
	 * @param nomGroupe
	 */
	public LigneAbsence(String file, String nom,String prenom,Long absid, OffsetDateTime dtdebut, OffsetDateTime dtfin, String nomCours, String nomGroupe) {
		this.file = file;
		this.nom = nom;
		this.prenom=prenom;
		this.absid = absid;	
		this.dtdebut = dtdebut;
		this.dtfin = dtfin;
		this.nomCours = nomCours;
		this.nomGroupe = nomGroupe;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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

	public OffsetDateTime getDtdebut() {
		return dtdebut;
	}
	public void setDtdebut(OffsetDateTime dtdebut) {
		this.dtdebut = dtdebut;
	}
	public OffsetDateTime getDtfin() {
		return dtfin;
	}
	public void setDtfin(OffsetDateTime dtfin) {
		this.dtfin = dtfin;
	}
	public String getNomCours() {
		return nomCours;
	}
	public void setNomCours(String nomCours) {
		this.nomCours = nomCours;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	
	public Long getAbsid() {
		return absid;
	}

	public void setAbsid(Long absid) {
		this.absid = absid;
	}

	@Override
	public String toString() {
		return "LigneAbsence [dtdebut=" + dtdebut + ", dtfin=" + dtfin + ", nomCours=" + nomCours + ", nomGroupe="
				+ nomGroupe + ", absid=" + absid + "]";
	}

	
	
}