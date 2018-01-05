package br.com.alunos.service;

import java.util.List;

import br.com.alunos.entity.Aluno;
import br.com.alunos.exception.AlunoException;
import br.com.alunos.rest.AlunoRest;
import br.com.alunos.rest.EnderecoRest;

public interface AlunoService {

	List<Aluno> findAll();
	
	Aluno findById(final int id);
	
	Aluno save(final AlunoRest a, final EnderecoRest e);
	
	void update(final AlunoRest a);
	
	Aluno delete(final int id) throws AlunoException;
}
