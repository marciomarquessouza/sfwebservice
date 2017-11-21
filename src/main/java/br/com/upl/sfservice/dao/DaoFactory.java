package br.com.upl.sfservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	

	public Connection getConnection() throws ClassNotFoundException {
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection("jdbc:jtds:sqlserver://172.27.20.51:1433/AS_SAPBI","consultor_tgn","consultor16@");
		} catch (SQLException e) {
			System.out.println("Database Connection error / erro no acesso ao banco de dados");
			throw new RuntimeException(e);
		}
	}
	/*
	
	public static void main (String args[]) {
		
		Connection connection = new DaoFactory().getConnection();
		System.out.println("Conectado");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}
