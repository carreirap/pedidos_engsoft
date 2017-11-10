package br.ufpr.engsoft.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.ItemPedido;
import br.ufpr.engsoft.pedidoprodutos.MyException;
import br.ufpr.engsoft.pedidoprodutos.Pedido;
import br.ufpr.engsoft.pedidoprodutos.Produto;

@SessionScoped
@ManagedBean
public class PedidoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4275214786598017090L;

	private ProdutoBean produto;
	private int quantidade;
	private List<ItemPedido> lstItemPedidos;
	private ClienteBean cliente;
	
	public PedidoBean() {
		produto = new ProdutoBean();
		cliente = new ClienteBean();
		lstItemPedidos = new ArrayList<ItemPedido>();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ItemPedido> getLstItemPedidos() {
		return lstItemPedidos;
	}
	
	public ProdutoBean getProduto() {
		return produto;
	}

	public void setProduto(ProdutoBean produto) {
		this.produto = produto;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public void setLstItemPedidos(List<ItemPedido> lstItemPedidos) {
		this.lstItemPedidos = lstItemPedidos;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String incluir() {
		
		ItemPedido item = new ItemPedido();
		item.setProduto(new Produto());
		item.getProduto().setId(this.produto.getId());
		item.getProduto().setDescricao(this.produto.getDescricao());
		item.setQuantidade(this.quantidade);
		
		lstItemPedidos.add(item);
		this.produto.setId(0);
		this.produto.setDescricao("");
		this.quantidade = 0;
		return null;
	}
	
	public void buscarNomeCliente(AjaxBehaviorEvent event) throws SQLException, MyException {
		Cliente cli = new Cliente();
		cli.setCpf(this.cliente.getCpf());
		cli.consultarCPF();
		cli.setNome(cli.getNome() + " " + cli.getSobreNome());
	}
	
	public void buscarNomeProduto(AjaxBehaviorEvent event) throws SQLException {
		Produto pro = new Produto();
		pro.setId(this.produto.getId());
		pro.findProduto();
		this.produto.setDescricao(pro.getDescricao());
		
	}
	
//	public void onRowSelect(SelectEvent event) {
//        //FacesMessage msg = new FacesMessage("Car Selected", ((Produto) event.getObject()).getDescricao());
//		this.cliente.setNome(((Cliente) event.getObject()).getNome() + " " + ((Cliente) event.getObject()).getSobreNome());
//        this.cliente.setCpf(((Cliente) event.getObject()).getCpf());
//        //FacesContext.getCurrentInstance().addMessage(null, msg);
//	}
	
	public void salvar() {
		this.lstItemPedidos.forEach(System.out::println);
		Pedido ped = new Pedido();
		ped.setCliente(new Cliente());
		ped.getCliente().setCpf(this.cliente.getCpf());
		
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		ped.setData(format.format(d));
		ped.setItens(this.lstItemPedidos);
		try {

			ped.savePedido();
			
		} catch (Exception ex) {
			ex.printStackTrace();;
		}

	}
}
