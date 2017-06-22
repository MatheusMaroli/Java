package venda.maluca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Persistencia {

	private String host;
	private String user;
	private String password;
	private Connection connection;
	private Statement statment;
	private String strConnection;
	private String driverjdbc;

	public static final String _host = "127.0.0.1";
	public static final String _port = "3306";
	public static final String _database = "programacao3";
	public static final String _user = "faculdade";
	public static final String _password = "faculdadedb";

	public Persistencia() {	

		setStrConexao("jdbc:mysql://" + _host + ":" + _port + "/" + _database);
		setHost(_host);
		setPassword(_password);
		setUser(_user);
		setDriverjdbc("com.mysql.jdbc.Driver");
	}

	public void conect() {

		try {
			Class.forName(getDriverjdbc());
			setConnection(DriverManager.getConnection(getStrConnection(), getUser(), getPassword()));
			setStatment(getConnection().createStatement());
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public void disconect() {

		try {
			getConnection().close();
		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public ResultSet query(String sql) {

		try {
			return getStatment().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean execSql(String sql) {

		try {
			return getStatment().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void configUser(String user, String password) {

		setUser(user);
		setPassword(password);
	}

	public void configLocal(String database) {
		setHost(database);
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatment() {
		return this.statment;
	}

	public void setStatment(Statement statment) {
		this.statment = statment;
	}

	public String getStrConnection() {
		return this.strConnection;
	}

	public void setStrConexao(String strConnection) {
		this.strConnection = strConnection;
	}

	public String getDriverjdbc() {
		return this.driverjdbc;
	}

	public void setDriverjdbc(String driverjdbc) {
		this.driverjdbc = driverjdbc;
	}
}