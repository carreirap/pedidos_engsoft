package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.SQLException;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.MyException;

public abstract class GenericDao<T> {
	
	ConnectionDB connection;
	
	abstract void insert(T object) throws SQLException, MyException;
	
	abstract void delete(int id) throws SQLException;
	
	abstract List<T> selectByAtributo(String field, String value) throws SQLException;
	
	abstract T findById(int id) throws SQLException;;
	
	abstract List<T> findAll() throws SQLException;;
	
	abstract void updateById(T object) throws SQLException;
		
	void getConnection() {
		connection = ConnectionDB.getInstance();
		try {
			connection.conectarPool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
