package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.Produto;

public class ProdutoDAO extends GenericDao<Produto> {
	
	private final String sqlInsert = "INSERT INTO PRODUTO (DESCRICAO) VALUES(?)";
	private final String sqlDelete = "DELETE FROM PRODUTO WHERE ID = ?";
	private final String sqlSelectById = "SELECT * FROM PRODUTO WHERE ID = ?";
	private final String sqlSelectByDescricao = "SELECT * FROM PRODUTO WHERE DESCRICAO = ?";
	private final String sqlUpdate = "UPDATE PRODUTO SET DESCRICAO = ? WHERE ID = ?";
	private final String sqlAll = "SELECT * FROM PRODUTO";

	@Override
	void insert(Produto object) throws SQLException {

		getConnection();
		
		try (PreparedStatement stmt = connection.prepareSQL(sqlInsert)) {
			stmt.setString(1, object.getDescricao());
						
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.desconectar();
		}
		
	}

	@Override
	void delete(int id) throws SQLException {
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
	List<Produto> selectByDescricao(String descricao) throws SQLException {
		getConnection();
		
		List<Produto> lista = new ArrayList<Produto>();
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlSelectByDescricao); ) {
			stmt.setString(1, descricao);
				
			rs = stmt.executeQuery();
			
			lista = loadProduto(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)  rs.close();
			connection.desconectar();
		}
		return lista;
	}
	
	private List<Produto> loadProduto(ResultSet rs) throws SQLException {
		List<Produto> lista = new ArrayList<Produto>();
		if (rs.next()) {
			Produto prod = new Produto();
			prod.setId(rs.getInt("id"));
			prod.setDescricao(rs.getString("descricao"));
			lista.add(prod);		
		}
		return lista;
	}

	@Override
	Produto findById(int id) throws SQLException {
		getConnection();

		Produto prod = null;
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlSelectById);) {
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			List<Produto> lista = loadProduto(rs);
			if (lista.size() > 0) {
				prod = lista.get(0);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			connection.desconectar();
		}
		return prod;
	}

	@Override
	void updateById(Produto object) throws SQLException {
		getConnection();
		
		try (PreparedStatement stmt = connection.prepareSQL(sqlUpdate); ) {
			stmt.setString(1, object.getDescricao());
			stmt.setInt(2, object.getId());
							
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.desconectar();
		}
		
	}

	public void cleanUp() throws Exception {
		System.out.println("delete everything");
		getConnection();
		//connection.createDB();
		try (PreparedStatement stmt = connection.prepareSQL("DELETE FROM PRODUTO"); ) {
				
			int i = stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			connection.desconectar();
		}
		
	}
	
	@Override
	public List<Produto> findAll() throws SQLException {
		getConnection();
		
		List<Produto> lista = new ArrayList<Produto>();
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlAll); ) {
				
			rs = stmt.executeQuery();
			
			lista = loadProduto(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)  rs.close();
			connection.desconectar();
		}
		return lista;
	}
	
}
