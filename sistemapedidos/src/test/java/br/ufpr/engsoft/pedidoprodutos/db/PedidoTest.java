package br.ufpr.engsoft.pedidoprodutos.db;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.ItemPedido;
import br.ufpr.engsoft.pedidoprodutos.MyException;
import br.ufpr.engsoft.pedidoprodutos.Pedido;
import br.ufpr.engsoft.pedidoprodutos.Produto;

public class PedidoTest {
	
	@Test
	public void test0clean() {
		PedidoDAO dao = new PedidoDAO();
		try {
			dao.cleanUp();
			Assert.assertEquals(true, true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test1InsertPedido() {
		PedidoDAO dao = new PedidoDAO();
		
		ClienteDAO cliDAO = new ClienteDAO();
				
		
		try {
			List<Cliente> lstCli = cliDAO.findAll();
			Cliente cli = lstCli.get(0);
					
			Pedido ped = new Pedido();
			ped.setCliente(cli);
			ped.setData("12/06/1979");
			ped.setItens(new ArrayList<ItemPedido>());
			ProdutoDAO prodDao = new ProdutoDAO();

			List<Produto> listProd = prodDao.selectByAtributo("DESCRICAO","Manga Manteiga");
			
			ped.getItens().forEach(p-> { p.setQuantidade(10); p.setProduto(listProd.get(0)); });
			dao.insert(ped);
			
			List<Pedido> result = dao.findAll();
			Assert.assertEquals(ped.getId(), result.get(0).getId());
		} catch (SQLException | MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
}
