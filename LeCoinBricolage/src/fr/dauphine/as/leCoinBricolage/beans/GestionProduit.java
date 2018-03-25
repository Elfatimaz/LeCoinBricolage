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
public class GestionProduit implements Serializable {
	static final long serialVersionUID = 1L;
	private static DataSource dataSource = null;
	private final static String _SQL_SELECT_PRODUIT= "select * from coinbrico.produit";
	private final static String _SQL_INSERT_PRODUIT = "INSERT INTO coinbrico.produit (P_LIBELLE,P_TYPE,P_DESC_TECHNIQUE,P_DEFAUT,P_DATE_DER_MAJ,P_PRIX,P_AMENDE_DJ,P_CAUTION)"
			+ " VALUES(?,?,?,?,?,?,?,?)";
	private final static String _SQL_UPDATE_PRODUIT = "UPDATE coinbrico.produit SET P_LIBELLE=?,"
			+ "P_TYPE=?,P_DESC_TECHNIQUE=?,P_DEFAUT=?,P_PRIX=?,P_AMENDE=?,P_CAUTION=? where ID_PRODUIT=?";
	private final static String _SQL_DELETE_PRODUIT = "DELETE from coinbrico.PRODUIT where ID_PRODUIT=?";

	private List<Produit> produit;
	private Produit prod = null;

	public ArrayList<Produit> getProduit() {
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
				p.setId_produit(new Integer(resultSet.getString(10)).intValue());
				p.setP_libelle(resultSet.getString(45));
				p.setP_type(resultSet.getString(45));
				p.setP_desc(resultSet.getString(500));
				p.setP_defaut(resultSet.getString(500));
				p.setStringP_date_der_maj(resultSet.getString(10));
				p.setP_prix(Float.parseFloat(resultSet.getString(10)));
				p.setP_amende(Float.parseFloat(resultSet.getString(10)));
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
	}

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

	/*private String getP_type() {
		// TODO Auto-generated method stub
		return null;
	}*/


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

	}

	public String getResponse() {
		String retour = null;

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("BricoTestWeb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(getProduit());
		et.commit();

		setProduit(em.find(Produit.class, getId_produit()));

		retour = "insertion ok";
		return retour;
	}
	


	public Produit getProd() {
		return prod;
	}

	public void setProduit(Produit p) {
		this.prod = p;
	}

	public int getId_produit() {
		if (this.prod == null) {
			this.prod = new Produit();
		}
		return getProd().getId_produit();
	}

	public void setId_produit(int id_produit) {
		getProd().setId_produit(id_produit);
	}

	public String getP_libelle() {
		return getProd().getP_libelle();
	}
		
	public void setP_libelle(String libelle) {
		getProd().setP_libelle(libelle);
	}
	
	public String getP_type() {
		return getProd().getP_type();
	}
		
	public void setP_type(String type) {
		getProd().setP_type(type);
	}
	public String getP_desc() {
		return getProd().getP_desc();
	}
		
	public void setP_desc(String description) {
		getProd().setP_desc(description);
	}
	
	public String getP_defaut() {
		return getProd().getP_defaut();
	}
		
	public void setP_defaut(String defauts) {
		getProd().setP_defaut(defauts);
	}
	public Float getP_prix() {
		return getProd().getP_prix();
	}
		
	public void setP_prix(Float prix) {
		getProd().setP_prix(prix);
	}
	
	public Float getP_amende() {
		return getProd().getP_amende();
	}
		
	public void setP_amende(Float amende) {
		getProd().setP_amende(amende);
	}
	
	public Float getP_caution() {
		return getProd().getP_caution();
	}
		
	public void setP_caution(Float caution) {
		getProd().setP_caution(caution);
	}


	public Date getP_Date() {
		return getProd().getP_date_der_maj();
	}
	public String getStringP_Date() {
		return getProd().getStringP_date_der_maj();
	}

	public void setP_date_der_maj(Date date_der_maj) {
		getProd().setP_date_der_maj(date_der_maj);
	}

	public Integer getP_u_der_maj() {
		return getProd().getP_u_der_maj();
	}
		
	public void setP_u_der_maj(Integer utilId) {
		getProd().setP_u_der_maj(utilId);
	}

	
	public GestionProduit() {
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