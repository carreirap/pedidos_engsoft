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

import org.primefaces.event.SelectEvent;

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
	private Pedido selectedPedido;
	private List<ItemPedido> lstItemPedidos;
	private List<Pedido> lstPedidos;
	private ClienteBean cliente;
	private boolean showItens;
	
	public PedidoBean() {
		produto = new ProdutoBean();
		cliente = new ClienteBean();
		lstItemPedidos = new ArrayList<ItemPedido>();
	}
	
	public boolean isShowItens() {
		return showItens;
	}

	public void setShowItens(boolean showItens) {
		this.showItens = showItens;
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
	
	public Pedido getSelectedPedido() {
		return selectedPedido;
	}

	public void setSelectedPedido(Pedido selectedPedido) {
		this.selectedPedido = selectedPedido;
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
	
	public void buscarPedidos() {
		Pedido ped = new Pedido();
		Cliente cli = new Cliente();
		cli.setCpf(this.getCliente().getCpf());
		ped.setCliente(cli);
		try {
			this.lstPedidos = ped.consultaPedido();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public List<Pedido> getLstPedidos() {
		return lstPedidos;
	}

	public void setLstPedidos(List<Pedido> lstPedidos) {
		this.lstPedidos = lstPedidos;
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
	
	public void onRowSelect(SelectEvent event) {
		try {
			this.selectedPedido.buscarPedidoCompleto();
			this.lstItemPedidos = this.selectedPedido.getItens();
			this.showItens = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
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
