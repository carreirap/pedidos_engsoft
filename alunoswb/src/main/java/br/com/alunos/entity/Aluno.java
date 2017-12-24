package br.com.alunos.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aluno database table.
 * 
 */
@Entity
@Table(schema="public")
@NamedQuery(name="Aluno.findAll", query="SELECT a FROM Aluno a")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator="seqaluno")
	@SequenceGenerator(name="pk_sequence",sequenceName="seqaluno", allocationSize=1, schema="public")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    private int id;

	private String cpf;

	private Integer idendereco;
	
	private Integer idade;

	private String nome;

	//bi-directional many-to-one association to Endereco
	@ManyToOne
	@JoinColumn(name="idendereco", referencedColumnName="id", insertable=false, updatable=false)
	private Endereco endereco;

	public Aluno() {
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdendereco() {
		return this.idendereco;
	}

	public void setIdendereco(Integer idendereco) {
		this.idendereco = idendereco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
	

}