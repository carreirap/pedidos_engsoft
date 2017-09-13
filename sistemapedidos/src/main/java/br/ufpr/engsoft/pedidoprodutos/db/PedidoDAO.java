package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.MyException;
import br.ufpr.engsoft.pedidoprodutos.Pedido;

public class PedidoDAO extends GenericDao<Pedido> {
	
	private final String sqlGetId = "CALL NEXT VALUE FOR SEQ_PEDIDO";
	private final String sqlInsert = "INSERT INTO PEDIDO (ID,DATA,ID_CLIENTE) VALUES(?,?,?)";
	private final String sqlInsertItem = "INSERT INTO ITEM_DO_PEDIDO (ID_PEDIDO, ID_PRODUTO,QUANTIDADE) VALUES(?,?)";
	private final String sqlAll = 	"SELECT * FROM PEDIDO";
	
	@Override
	void insert(Pedido object) throws SQLException, MyException {
		getConnection();
		
		int aux = 0;
		this.connection.setAutoCommit(false);;
		try (PreparedStatement stmt = connection.prepareSQL(sqlGetId); 
				ResultSet rs = stmt.executeQuery();){
			if (rs.next()) {
				aux = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			
		}
		object.setId(aux);
		if (object.getId() == 0) {
			throw new MyException("Não foi possivel pegar o id do novo Pedido");
		}
		try (PreparedStatement stmt = connection.prepareSQL(sqlInsert)) {
			stmt.setInt(1, object.getId());
			stmt.setString(2, object.getData());
			stmt.setInt(3, object.getCliente().getId());
						
			stmt.executeUpdate();
			
			object.getItens().forEach(
				p -> 
				{ try (PreparedStatement stmtI = connection.prepareSQL(sqlInsertItem)) {
					stmtI.setInt(1, object.getId());
					stmtI.setInt(2, p.getProduto().getId());
					stmtI.setInt(3, p.getQuantidade());
					
					stmtI.executeUpdate();
					} catch (SQLException ex) {
						throw new RuntimeException(ex);
					}
				}
			);
			
			this.connection.commit();
		} catch (SQLException e) {
			throw e;
		} finally {
			System.out.println("Desconcetar");
			connection.desconectar();
		}
		
	}

	@Override
	void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	List<Pedido> selectByDescricao(String descricao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Pedido findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	List<Pedido> findAll() throws SQLException {
		getConnection();
		
		List<Pedido> lista = new ArrayList<Pedido>();
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlAll); ) {
				
			rs = stmt.executeQuery();
			
			lista = loadPedido(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)  rs.close();
			connection.desconectar();
		}
		return lista;
	}
	
	private List<Pedido> loadPedido(ResultSet rs) throws SQLException {
		List<Pedido> lista = new ArrayList<Pedido>();
		if (rs.next()) {
			do {
				Pedido ped = new Pedido();
				ped.setId(rs.getInt("ID"));
				ped.setCliente(new Cliente());
				ped.getCliente().setId(rs.getInt("ID_CLIENTE"));
				System.out.println("ID: " + ped.getId() + "Cliente:" + ped.getCliente().getId());
				lista.add(ped);
			} while(rs.next());
		}
		return lista;
	}

	@Override
	void updateById(Pedido object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void cleanUp() throws Exception {
		System.out.println("Cleaning table pedido");
		getConnection();
		// connection.createDB();
		try (PreparedStatement stmt = connection.prepareSQL("DELETE FROM PEDIDO");) {

			int i = stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			connection.desconectar();
		}
	}
	
}
