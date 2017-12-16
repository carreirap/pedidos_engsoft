package br.com.alunos.rest;

import javax.xml.bind.annotation.XmlRootElement;
// pg_ctl -D alunoswb -l logfile start
//pg_ctl -D ^"C^:^\pgsql^\data^" -l logfile start
@XmlRootElement
public class AlunoRest {
	
	private Integer Id;
	private String CPF;
	private String nome;
	private String idade;
	private String endere�o;

	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
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
	public String getEndere�o() {
		return endere�o;
	}
	public void setEndere�o(String endere�o) {
		this.endere�o = endere�o;
	}
	@Override
	public String toString() {
		return "Aluno [Id=" + Id + ", CPF=" + CPF + ", nome=" + nome + ", idade=" + idade + ", endere�o=" + endere�o
				+ "]";
	}
	
	

}
