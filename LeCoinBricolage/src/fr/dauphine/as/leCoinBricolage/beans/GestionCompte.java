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
public class GestionCompte implements Serializable {
	static final long serialVersionUID = 1L;
	private static DataSource dataSource = null;
	private final static String _SQL_SELECT_CLIENT= "select * from coinbrico.client";
	private final static String _SQL_INSERT_COMPTE = "INSERT INTO coinbrico.client (CL_NOM,CL_PRENOM,CL_DATE_CREATION,CL_TEL,CL_MAIL,"
			+ "CL_PASSWORD,CL_VOIE,CL_CODE_POSTAL,CL_VILLE) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String _SQL_UPDATE_COMPTE = "UPDATE coinbrico.client SET CL_MAIL=?,"
			+ "CL_PASSWORD=?,CL_VOIE=?,CL_CODE_POSTAL=?,CL_VILLE=? where ID_CLIENT=?";
	private final static String _SQL_DELETE_COMPTE = "DELETE from coinbrico.client where ID_CLIENT=?";
	private final static String _SQL_SEARCH_COMPTE = "select CL_NOM from coinbrico.client where CL_MAIL=? and CL_PASSWORD=?";

	private List<Compte> client;
	private Compte compte = null;

	public ArrayList<Compte> getClient() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Compte c= null;
		ArrayList<Compte> resultat = null;
		try {
			resultat = new ArrayList<Compte>();
			connection = getDataSource().getConnection();
			preparedStatement = connection
					.prepareStatement(_SQL_SELECT_CLIENT);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				c = new Compte();
				c.setId_client(new Integer(resultSet.getString(1)).intValue());
				c.setCl_nom(resultSet.getString(2));
				c.setCl_prenom(resultSet.getString(3));
				c.setCl_tel(resultSet.getString(5));
				c.setCl_mail(resultSet.getString(6));
				c.setCl_password(resultSet.getString(7));
				c.setCl_voie(resultSet.getString(9));
				c.setCl_code_postal(resultSet.getString(10));
				c.setCl_ville(resultSet.getString(11));
				resultat.add(c);
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


	public String editCompte(Compte c) {
		this.compte = c;
		return "edit";
	}
    
	public String inscripClient() {
		return "inscription";
	}
	
	public String deleteCompte(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection.prepareStatement(_SQL_DELETE_COMPTE);
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
	
	public String loginClient(Compte c){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet= null;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection.prepareStatement(_SQL_SEARCH_COMPTE);
			preparedStatement.setString(1, this.getCl_mail());
			preparedStatement.setString(2, this.getCl_password());
			resultSet = preparedStatement.executeQuery();
			if(resultSet == null){
		
			}
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

	public String updateCompte(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection.prepareStatement(_SQL_UPDATE_COMPTE);
			preparedStatement.setString(1, this.getCl_mail());
			preparedStatement.setString(2, this.getCl_password());
			preparedStatement.setString(3, this.getCl_voie());
			preparedStatement.setString(4, this.getCl_code_postal());
			preparedStatement.setString(5, this.getCl_ville());
			preparedStatement.setInt(6, this.getId_client());
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

	public String addClient() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection
					.prepareStatement(_SQL_INSERT_COMPTE);
			
			preparedStatement.setString(1, this.getCl_nom());
			preparedStatement.setString(2, this.getCl_prenom());
			preparedStatement.setDate(3, getCurrentDate());
			preparedStatement.setString(4, this.getCl_tel());
			preparedStatement.setString(5, this.getCl_mail());
			preparedStatement.setString(6, this.getCl_password());
			preparedStatement.setString(7, this.getCl_voie());
			preparedStatement.setString(8, this.getCl_code_postal());
			preparedStatement.setString(9, this.getCl_ville());
			
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
		em.persist(getCompte());
		et.commit();

		setCompte(em.find(Compte.class, getId_client()));

		retour = "insertion ok";
		return retour;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public int getId_client() {
		if (this.compte == null) {
			this.compte = new Compte();
		}
		return getCompte().getId_client();
	}

	public void setId_client(int id_client) {
		getCompte().setId_client(id_client);
	}

	public String getCl_nom() {
		return getCompte().getCl_nom();
	}

	public void setCl_nom(String cl_nom) {
		getCompte().setCl_nom(cl_nom);
	}

	public String getCl_prenom() {
		return getCompte().getCl_prenom();
	}

	public void setCl_prenom(String cl_prenom) {
		getCompte().setCl_prenom(cl_prenom);
	}

	public String getCl_tel() {
		return getCompte().getCl_tel();
	}

	public void setCl_tel(String cl_tel) {
		getCompte().setCl_tel(cl_tel);
	}

	public Date getDate_creation() {
		return getCompte().getCl_date_creation();
	}

	public void setDatz_creation(Date cl_date_creation) {
		getCompte().setCl_date_creation(cl_date_creation);
	}

	public String getCl_mail() {
		return getCompte().getCl_mail();
	}

	public void setCl_mail(String cl_mail) {
		getCompte().setCl_mail(cl_mail);
	}

	public String getCl_password() {
		return getCompte().getCl_password();
	}

	public void setCl_password(String cl_password) {
		getCompte().setCl_password(cl_password);
	}

	public String getCl_etat() {
		return getCompte().getCl_etat();
	}

	public void setCl_etat(String cl_etat) {
		getCompte().setCl_etat(cl_etat);
	}
	
	public String getCl_voie() {
		return getCompte().getCl_voie();
	}

	public void setCl_voie(String cl_voie) {
		getCompte().setCl_voie(cl_voie);
	}	
	
	public String getCl_code_postal() {
		return getCompte().getCl_code_postal();
	}

	public void setCl_code_postal(String cl_code_postal) {
		getCompte().setCl_code_postal(cl_code_postal);
	}
	
	public String getCl_ville() {
		return getCompte().getCl_ville();
	}

	public void setCl_ville(String cl_ville) {
		getCompte().setCl_ville(cl_ville);
	}
	
	public GestionCompte() {
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