/**
 * 
 */
package br.com.alunos.rest;

import java.util.Arrays;
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
		AlunoRest a = new AlunoRest();
		a.setId(10);
		a.setNome("Paulo B Carreira");
		a.setIdade("38");
		a.setEndereço("Theodoro Schneider 241");
		
		AlunoRest a1 = new AlunoRest();
		a1.setId(10);
		a1.setNome("Paulo B Carreira");
		a1.setIdade("38");
		a1.setEndereço("Theodoro Schneider 241");
		
		return Arrays.asList(a,a1);
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest getAlunoRest(@PathParam("id") String id) {
		AlunoRest a = new AlunoRest();
		a.setId(10);
		a.setNome("Isabela S Carreira");
		a.setIdade("7");
		a.setEndereço("Theodoro Schneider 241");
		
		
		return a;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest postAlunoRest(AlunoRest a) {
		
		System.out.println(a);
		
		return a;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest putAlunoRest(AlunoRest a) {
				
		return a;
	}
	
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	public AlunoRest deleteAlunoRest(@PathParam("id") String id) {
		AlunoRest a = new AlunoRest();
		a.setId(10);
		a.setNome("Isabela S Carreira");
		a.setIdade("7");
		a.setEndereço("Theodoro Schneider 241");
		
		return a;
	}
	
}
