package br.com.alunos.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alunos.entity.Aluno;
import br.com.alunos.entity.Endereco;
import br.com.alunos.repository.AlunoRepository;
import br.com.alunos.repository.EnderecoRepository;
import br.com.alunos.rest.AlunoRest;
import br.com.alunos.rest.EnderecoRest;

@Stateless
public class AlunoImpl implements AlunoService {
	
	@PersistenceContext(unitName="alunoswb")
	private EntityManager em;
	//private EntityManagerFactory emf;
	
	
	@Inject
	private EnderecoRepository enderecoRepository;
	@Inject
	private AlunoRepository alunoRepository;

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
		
		Aluno ent = convertAlunoToEntity(a);

		Endereco end = convertEnderecoToEntity(e);
		
		enderecoRepository.save(end);
		
		ent.setIdendereco(end.getId());
		ent.setEndereco(end);
		alunoRepository.save(ent);
		//em.persist(ent);

		//em.getTransaction().commit();
		
		return ent;
	}

	private Endereco convertEnderecoToEntity(EnderecoRest e) {
		Endereco end = new Endereco();
		end.setLogradouro(e.getLogradouro());
		end.setBairro(e.getBairro());
		end.setComplemento(e.getComplemento());
		end.setCep(e.getCep());
		end.setCidade(e.getCidade());
		end.setEstado(e.getEstado());
		end.setNumero(new BigDecimal(e.getNumero()));
		if (e.getId() != null && e.getId() != 0) 
			end.setId(e.getId());
		return end;
	}

	private Aluno convertAlunoToEntity(AlunoRest a) {
		Aluno ent = new Aluno();
		ent.setCpf(a.getCPF());
		ent.setNome(a.getNome());
		ent.setIdade(Integer.parseInt(a.getIdade()));
		ent.setNome(a.getNome());
		if (a.getId() != null && a.getId() != 0) 
			ent.setId(a.getId());
		
		return ent;
	}

	@Override
	public void update(AlunoRest a) {
		
		Aluno aluno = convertAlunoToEntity(a);
		
		Endereco endereco = convertEnderecoToEntity(a.getEndereco());
		
		enderecoRepository.update(endereco);
		aluno.setEndereco(endereco);
		aluno.setIdendereco(endereco.getId());
		alunoRepository.update(aluno);
		
		
	}

	@Override
	public void delete(int id) {
		Aluno aluno = alunoRepository.findById(id);
		alunoRepository.delete(aluno);
		aluno.getEndereco();
		enderecoRepository.delete(aluno.getEndereco());
		
	}
	
	
	

}
