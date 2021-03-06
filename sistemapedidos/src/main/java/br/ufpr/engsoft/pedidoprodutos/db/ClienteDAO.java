package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.Cliente;

public class ClienteDAO extends GenericDao<Cliente> {

	private final String sqlInsert = "INSERT INTO CLIENTE (CPF, NOME, SOBRENOME) VALUES(?,?,?)";
	private final String sqlDelete = "DELETE FROM CLIENTE WHERE ID = ?";
	private final String sqlSelectById = "SELECT * FROM CLIENTE WHERE ID = ?";
	private final String sqlSelectByAttribute = "SELECT * FROM CLIENTE WHERE %s = ?";
	private final String sqlUpdate = "UPDATE CLIENTE SET CPF=?, NOME=?, SOBRENOME=? WHERE ID = ?"; 
	private final String sqlAll = "SELECT * FROM CLIENTE";
	
		
	@Override
	public void insert(Cliente object) throws SQLException {
		
		getConnection();
		System.out.println(object);
		try (PreparedStatement stmt = connection.prepareSQL(sqlInsert)) {
			stmt.setString(1, object.getCpf());
			stmt.setString(2, object.getNome());
			stmt.setString(3, object.getSobreNome());
			
			System.out.println(stmt.executeUpdate());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			connection.desconectar();
		}
		
				
	}

	@Override
	public void delete(int id) throws SQLException {
	
		getConnection();
		
		try (PreparedStatement stmt = connection.prepareSQL(sqlDelete)) {
			stmt.setInt(1, id);
				
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.desconectar();
		}
	}

	@Override
	public	List<Cliente> selectByAtributo(String field, String value) throws SQLException {
		getConnection();
		
		List<Cliente> lista = new ArrayList<Cliente>();
		ResultSet rs = null;
		System.out.println(String.format(sqlSelectByAttribute, field));
		try (PreparedStatement stmt = connection.prepareSQL(String.format(sqlSelectByAttribute, field))) {
			stmt.setString(1, value);
				
			rs = stmt.executeQuery();
			
			lista = loadCliente(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)  rs.close();
			connection.desconectar();
		}
		return lista;
	}

	private List<Cliente> loadCliente(ResultSet rs) throws SQLException {
		List<Cliente> lista = new ArrayList<Cliente>();
		if (rs.next()) {
			do {
			Cliente cli = new Cliente();
			cli.setId(rs.getInt("id"));
			cli.setNome(rs.getString("nome"));
			cli.setSobreNome(rs.getString("sobreNome"));
			cli.setCpf(rs.getString("cpf"));
			lista.add(cli);
			} while (rs.next());
		}
		return lista;
	}

	@Override
	public void updateById(Cliente object) throws SQLException {
		getConnection();
		try (PreparedStatement stmt = connection.prepareSQL(sqlUpdate); ) {
			stmt.setString(1, object.getCpf());
			stmt.setString(2, object.getNome());
			stmt.setString(3, object.getSobreNome());
			stmt.setInt(4, object.getId());
				
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.desconectar();
		}
		
	}

	@Override
	Cliente findById(int id) throws SQLException {
		
		getConnection();
		
		Cliente cli = null;
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlSelectById); ) {
			stmt.setInt(1, id);
				
			rs = stmt.executeQuery();
			
			List<Cliente> lista = loadCliente(rs);
			if (lista.size() > 0) {
				cli = lista.get(0);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null)  rs.close(); 
			connection.desconectar();
		}
		return cli;
	}
	
	
	public void cleanUp() throws Exception {
		System.out.println("delete everything");
		getConnection();
		//connection.createDB();
		try (PreparedStatement stmt = connection.prepareSQL("DELETE FROM CLIENTE"); ) {
			int i = stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			connection.desconectar();
		}
		
	}

	@Override
	public List<Cliente> findAll() throws SQLException {
		getConnection();
		
		List<Cliente> lista = new ArrayList<Cliente>();
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlAll); ) {
				
			rs = stmt.executeQuery();
			
			lista = loadCliente(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)  rs.close();
			connection.desconectar();
		}
		return lista;
	}

}
