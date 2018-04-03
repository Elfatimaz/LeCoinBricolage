package fr.dauphine.as.leCoinBricolage.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Objet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id_objet;
	private int id_pl;
	private int id_produit;
	private String o_libelle;
	private String o_etat;
	private Date p_date_creation;
	private Date p_date_der_maj;
	private Integer p_u_der_maj;
	private Float o_prix;
	private Float o_caution;
	private Float o_amende_dj;
	public Objet() {
		// TODO Auto-generated constructor stub
	}
	public String getId_objet() {
		return id_objet;
	}
	public void setId_objet(String id_objet) {
		this.id_objet = id_objet;
	}
	public int getId_pl() {
		return id_pl;
	}
	public void setId_pl(int id_pl) {
		this.id_pl = id_pl;
	}
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public String getO_libelle() {
		return o_libelle;
	}
	public void setO_libelle(String o_libelle) {
		this.o_libelle = o_libelle;
	}
	public String getO_etat() {
		return o_etat;
	}
	public void setO_etat(String o_etat) {
		this.o_etat = o_etat;
	}
	public Date getP_date_creation() {
		return p_date_creation;
	}
	public void setP_date_creation(Date p_date_creation) {
		this.p_date_creation = p_date_creation;
	}
	public Date getP_date_der_maj() {
		return p_date_der_maj;
	}
	public void setP_date_der_maj(Date p_date_der_maj) {
		this.p_date_der_maj = p_date_der_maj;
	}
	public Integer getP_u_der_maj() {
		return p_u_der_maj;
	}
	public void setP_u_der_maj(Integer p_u_der_maj) {
		this.p_u_der_maj = p_u_der_maj;
	}
	public Float getO_prix() {
		return o_prix;
	}
	public void setO_prix(Float o_prix) {
		this.o_prix = o_prix;
	}
	public Float getO_caution() {
		return o_caution;
	}
	public void setO_caution(Float o_caution) {
		this.o_caution = o_caution;
	}
	public Float getO_amende_dj() {
		return o_amende_dj;
	}
	public void setO_amende_dj(Float o_amende_dj) {
		this.o_amende_dj = o_amende_dj;
	}
	
}