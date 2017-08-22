package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.SQLException;
import java.util.List;

public abstract class GenericDao<T> {
	
	ConnectionDB connection;
	
	abstract void insert(T object) throws SQLException;
	
	abstract void delete(int id) throws SQLException;
	
	abstract List<T> selectByDescricao(String descricao) throws SQLException;
	
	abstract T findById(int id) throws SQLException;;
	
	abstract void updateById(T object) throws SQLException;
		
	void getConnection() {
		connection = ConnectionDB.getInstance();
		connection.conectar();
	}
	
}
