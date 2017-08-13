package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.Cliente;

public class ClienteDAO extends GenericDao<Cliente> {

	private final String sqlInsert = "INSERT INTO cliente values(?,?,?,?)";
	private final String sqlDelete = "DELETE FROM cliente where id = ?";
	private final String sqlSelectById = "SELECT * FROM cliente where id = ?";
	
	@Override
	public void insert(Cliente object) throws SQLException {
		
		getConnection();
		
		try (PreparedStatement stmt = connection.prepareSQL(sqlInsert)) {
			stmt.setString(2, object.getNome());
			stmt.setString(3, object.getSobreNome());
			stmt.setString(4, object.getCpf());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
				
	}

	@Override
	public void delete(int id) throws SQLException {
		
		try (PreparedStatement stmt = connection.prepareSQL(sqlDelete)) {
			stmt.setInt(1, id);
				
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public List<Cliente> selectById(int id) throws SQLException {
		List<Cliente> lista = new ArrayList<Cliente>();
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlSelectById); ) {
			stmt.setInt(1, id);
				
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				Cliente cli = new Cliente();
				cli.setId(rs.getInt("id"));
				cli.setNome(rs.getString("nome"));
				cli.setSobreNome(rs.getString("sobreNome"));
				cli.setCpf(rs.getString("cpf"));
				lista.add(cli);		
			}
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
			connection.desconectar();
		}
		return lista;
	}

	@Override
	void updateById(Cliente object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
