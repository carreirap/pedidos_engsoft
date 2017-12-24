package br.com.alunos.service;

import java.util.List;

import br.com.alunos.entity.Aluno;
import br.com.alunos.rest.AlunoRest;
import br.com.alunos.rest.EnderecoRest;

public interface AlunoService {

	List<Aluno> findAll();
	
	Aluno findById(int id);
	
	Aluno save(AlunoRest a, EnderecoRest e);
}
