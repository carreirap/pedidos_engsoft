package br.ufpr.engsoft.pedidoprodutos.db;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufpr.engsoft.pedidoprodutos.Produto;
import junit.framework.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoDaoTest {
	
	@Test
	public void test0clean() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.cleanUp();
			Assert.assertEquals(true, true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test1InsertProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		
		Produto pro = new Produto();
		pro.setDescricao("Manga Tomi");
		try {
			dao.insert(pro);
			
			List<Produto> Produto = dao.selectByDescricao("Manga Tomi");
			Assert.assertEquals("Manga Tomi", Produto.get(0).getDescricao());
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test2UpdateProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		
		List<Produto> produto;
		try {
			produto = dao.selectByDescricao("Manga Tomi");
			
			produto.get(0).setDescricao("Melão");
			dao.updateById(produto.get(0));
			
			Produto cli = dao.findById(produto.get(0).getId());
			
			Assert.assertEquals(cli.getDescricao(), "Melão");
		} catch (SQLException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test3RemoveProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		
		try {
			List<Produto> produto = dao.selectByDescricao("Melão");
			dao.delete(produto.get(0).getId());
			
			Produto pro = dao.findById(produto.get(0).getId());
			Assert.assertEquals(pro, null);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
