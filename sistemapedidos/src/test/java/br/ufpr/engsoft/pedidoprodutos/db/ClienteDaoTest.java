package br.ufpr.engsoft.pedidoprodutos.db;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufpr.engsoft.pedidoprodutos.Cliente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteDaoTest {


	@Test
	public void test0clean() {
		ClienteDAO dao = new ClienteDAO();
		try {
			dao.cleanUp();
			Assert.assertEquals(true, true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test1InsertCliente() {
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
	public void test2UpdateCliente() {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> cliente;
		try {
			cliente = dao.selectByDescricao("paulo");
			
			cliente.get(0).setSobreNome("Barbosa Carreira");
			dao.updateById(cliente.get(0));
			
			Cliente cli = dao.findById(cliente.get(0).getId());
			
			Assert.assertEquals(cli.getSobreNome(), "Barbosa Carreira");
		} catch (SQLException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test3RemoveCliente() {
		ClienteDAO dao = new ClienteDAO();
		
		try {
			List<Cliente> cliente = dao.selectByDescricao("paulo");
			dao.delete(cliente.get(0).getId());
			
			Cliente cli = dao.findById(cliente.get(0).getId());
			Assert.assertEquals(cli, null);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test4InsertCliente() {
		ClienteDAO dao = new ClienteDAO();
		
		Cliente cli = new Cliente();
		cli.setCpf("22222222222");
		cli.setNome("Rosalvo");
		cli.setSobreNome("Bruno");
		try {
			dao.insert(cli);
			
			List<Cliente> cliente = dao.selectByDescricao("Rosalvo");
			Assert.assertEquals("22222222222", cliente.get(0).getCpf());
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
