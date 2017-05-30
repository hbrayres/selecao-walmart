package br.com.selecao.wallmart.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import br.com.selecao.wallmart.service.SegurancaService;
import br.com.selecao.wallmart.util.KeyGenerator;
import br.com.selecao.wallmart.util.KeyGeneratorImpl;
import br.com.selecao.wallmart.vo.Crendenciais;

/**
 * Endpoint para autenticar usuario no sistema.
 * 
 * @author Heber Santiago
 *
 */
@Stateless
@Path("/login")
public class LoginEndpoint {

	@EJB
	private SegurancaService segService;

	@Context
	private UriInfo uriInfo;

	/**
	 * Gerador da chave para decriptografar o Token JWT
	 */
	private KeyGenerator keyGenerator = new KeyGeneratorImpl();

	/**
	 * Realizar a autenticacao do usuário conforme as credenciais enviadas.
	 * 
	 * @param user
	 * @return
	 */
	@POST
	public Response login(final Crendenciais user) {

		try {
			// autenticar usuario
			segService.authenticate(user);

			// gerar token JWT
			final String token = segService.createToken(user.getLogin(), uriInfo, keyGenerator);

			// retorna no header do Response o token gerado
			return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

		} catch (Exception e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

}
