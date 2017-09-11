package br.ufpr.engsoft.pedidoprodutos.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufpr.engsoft.pedidoprodutos.ItemPedido;

public class ItemPedidoModel extends AbstractTableModel {
	
	private List<ItemPedido> data;
	
	private final String[] columnNames = new String[]{"CODIGO", "PRODUTO", "QUANTIDADE"};
	
	public ItemPedidoModel() {
		this.data = new ArrayList<ItemPedido>();
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
	
	public void setData(ItemPedido item) {
		this.data.add(item);
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
		case 0: return this.data.get(linha).getProduto().getId(); 
		case 1: return this.data.get(linha).getProduto().getDescricao();
		case 2: return this.data.get(linha).getQuantidade();
		}
		return this.data.get(linha);
	}

}
