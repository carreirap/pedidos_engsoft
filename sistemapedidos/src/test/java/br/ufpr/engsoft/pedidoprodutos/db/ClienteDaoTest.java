package br.ufpr.engsoft.pedidoprodutos.db;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.testng.Assert;

import br.ufpr.engsoft.pedidoprodutos.Cliente;

public class ClienteDaoTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testInsertCliente() {
		ClienteDAO dao = new ClienteDAO();
		
		Cliente cli = new Cliente();
		cli.setCpf("11111111111");
		cli.setNome("paulo");
		cli.setSobreNome("dddddddd");
		try {
			dao.insert(cli);
			
			List<Cliente> cliente = dao.selectByDescricao("paulo");
			Assert.assertEquals("11111111111", cliente.get(0).getCpf());
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testRemoveCliente() {
		ClienteDAO dao = new ClienteDAO();
		
		try {
			dao.delete(100);
			
			Cliente cli = dao.findById(100);
			Assert.assertEquals(cli.getCpf(), null);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
