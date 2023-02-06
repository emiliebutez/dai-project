package model;

import java.time.OffsetDateTime;
import java.util.Date;

public class LigneAbsence {
	//Propriété
	OffsetDateTime dtdebut;
	OffsetDateTime dtfin;
	String nomCours;
	String nomGroupe;
	
	public LigneAbsence() {
		
	}
	/**
	 * Constructeur
	 * @param dtdebut
	 * @param dtfin
	 * @param nomCours
	 * @param nomGroupe
	 */
	public LigneAbsence(OffsetDateTime dtdebut, OffsetDateTime dtfin, String nomCours, String nomGroupe) {
		this.dtdebut = dtdebut;
		this.dtfin = dtfin;
		this.nomCours = nomCours;
		this.nomGroupe = nomGroupe;
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
	@Override
	public String toString() {
		return "LigneAbsence [dtdebut=" + dtdebut + ", dtfin=" + dtfin + ", nomCours=" + nomCours + ", nomGroupe="
				+ nomGroupe + "]";
	}
	
}
