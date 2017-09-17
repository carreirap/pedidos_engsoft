package br.ufpr.engsoft.pedidoprodutos;

import java.sql.SQLException;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.db.ClienteDAO;
import br.ufpr.engsoft.pedidoprodutos.db.PedidoDAO;

public class Pedido {
	
	private int id;
	
	private String data;
	
	private Cliente cliente;
	
	private List<ItemPedido> itens;
	
	public void savePedido() throws SQLException, MyException {
		if (this.data == null || this.cliente.getCpf() == null || this.itens.size() == 0) {
			throw new MyException("O pedido não está completo");
		}
		getCliente().consultarCPF();
		PedidoDAO dao = new PedidoDAO();
		dao.insert(this);
	}
	
	public List<Pedido> consultaPedido() throws SQLException {
		PedidoDAO dao = new PedidoDAO();
		ClienteDAO cliDAO = new ClienteDAO();
		List<Cliente> lstCliente = cliDAO.selectByAtributo("CPF", this.getCliente().getCpf());
		this.setCliente(lstCliente.get(0));
		
		List<Pedido> lst = dao.selectByAtributo("ID_CLIENTE", String.valueOf(this.getCliente().getId()));
		lst.forEach(p -> p.setCliente(this.getCliente()));
		return lst;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
}
