package br.ufpr.engsoft.pedidoprodutos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.engsoft.pedidoprodutos.db.ClienteDAO;

public class Cliente {
	
	private int id;
	
	private String nome;
	
	private String sobreNome;
	
	private String cpf;
	
	public void salvarCliente() throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		dao.insert(this);
	}
	
	public void deletarCliente() throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		dao.delete(id);
	}
	
	public void alterar() throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		dao.updateById(this);
	}
	
	public void consultarCPF() throws SQLException, MyException {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> lst = dao.selectByAtributo("CPF", this.cpf);
		if (lst.size() == 0) {
			throw new MyException("CPF informado não cadastrado");
		}
		this.id = lst.get(0).getId();
		this.nome = lst.get(0).getNome();
		this.sobreNome = lst.get(0).getSobreNome();
	}
	
	public List<Cliente> listarClientes() {
		ClienteDAO dao = new ClienteDAO();
		try {
			List<Cliente> l = dao.findAll();
			return l;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}	
		return new ArrayList<Cliente>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", sobreNome=" + sobreNome + ", cpf=" + cpf + "]";
	}
	
	

}
