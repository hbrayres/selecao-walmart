package br.com.selecao.wallmart.filters;

import java.io.IOException;
import java.security.Key;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.selecao.wallmart.annotations.RequiredToken;
import br.com.selecao.wallmart.util.KeyGenerator;
import br.com.selecao.wallmart.util.KeyGeneratorImpl;
import io.jsonwebtoken.Jwts;

/**
 * Implementacao da autorizacao pelo Token para cada servico que necessitem do Token.
 * 
 * @author Heber Santiago
 *
 */
@Provider
@RequiredToken
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private KeyGenerator keyGenerator = new KeyGeneratorImpl();
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		
		// recuperar o valor do cabecalho da requisicao
		final String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		try {
			
			// recuperar o token
			final String token = authHeader.substring("Bearer ".length());
			
			// gerar a mesma chave gerada na criacao do token
			final Key key = keyGenerator.generateKey();
			
			// usar a chave para descriptografar
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

}
