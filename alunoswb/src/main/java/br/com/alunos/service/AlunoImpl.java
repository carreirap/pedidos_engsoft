package br.com.alunos.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import br.com.alunos.entity.Aluno;

@Stateless
public class AlunoImpl implements AlunoService {
	
	@PersistenceContext(unitName="alunoswb")
	private EntityManager em;
	//private EntityManagerFactory emf;

	@Override
	public List<Aluno> findAll() {
	
		//EntityManager em = emf.createEntityManager();
		List<Aluno> lst = em.createNamedQuery("Aluno.findAll").getResultList();
		return lst;
	}

	@Override
	public Aluno findById(int id) {
		Aluno aluno = em.find(Aluno.class, id);
		
		return aluno;
	}

}
