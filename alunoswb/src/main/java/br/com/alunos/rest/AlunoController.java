/**
 * 
 */
package br.com.alunos.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.alunos.entity.Aluno;
import br.com.alunos.exception.AlunoException;
import br.com.alunos.service.AlunoService;

/**
 * @author carreira
 *
 */

@Path(value="/aluno")
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
	public AlunoRest getAlunoRest(@PathParam("id") Integer id) {
		
		Aluno aluno = service.findById(id);
		if (aluno != null) {
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
			
			//a.setIdade("7");
			
			
			return a1;
		} else {
			throw new NotFoundException();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest postAlunoRest(AlunoRest a) {
		
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
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest deleteAlunoRest(@PathParam("id") String id) {
		
		try {
			Aluno aluno = service.delete(Integer.parseInt(id));
		
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
			
			return a1;
		} catch(AlunoException e) {
			throw new NotFoundException();
		}
		
		
	}
	
	
	
	/*@GET
	@Path("/v")
	@Produces({MediaType.APPLICATION_JSON})
	public String getAlunoRests(@QueryParam("value") String value) {
		
		
		return value;
	}*/
	
}
