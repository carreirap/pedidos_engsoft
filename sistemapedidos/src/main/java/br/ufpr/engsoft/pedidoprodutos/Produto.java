package br.ufpr.engsoft.pedidoprodutos;

import java.sql.SQLException;

import br.ufpr.engsoft.pedidoprodutos.db.ProdutoDAO;

public class Produto {
	
	private int id;
	
	private String descricao;
	
	public void salvar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		dao.insert(this);
	}
	
	public void alterar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		dao.updateById(this);
	}
	
	public void deletar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		dao.delete(id);
	}
	
	public void findProduto() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto prod = dao.findById(this.getId());
		this.setDescricao(prod.getDescricao());
		this.setId(prod.getId());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
