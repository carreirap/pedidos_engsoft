package br.com.alunos.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alunos.entity.Aluno;
import br.com.alunos.entity.Endereco;
import br.com.alunos.rest.AlunoRest;
import br.com.alunos.rest.EnderecoRest;

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

	@Override
	public Aluno save(AlunoRest a, EnderecoRest e) {
		//em.getTransaction().begin();;
		
		Aluno ent = new Aluno();
		ent.setCpf(a.getCPF());
		ent.setNome(a.getNome());
		ent.setIdade(Integer.parseInt(a.getIdade()));
		ent.setNome(a.getNome());
		Endereco end = new Endereco();
		end.setLogradouro(e.getLogradouro());
		end.setBairro(e.getBairro());
		end.setComplemento(e.getComplemento());
		end.setCep(e.getCep());
		end.setCidade(e.getEstado());
		end.setEstado(e.getEstado());
		end.setNumero(new BigDecimal(e.getNumero()));
		
		em.persist(end);
		ent.setIdendereco(end.getId());
		em.flush();
		ent.setEndereco(end);
		em.persist(ent);
		
		

		//em.getTransaction().commit();
		
		return ent;
	}
	
	

}
