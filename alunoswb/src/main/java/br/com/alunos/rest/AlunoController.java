/**
 * 
 */
package br.com.alunos.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alunos.entity.Aluno;
import br.com.alunos.entity.Endereco;
import br.com.alunos.service.AlunoService;

/**
 * @author carreira
 *
 */

@Path(value="aluno")
public class AlunoController {

	@Inject
	AlunoService service;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<AlunoRest> getAlunoRests() {
		
		List<Aluno> lst = service.findAll();
		List<AlunoRest> lst2 = new ArrayList<AlunoRest>();
		lst.forEach(aluno->{
			AlunoRest a1 = new AlunoRest();
			a1.setId(aluno.getId());
			a1.setNome(aluno.getNome());
			a1.setCPF(aluno.getCpf());
			a1.setIdade(aluno.getIdade().toString());
			a1.setEndereco(new EnderecoRest());
			a1.getEndereco().setId(aluno.getEndereco().getId());
			a1.getEndereco().setNumero(aluno.getEndereco().getNumero().toString());
			a1.getEndereco().setLogradouro(aluno.getEndereco().getLogradouro());
			a1.getEndereco().setComplemento(aluno.getEndereco().getComplemento());
			a1.getEndereco().setBairro(aluno.getEndereco().getBairro());
			a1.getEndereco().setEstado(aluno.getEndereco().getEstado());
			a1.getEndereco().setCidade(aluno.getEndereco().getCidade());
			a1.getEndereco().setCep(aluno.getEndereco().getCep());
			lst2.add(a1);
		});
		return lst2;
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest getAlunoRest(@PathParam("id") String id) {
		
		Aluno aluno = service.findById(Integer.parseInt(id));
		AlunoRest a = new AlunoRest();
		a.setId(aluno.getId());
		a.setNome(aluno.getNome());
		a.setIdade(aluno.getIdade().toString());
		a.setCPF(aluno.getCpf());
		
		//a.setIdade("7");
		
		
		return a;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest postAlunoRest(AlunoRest a) {
		
		/*Aluno ent = new Aluno();
		ent.setCpf(a.getCPF());
		ent.setNome(a.getNome());
		ent.setIdade(Integer.parseInt(a.getIdade()));
		ent.setNome(a.getNome());
		ent.setEndereco(new Endereco());
		ent.getEndereco().setLogradouro(a.getEndereco().getLogradouro());
		ent.getEndereco().setBairro(a.getEndereco().getBairro());
		ent.getEndereco().setComplemento(a.getEndereco().getComplemento());
		ent.getEndereco().setCep(a.getEndereco().getCep());
		ent.getEndereco().setCidade(a.getEndereco().getEstado());
		ent.getEndereco().setEstado(a.getEndereco().getEstado());*/
		
		
		
		Aluno aluno = service.save(a, a.getEndereco());
		
		System.out.println(aluno);
		
		a.setId(aluno.getId());
		a.getEndereco().setId(aluno.getEndereco().getId());
		
		return a;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest putAlunoRest(AlunoRest a) {
		service.update(a);		
		return a;
	}
	
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest deleteAlunoRest(@PathParam("id") String id) {
		
		service.delete(Integer.parseInt(id));
		
		
		return a;
	}
	
}
