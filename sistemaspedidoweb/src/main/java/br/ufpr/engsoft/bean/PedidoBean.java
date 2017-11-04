package br.ufpr.engsoft.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.ItemPedido;
import br.ufpr.engsoft.pedidoprodutos.MyException;
import br.ufpr.engsoft.pedidoprodutos.Produto;

@ManagedBean
public class PedidoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4275214786598017090L;

	private String cpf;
	private String nomeCliente;
	private int codigoProduto;
	private String descricaoProduto;
	private int quantidade;
	private List<ItemPedido> lstItemPedidos;
	private List<Cliente> lstClientes;
	private Cliente selectedCliente;
	
	public Cliente getSelectedCliente() {
		return selectedCliente;
	}
	public void setSelectedCliente(Cliente selectedCliente) {
		this.selectedCliente = selectedCliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ItemPedido> getLstItemPedidos() {
		return lstItemPedidos;
	}
	
	public int getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public void setLstItemPedidos(List<ItemPedido> lstItemPedidos) {
		this.lstItemPedidos = lstItemPedidos;
	}
	
	public List<Cliente> getLstClientes() {
		if (lstClientes == null) {
			Cliente cli = new Cliente();
			lstClientes = cli.listarClientes();
		}
		return lstClientes;
	}
	public void setLstClientes(List<Cliente> lstClientes) {
		this.lstClientes = lstClientes;
	}
	public void incluir() {
		if (lstItemPedidos == null) {
			lstItemPedidos = new ArrayList<ItemPedido>();
		}
		ItemPedido item = new ItemPedido();
		item.setProduto(new Produto());
		item.getProduto().setId(this.codigoProduto);
		item.getProduto().setDescricao(this.getDescricaoProduto());
		item.setQuantidade(this.quantidade);
		
		lstItemPedidos.add(item);
	}
	
	public void buscarNomeCliente(AjaxBehaviorEvent event) throws SQLException, MyException {
		Cliente cli = new Cliente();
		cli.setCpf(this.cpf);
		cli.consultarCPF();
		this.nomeCliente = cli.getNome() + " " + cli.getSobreNome();
	}
	
	public void buscarNomeProduto(AjaxBehaviorEvent event) throws SQLException {
		Produto pro = new Produto();
		pro.setId(this.codigoProduto);
		pro.findProduto();
		this.descricaoProduto = pro.getDescricao();
		
	}
}
