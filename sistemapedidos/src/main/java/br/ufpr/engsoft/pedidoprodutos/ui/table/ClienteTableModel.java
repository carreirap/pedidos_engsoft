package br.ufpr.engsoft.pedidoprodutos.ui.table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.db.ClienteDAO;

public class ClienteTableModel extends AbstractTableModel {
	
    private List<Cliente> data;
	
	private final String[] columnNames = new String[]{"ID", "NOME", "SOBRENOME", "CPF"};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6151707643944765141L;
	
	public ClienteTableModel() {
		this.data = new ArrayList<Cliente>();
	}
	
	public void listarClientes() {
		ClienteDAO dao = new ClienteDAO();
		try {
			this.data.clear();
			List<Cliente> l = dao.findAll();
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
		case 1: return this.data.get(linha).getNome(); 
		case 2: return this.data.get(linha).getSobreNome(); 
		case 3: return this.data.get(linha).getCpf(); 
		}
		return this.data.get(linha);
	}
	
}
