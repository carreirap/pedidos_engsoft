package br.ufpr.engsoft.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import org.primefaces.event.SelectEvent;

import br.ufpr.engsoft.pedidoprodutos.Cliente;

@ManagedBean
public class ClienteBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2901164094676228580L;
	private int id;
	private String nome;
	private String sobreNome;
	private String cpf;
	private List<Cliente> lstClientes;
	private Cliente selectedCliente;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	public Cliente getSelectedCliente() {
		return selectedCliente;
	}
	public void setSelectedCliente(Cliente selectedCliente) {
		this.selectedCliente = selectedCliente;
	}
	public String salvar( ) {
		Cliente cli = new Cliente();
		cli.setId(this.id);
		cli.setNome(this.nome);
		cli.setCpf(this.cpf);
		cli.setSobreNome(this.sobreNome);
		
		try {
			if (this.id == 0)
				cli.salvarCliente();
			else
				cli.alterar();
			limparCampos();
			FacesMessage msg = new FacesMessage("Sucesso", "Operacao executada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = null;
			if (e.getMessage().contains("unique constraint")) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Cliente já cadastrado com o CPF informado!");
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", e.getMessage());
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String excluir() {
		Cliente cli = new Cliente();
		cli.setId(this.id);
		try {
			cli.deletarCliente();
			limparCampos();
		} catch (SQLException e) {
			FacesMessage msg = null;
			if (e.getMessage().contains("foreign key no action")) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Cliente não pode ser excluído, existe uma associação com um ou mais Pedidos");
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", e.getMessage());
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return null;
	}
	private void limparCampos() {
		lstClientes = null;
		this.id = 0;
		this.nome = "";
		this.sobreNome = "";
		this.cpf = "";
	}
	
	public void onRowSelect(SelectEvent event) {
        //FacesMessage msg = new FacesMessage("Car Selected", ((Produto) event.getObject()).getDescricao());
        this.setNome(((Cliente) event.getObject()).getNome());
        this.setSobreNome(((Cliente) event.getObject()).getSobreNome());
        this.setCpf(((Cliente) event.getObject()).getCpf());
        this.setId(((Cliente)event.getObject()).getId());
        System.out.println("VAlor Id : " + this.getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    
	}
	
}
