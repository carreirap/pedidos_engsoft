package br.com.alunos.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alunos.entity.Aluno;
import br.com.alunos.entity.Endereco;

@Stateless
public class AlunoRepository {
	
	@PersistenceContext(unitName="alunoswb")
	private EntityManager em;
	
	public void save(Aluno aluno) {
		em.persist(aluno);
		em.flush();
	}
	
	public void update(Aluno aluno) {
		em.merge(aluno);
		em.flush();
	}
	
	public Aluno findById(int id) {
		return em.find(Aluno.class, id);
	}
	
	public void delete(Aluno aluno) {
		em.remove(aluno);
	}
	

}
