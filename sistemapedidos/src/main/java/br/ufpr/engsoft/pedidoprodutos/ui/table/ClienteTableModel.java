package br.ufpr.engsoft.pedidoprodutos.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufpr.engsoft.pedidoprodutos.Cliente;

public class ClienteTableModel extends AbstractTableModel {
	
    private List<Cliente> data;
	
	private final String[] columnNames = new String[]{"ID", "NOME", "SOBRENOME", "CPF"};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6151707643944765141L;
	
	public ClienteTableModel() {
		this.data = new ArrayList<Cliente>();
		Cliente cli = new Cliente();
		cli.setCpf("789451264646");
		cli.setNome("Paulo");
		cli.setSobreNome("Carreira");
		this.data.add(cli);
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
		return this.data.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0: return this.data.get(linha).getId(); 
		case 1: return this.data.get(linha).getNome(); 
		case 2: return this.data.get(linha).getNome(); 
		case 3: return this.data.get(linha).getCpf(); 
		}
		return this.data.get(linha);
	}
	
}
