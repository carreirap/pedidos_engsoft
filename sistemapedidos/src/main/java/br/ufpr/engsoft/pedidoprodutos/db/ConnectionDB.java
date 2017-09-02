package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	
	private static ConnectionDB connection;
	
	private Connection conn;
	
	private ConnectionDB() {
		
	}
	
	
	public static ConnectionDB getInstance() {
		synchronized (ConnectionDB.class) {
			if (connection == null) {
				connection = new ConnectionDB();
			}
		}
		
		return connection;
	}
	
	public void conectar() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");

			conn = DriverManager.getConnection("jdbc:hsqldb:file:mydbpedidos", "sa", "");
			System.out.println(conn.isClosed());
			
		} catch (SQLException e) {
			throw new RuntimeException("Unable to load database", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public PreparedStatement prepareSQL(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
	
	
	
	public void createDB() {
		
		try {
			Statement stmt = conn.createStatement();
	        
	        int result = stmt.executeUpdate("CREATE TABLE CLIENTE ( " 
	            + "ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,"
	            + " CPF VARCHAR(11) NOT NULL, " 
	            + "NOME VARCHAR(30) NOT NULL, SOBRENOME VARCHAR(30) NOT NULL); "
	            + "CREATE TABLE PEDIDO( "
	            + "ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 200, INCREMENT BY 1) PRIMARY KEY, "
	            + "DATA VARCHAR(10) NOT NULL, "
	            + "ID_CLIENTE INT NOT NULL); "
	            + "CREATE TABLE PRODUTO ( "
	            + "ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 300, INCREMENT BY 1) PRIMARY KEY, "
	            + "DESCRICAO VARCHAR(10)); "
	            + "CREATE TABLE ITEM_DO_PEDIDO ( "
	            + "ID_PEDIDO INT NOT NULL,ID_PRODUTO INT NOT NULL, QTDADE INT NOT NULL,"
	            + "PRIMARY KEY (ID_PEDIDO, ID_PRODUTO));");
	        
	        String sql = "ALTER TABLE PEDIDO "
	        	+ "ADD FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE (ID);";
	        result = stmt.executeUpdate(sql);
	        
	         System.out.println(result);
			// Run script here
		} catch (SQLException e) {
			throw new RuntimeException("Unable to load database", e);
		}
		
	}
	
	

}
