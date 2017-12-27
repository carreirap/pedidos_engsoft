package br.com.alunos.rest;

import javax.xml.bind.annotation.XmlRootElement;
// pg_ctl -D alunoswb -l logfile start
//./pg_ctl.exe -D "C:\pgsql\data" -l logfile start
@XmlRootElement
public class AlunoRest {
	
	private Integer id;
	private String CPF;
	private String nome;
	private String idade;
	private EnderecoRest endereco;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public EnderecoRest getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoRest endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return "AlunoRest [id=" + id + ", CPF=" + CPF + ", nome=" + nome + ", idade=" + idade + ", endereço=" + endereco
				+ "]";
	}
	
}
