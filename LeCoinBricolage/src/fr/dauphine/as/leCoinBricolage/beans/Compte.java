package fr.dauphine.as.leCoinBricolage.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Compte implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String _COMPTE_COURANT =
			"_COMPTE_COURANT";
	public static final String _LISTE_OPERATIONS =
			"_LISTE_OPERATIONS";

	@Id
	private int id_client;
	private String cl_nom;
	private String cl_prenom;
	private Date cl_date_creation;
	private String cl_tel;
	private String cl_mail;
	private String cl_password;
	private String cl_etat;
	private String cl_voie;
	private String cl_code_postal;
	private String cl_ville;

	public Compte() {
		// TODO Auto-generated constructor stub
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getCl_nom() {
		return cl_nom;
	}

	public void setCl_nom(String cl_nom) {
		this.cl_nom = cl_nom;
	}

	public String getCl_prenom() {
		return cl_prenom;
	}

	public void setCl_prenom(String cl_prenom) {
		this.cl_prenom = cl_prenom;
	}

	public Date getCl_date_creation() {
		return cl_date_creation;
	}

	public void setCl_date_creation(Date cl_date_creation) {
		this.cl_date_creation = cl_date_creation;
	}

	public String getCl_tel() {
		return cl_tel;
	}

	public void setCl_tel(String cl_tel) {
		this.cl_tel = cl_tel;
	}

	public String getCl_mail() {
		return cl_mail;
	}

	public void setCl_mail(String cl_mail) {
		this.cl_mail = cl_mail;
	}

	public String getCl_password() {
		return cl_password;
	}

	public void setCl_password(String cl_password) {
		this.cl_password = cl_password;
	}

	public String getCl_etat() {
		return cl_etat;
	}

	public void setCl_etat(String cl_etat) {
		this.cl_etat = cl_etat;
	}

	public String getCl_voie() {
		return cl_voie;
	}

	public void setCl_voie(String cl_voie) {
		this.cl_voie = cl_voie;
	}

	public String getCl_code_postal() {
		return cl_code_postal;
	}

	public void setCl_code_postal(String cl_code_postal) {
		this.cl_code_postal = cl_code_postal;
	}

	public String getCl_ville() {
		return cl_ville;
	}

	public void setCl_ville(String cl_ville) {
		this.cl_ville = cl_ville;
	}
	
	
}