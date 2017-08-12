package br.ufpr.engsoft.pedidoprodutos.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufpr.engsoft.pedidoprodutos.Cliente;

public class ClienteTableModel extends AbstractTableModel {
	
    private List<Cliente> clientes;
	
	private String[] colunas = {"ID", "NOME", "SOBRENOME", "CPF"};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6151707643944765141L;
	
	public ClienteTableModel() {
		this.clientes = new ArrayList<Cliente>();
		Cliente cli = new Cliente();
		cli.setCpf("789451264646");
		cli.setNome("Paulo");
		cli.setSobreNome("Carreira");
		this.clientes.add(cli);
	}
	

	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.clientes.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0: return this.clientes.get(linha).getId(); 
		case 1: return this.clientes.get(linha).getNome(); 
		case 2: return this.clientes.get(linha).getNome(); 
		case 3: return this.clientes.get(linha).getCpf(); 
		}
		return this.clientes.get(linha);
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public String[] getColunas() {
		return colunas;
	}


	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	
}
