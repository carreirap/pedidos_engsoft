package br.ufpr.engsoft.pedidoprodutos.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufpr.engsoft.pedidoprodutos.ItemPedido;
import br.ufpr.engsoft.pedidoprodutos.Pedido;

public class PedidoModel extends AbstractTableModel {
	
	private List<Pedido> data;
	
	private final String[] columnNames = new String[]{"CODIGO", "CPF", "DATA"};
	
	public PedidoModel() {
		this.data = new ArrayList<Pedido>();
	}
	
//	public void listarProdutos() {
//		ProdutoDAO dao = new ProdutoDAO();
//		try {
//			this.data.clear();
//			List<Produto> l = dao.findAll();
//			l.forEach(s -> this.data.add(s));
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		}		
//	}
	
	public void setData(Pedido pedido) {
		this.data.add(pedido);
	}
	
	public void setDatas(List<Pedido> pedidos) {
		this.data = pedidos;
	}
	
	@Override
	public String getColumnName(int column) {
	   return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		try {
			return this.data.size();
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0: return this.data.get(linha).getId(); 
		case 1: return this.data.get(linha).getCliente().getCpf();
		case 2: return this.data.get(linha).getData();
		}
		return this.data.get(linha);
	}

	public List<Pedido> getData() {
		return data;
	}
}
