package br.ufpr.engsoft.pedidoprodutos.ui.table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufpr.engsoft.pedidoprodutos.Produto;
import br.ufpr.engsoft.pedidoprodutos.db.ProdutoDAO;

public class ProdutoTableModel extends AbstractTableModel {
	
    private List<Produto> data;
	
	private final String[] columnNames = new String[]{"ID", "DESCRIÇÃO"};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6151707643944765141L;
	
	public ProdutoTableModel() {
		this.data = new ArrayList<Produto>();
	}
	
	public void listarProdutos() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			this.data.clear();
			List<Produto> l = dao.findAll();
			l.forEach(s -> this.data.add(s));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}		
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
		case 1: return this.data.get(linha).getDescricao(); 
		}
		return this.data.get(linha);
	}
	
}
