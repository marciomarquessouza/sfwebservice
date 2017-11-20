package br.com.upl.sfservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
	public Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:jtds:sqlserver://UPLBRCPSVMDBTO:1433/CORPORE;instance=CORPORE","sa","P@ssw0rdUPLuniph05");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main (String args[]) {
		
		Connection connection = new DaoFactory().getConnection();
		System.out.println("Conectado");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
