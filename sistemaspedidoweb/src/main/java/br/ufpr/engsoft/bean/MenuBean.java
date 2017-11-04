package br.ufpr.engsoft.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class MenuBean implements Serializable {
	private static final long serialVersionUID = -1913140982550774826L;

	public String goProduto() {
		return "produto.jsf";
	}

	public String goCliente() {
		return "cliente.jsf";
	}
	
	public String goPedido() {
		return "cadPedido.jsf";
	}
}
