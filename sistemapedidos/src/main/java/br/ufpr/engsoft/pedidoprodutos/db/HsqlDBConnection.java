package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HsqlDBConnection {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
			//Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Class.forName("org.hsqldb.jdbcDriver");
			
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:mydbpedidos", "sa", "");
			
			System.out.println(connection.isClosed());
			
		 	Statement stmt = connection.createStatement();
	         
	        int result = stmt.executeUpdate("CREATE TABLE Cliente ( " 
	            + "id INT NOT NULL, cpf VARCHAR(11) NOT NULL, " 
	            + "nome VARCHAR(30) NOT NULL, sobrenome VARCHAR(30) NOT NULL, " 
	            + "PRIMARY KEY (id)); "
	            + "CREATE TABLE Pedido( "
	            + "id INT NOT NULL, data VARCHAR(10) NOT NULL, "
	            + "id_cliente INT NOT NULL, PRIMARY KEY (id)); "
	            + "CREATE TABLE Produto ( "
	            + "id INT NOT NULL, descricao VARCHAR(10) NOT NULL, "
	            + "PRIMARY KEY (id)); "
	            + "CREATE TABLE item_do_pedido ( "
	            + "id_pedido INT NOT NULL,id_produto INT NOT NULL, qtdade INT NOT NULL,"
	            + "PRIMARY KEY (id_pedido, id_produto));");
	        
	        String sql = "ALTER TABLE Pedido "
	        	+ "ADD FOREIGN KEY (id_cliente) REFERENCES Cliente (id);";
	        result = stmt.executeUpdate(sql);
	        
	        sql = "INSERT INTO Cliente values (1,'11111111111','Paulo','B. Carreira')";
	        //stmt = connection.createStatement();
	        
	        result = stmt.executeUpdate(sql);
	         System.out.println(result);
			// Run script here
		} catch (SQLException e) {
			throw new RuntimeException("Unable to load database", e);
		}
	}

}
