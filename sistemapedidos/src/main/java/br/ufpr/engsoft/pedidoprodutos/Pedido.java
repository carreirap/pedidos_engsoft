package br.ufpr.engsoft.pedidoprodutos;

import java.sql.SQLException;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.db.PedidoDAO;

public class Pedido {
	
	private int id;
	
	private String data;
	
	private Cliente cliente;
	
	private List<ItemPedido> itens;
	
	public void savePedido() throws SQLException, MyException {
		PedidoDAO dao = new PedidoDAO();
		dao.insert(this);
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
