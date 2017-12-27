package br.com.alunos.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alunos.entity.Endereco;

@Stateless
public class EnderecoRepository {
	
	@PersistenceContext(unitName="alunoswb")
	private EntityManager em;
	
	public void save(Endereco endereco) {
		em.persist(endereco);
		em.flush();
	}
	
	public void update(Endereco endereco) {
		em.merge(endereco);
		em.flush();
	}
	
	public Endereco findById(int id) {
		return em.find(Endereco.class, id);
	}
	
	public void delete (Endereco endereco) {
		em.remove(endereco);
	}

}
