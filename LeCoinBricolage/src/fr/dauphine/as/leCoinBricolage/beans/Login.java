package fr.dauphine.as.leCoinBricolage.beans;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import fr.dauphine.as.leCoinBricolage.util.SessionUtils;


@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	private static DataSource dataSource = null;
	private final static String _SQL_SELECT_CLIENT= "select cl_mail, cl_password from coinbrico.client where cl_mail = ? and cl_password = ?";
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getDataSource().getConnection();
			preparedStatement = connection
					.prepareStatement(_SQL_SELECT_CLIENT);
			preparedStatement.setString(1, this.getUser());
			preparedStatement.setString(2, this.getPwd());
			resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			System.out.print("hi");
			return "profile";
		} else {
			System.out.print("hola");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Email ou mot de passe incorrect",
							"vous n'avez pas de compte ? cliquez sur s'inscrire"));
			return "login";
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
		return "no";
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
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