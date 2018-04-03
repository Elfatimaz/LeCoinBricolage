package fr.dauphine.as.leCoinBricolage.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@ManagedBean
@SessionScoped
public class GestionObjet implements Serializable {
	static final long serialVersionUID = 1L;
	private static DataSource dataSource = null;
	private final static String _SQL_SELECT_PRODUIT= "select * from coinbrico.produit";
	private final static String _SQL_INSERT_PRODUIT = "INSERT INTO coinbrico.produit (P_LIBELLE,P_TYPE,P_DESC_TECHNIQUE,P_DEFAUT,P_DATE_DER_MAJ,P_PRIX,P_AMENDE_DJ,P_CAUTION)"
			+ " VALUES(?,?,?,?,?,?,?,?)";
	private final static String _SQL_UPDATE_PRODUIT = "UPDATE coinbrico.produit SET P_LIBELLE=?,"
			+ "P_TYPE=?,P_DESC_TECHNIQUE=?,P_DEFAUT=?,P_PRIX=?,P_AMENDE=?,P_CAUTION=? where ID_PRODUIT=?";
	private final static String _SQL_DELETE_PRODUIT = "DELETE from coinbrico.PRODUIT where ID_PRODUIT=?";
	
	private final static String _SQL_SELECT_OBJETS ="select * from coinbrico.objet where id_produit=?";

	private List<Objet> objet;
	private Objet o = null;
	static Produit p;

	/*public ArrayList<Produit> getProduit() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Produit p= null;
		ArrayList<Produit> resultat = null;
		try {
			resultat = new ArrayList<Produit>();
			connection = getDataSource().getConnection();
			preparedStatement = connection
					.prepareStatement(_SQL_SELECT_PRODUIT);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				p = new Produit();
				p.setId_produit(new Integer(resultSet.getString(1)).intValue());
				p.setP_libelle(resultSet.getString(2));
				p.setP_type(resultSet.getString(3));
				p.setP_desc(resultSet.getString(4));
				p.setP_defaut(resultSet.getString(5));
				p.setP_prix(Float.parseFloat(resultSet.getString(8)));
				p.setP_amende(Float.parseFloat(resultSet.getString(9)));
				p.setP_caution(Float.parseFloat(resultSet.getString(10)));
				resultat.add(p);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage().toString());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e2) {
				System.err.println(e2.getMessage().toString());
			}
		}
		return resultat;
	}


	public String editProduit(Produit p) {
		this.prod = p;
		return "edit";
	}*/
	
	public String listeObjets() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Objet o = null;
		ArrayList<Objet> resultat = null;
		try {
			resultat = new ArrayList<Objet>();
			connection = getDataSource().getConnection();
			preparedStatement = connection
					.prepareStatement(_SQL_SELECT_OBJETS);
			preparedStatement.setInt(1, p.getId_produit());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				o = new Objet();
				o.setId_objet(resultSet.getString(1));
				o.setO_libelle(resultSet.getString(4));
				o.setId_pl(new Integer(resultSet.getString(2)).intValue());
				o.setO_etat(resultSet.getString(9));
				o.setO_prix(Float.parseFloat(resultSet.getString(5)));
				o.setO_amende_dj(Float.parseFloat(resultSet.getString(6)));
				o.setO_caution(Float.parseFloat(resultSet.getString(7)));
				resultat.add(o);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage().toString());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e2) {
				System.err.println(e2.getMessage().toString());
			}
		}
		return "objProd";
	}

	/*
	public String deleteProduit(Produit p){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection.prepareStatement(_SQL_DELETE_PRODUIT);
			preparedStatement.setInt(1, p.getId_produit());
			resultSet = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			System.err.println(e.getMessage().toString());
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e2) {
				System.err.println(e2.getMessage().toString());
			}
		}
		return "list";


	}

	public String updateProduit(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection.prepareStatement(_SQL_UPDATE_PRODUIT);
			preparedStatement.setString(1, this.getP_libelle());
			preparedStatement.setString(2, this.getP_type());
			preparedStatement.setString(3, this.getP_desc());
			preparedStatement.setString(4, this.getP_defaut());
			preparedStatement.setFloat(5, this.getP_prix());
			preparedStatement.setFloat(6, this.getP_amende());
			preparedStatement.setFloat(7, this.getP_caution());
			preparedStatement.setString(10, this.getStringP_Date());
			preparedStatement.setInt(9, this.getP_u_der_maj());
			resultSet = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			System.err.println(e.getMessage().toString());
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e2) {
				System.err.println(e2.getMessage().toString());
			}
		}
		return "list";
	}




	public String addProduit() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection
					.prepareStatement(_SQL_INSERT_PRODUIT);
			
			preparedStatement.setString(1, this.getP_libelle());
			preparedStatement.setString(2, this.getP_type());
			preparedStatement.setString(3, this.getP_desc());
			preparedStatement.setString(4, this.getP_defaut());
			preparedStatement.setDate(5, getCurrentDate());
			//preparedStatement.setInt(6, this.getP_u_der_maj());
			preparedStatement.setFloat(6, this.getP_prix());
			preparedStatement.setFloat(7, this.getP_amende());
			preparedStatement.setFloat(8, this.getP_caution());
			
			resultSet = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			System.err.println(e.getMessage().toString());
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e2) {
				System.err.println(e2.getMessage().toString());
			}
		}
		return "list";

	}*/


	public Objet getO() {
		return o;
	}

	public void setObjet(Objet o) {
		this.o = o;
	}

	public String getId_objet() {
		if (this.o == null) {
			this.o = new Objet();
		}
		return getO().getId_objet();
	}

	public void setId_objet(String id) {
		getO().setId_objet(id);
	}

	public String getO_libelle() {
		return getO().getO_libelle();
	}
		
	public void setO_libelle(String libelle) {
		getO().setO_libelle(libelle);
	}
	
	public String getO_etat() {
		return getO().getO_etat();
	}
		
	public void setO_etat(String etat) {
		getO().setO_libelle(etat);
	}
	
	
	public int getO_pl() {
		return getO().getId_pl();
	}
		
	public void setO_pl(int id_pl) {
		getO().setId_pl(id_pl);
	}
	public Float getO_prix() {
		return getO().getO_prix();
	}
		
	public void setO_prix(float prix) {
		getO().setO_prix(prix);
	}
	
	public Float getO_amende() {
		return getO().getO_amende_dj();
	}
		
	public void setO_amende(float amende) {
		getO().setO_amende_dj(amende);
	}
	
	public Float getO_caution() {
		return getO().getO_caution();
	}
		
	public void setO_caution(float caution) {
		getO().setO_caution(caution);
	}
	
	public static Object getp() {
		return p;
	}

	public static void setP(Produit p ) {
		GestionObjet.p = p;
	}

	public GestionObjet() {
	}
	
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	
	public static DataSource getDataSource() {		
		if (null == dataSource) {
			try {			
				InitialContext initialContext = new InitialContext();	
				dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/coinBricoDS");		
			} catch (Exception e) {	
				System.err.println(e.getMessage());		
			}
		}
		return dataSource;
	}


}