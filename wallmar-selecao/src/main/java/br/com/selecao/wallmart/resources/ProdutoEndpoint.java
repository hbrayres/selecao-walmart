package br.com.selecao.wallmart.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.selecao.wallmart.annotations.RequiredToken;
import br.com.selecao.wallmart.entity.Produto;
import br.com.selecao.wallmart.service.ProdutoService;

/**
 * EndPoint de produto.
 */
@Stateless
@Path("/produtos")
public class ProdutoEndpoint {
	
	@EJB
	private ProdutoService produtoService;
	
	/**
	 * Persistir uma nova entidade na base de dados.
	 * 
	 * @param entity
	 * @return
	 */
	@POST
	@Consumes("application/json")
	public Response create(final Produto entity) {
		
		produtoService.create(entity);
		
		return Response.created(
				UriBuilder.fromResource(ProdutoEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	
	/**
	 * Remover a entidade conforme ID.
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		
		final Produto entity = produtoService.findById(id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		produtoService.deleteById(id);
		
		return Response.noContent().build();
	}

	/**
	 * Recuperar pelo ID da entidade.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {

		Produto entity;
		try {
			
			entity = produtoService.findById(id);
			
		} catch (Exception e) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	/**
	 * Listar todos os registros da entidade.
	 * Necessita que usuario esteja autenticado no sistema.
	 * 
	 * @return
	 */
	@GET
	@Produces("application/json")
	@RequiredToken
	public List<Produto> listAll() {
		final List<Produto> results = produtoService.listAll();
		return results;
	}

	/**
	 * Conforme o ID e entidade, salvar as alteracoes na base.
	 * 
	 * @param id
	 * @param entity
	 * @return
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, Produto entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (produtoService.findById(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		try {
			entity = produtoService.update(entity);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(entity).build();
		}

		return Response.noContent().build();
	}
}
