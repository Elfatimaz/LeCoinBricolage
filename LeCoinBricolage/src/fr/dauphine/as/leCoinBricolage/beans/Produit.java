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
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String _COMPTE_COURANT =
			"_COMPTE_COURANT";
	public static final String _LISTE_OPERATIONS =
			"_LISTE_OPERATIONS";

	@Id
	private int id_produit;
	private String p_libelle;
	private String p_type;
	private String p_desc_technique;
	private String p_defaut;
	private Date p_date_der_maj;
	private Integer p_u_der_maj;
	private Float p_prix;
	private Float p_caution;
	private Float p_amende_dj;
	public Produit() {
		// TODO Auto-generated constructor stub
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getId_produit() {
		return id_produit;
	}

	public String getP_libelle() {
		return p_libelle;
	}

	public void setP_libelle(String p_libelle) {
		this.p_libelle = p_libelle;
	}

	public String getP_type() {
		return p_type;
	}
	public String getP_desc() {
		return p_desc_technique;
	}
	public String getP_defaut() {
		return p_defaut;
	}
	public Float getP_prix() {
		return p_prix;
	}
	public Float getP_amende() {
		return p_amende_dj;
	}

	public Float getP_caution() {
		return p_caution;
	}
	public void setP_desc(String p_desc) {
		this.p_desc_technique = p_desc;
	}

	
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}

	public void setP_defaut(String p_defaut) {
		this.p_defaut = p_defaut;
	}
	
	public Date getP_date_der_maj() {
		return p_date_der_maj;
	}
	public String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
	   /*you can also use DateFormat reference instead of SimpleDateFormat 
	    * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	    */
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
	public String getStringP_date_der_maj() {
		return convertStringToDate(p_date_der_maj);
	}
	
	public void setStringP_date_der_maj(String p_date) throws ParseException {
		DateFormat format= new SimpleDateFormat("dd/mm/yyyy",Locale.FRENCH);
		this.p_date_der_maj = format.parse(p_date);
	}
	public void setP_date_der_maj(Date p_date) {
		this.p_date_der_maj = p_date;
	}
	
	public Integer getP_u_der_maj() {
		return p_u_der_maj;
	}

	public void setP_u_der_maj(int p_u_der_maj) {
		this.p_u_der_maj = p_u_der_maj;
	}

	public void setP_prix(Float p_prix) {
		this.p_prix = p_prix;
	}

	public void setP_amende(Float p_amende) {
		this.p_amende_dj = p_amende;
	}

	public void setP_caution(Float p_caution) {
		this.p_caution = p_caution;
	}	
}