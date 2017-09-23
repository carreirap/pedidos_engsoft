package br.ufpr.engsoft.pedidoprodutos.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.ItemPedido;
import br.ufpr.engsoft.pedidoprodutos.MyException;
import br.ufpr.engsoft.pedidoprodutos.Pedido;
import br.ufpr.engsoft.pedidoprodutos.Produto;

public class PedidoDAO extends GenericDao<Pedido> {
	
	private final String sqlGetId = "CALL NEXT VALUE FOR SEQ_PEDIDO";
	private final String sqlInsert = "INSERT INTO PEDIDO (ID,DATA,ID_CLIENTE) VALUES(?,?,?)";
	private final String sqlInsertItem = "INSERT INTO ITEM_DO_PEDIDO (ID_PEDIDO, ID_PRODUTO,QTDADE) VALUES(?,?,?)";
	private final String sqlAll = 	"SELECT * FROM PEDIDO";
	private final String sqlById = "SELECT * FROM PEDIDO WHERE ID = ?";
	private final String sqlItemPedido = "SELECT * FROM ITEM_DO_PEDIDO WHERE ID_PEDIDO = ?";
	private final String sqlSelectByAtributo = "SELECT * FROM PEDIDO WHERE %s = ?";
	
	@Override
	public void insert(Pedido object) throws SQLException, MyException {
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
	public List<Pedido> selectByAtributo(String field, String value) throws SQLException {
		getConnection();
		
		List<Pedido> lista = new ArrayList<Pedido>();
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(String.format(sqlSelectByAtributo, field)); ) {
			stmt.setString(1, value);
				
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

	@Override
	public Pedido findById(int id) throws SQLException {
		getConnection();
		
		Pedido ped = null;
		ResultSet rs = null;
		try (PreparedStatement stmt = connection.prepareSQL(sqlById); ) {
			stmt.setInt(1, id);
				
			rs = stmt.executeQuery();
			
			List<Pedido> lista = loadPedido(rs);
			if (lista.size() > 0) {
				ped = lista.get(0);
			}
			if (ped != null) {
				findAllPedido(id, ped);
				
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null)  rs.close(); 
			connection.desconectar();
		}
		return ped;
	}

	private void findAllPedido(int id, Pedido ped) throws SQLException {
		ResultSet rsItem = null;
		try (PreparedStatement stmtItem = connection.prepareSQL(sqlItemPedido); ) {
			stmtItem.setInt(1, id);
			
			rsItem = stmtItem.executeQuery();
			if (rsItem.next()) {
				ped.setItens(new ArrayList<ItemPedido>());
				do {
					ItemPedido item = new ItemPedido();
					item.setProduto(new Produto());
					item.getProduto().setId(rsItem.getInt("ID_PRODUTO"));
					item.setQuantidade(rsItem.getInt("QTDADE"));
					ped.getItens().add(item);
				} while (rsItem.next());
			}
		}
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
				ped.setData(rs.getString("DATA"));
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
