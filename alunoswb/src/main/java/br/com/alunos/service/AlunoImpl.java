package br.com.alunos.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import br.com.alunos.entity.Aluno;

@Stateless
public class AlunoImpl implements AlunoService {
	
	@PersistenceUnit(unitName="alunoswb")
	private EntityManagerFactory emf;

	@Override
	public List<Aluno> findAll() {
	
		EntityManager em = emf.createEntityManager();
		List<Aluno> lst = em.createNamedQuery("Aluno.findAll").getResultList();
		return lst;
	}

}
