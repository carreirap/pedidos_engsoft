package br.ufpr.engsoft.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import br.ufpr.engsoft.pedidoprodutos.Produto;

@ViewScoped
@ManagedBean
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;
	
	private boolean showId;
	
	private List<Produto> lstProdutos;
	private Produto selectedProduto;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Produto> getLstProdutos() throws SQLException {
		if (lstProdutos == null ) {
			Produto pro = new Produto();
			lstProdutos = pro.findAllProduto();
		}
		return lstProdutos;
	}
	
	public String salvar() throws SQLException {
		Produto pro = new Produto();
		pro.setDescricao(this.getDescricao());
		pro.salvar();
		lstProdutos = null;
		return null;
		
	}
	
	public String excluir() throws SQLException {
		Produto pro = new Produto();
		pro.setId(this.getId());
		pro.deletar();
		System.out.println("Deletar");
		this.showId = false;
		this.lstProdutos = null;
		return null;
	}

	public void setLstProdutos(List<Produto> lstProdutos) {
		this.lstProdutos = lstProdutos;
	}
	
	public Produto getSelectedProduto() {
		return selectedProduto;
	}

	public void setSelectedProduto(Produto selectedProduto) {
		this.selectedProduto = selectedProduto;
	}

	public boolean isShowId() {
		return showId;
	}

	public void setShowId(boolean showId) {
		this.showId = showId;
	}

	public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage("Car Selected", ((Produto) event.getObject()).getDescricao());
	        this.setDescricao(((Produto) event.getObject()).getDescricao());
	        this.setId(((Produto)event.getObject()).getId());
	        System.out.println("VAlor Id : " + this.getId());
	        this.showId = true;
	        System.out.println("VAlor Id : " + this.getId() + " ShowId: " + this.showId);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    
	}
	
}
