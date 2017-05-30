package br.com.selecao.wallmart.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.resource.spi.SecurityException;
import javax.ws.rs.core.UriInfo;

import br.com.selecao.wallmart.entity.Usuario;
import br.com.selecao.wallmart.util.KeyGenerator;
import br.com.selecao.wallmart.vo.Crendenciais;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Servico de Seguranca, para logar com usuario e autentica-lo, 
 * gerando o token conforme requisitos.
 * 
 * @author Heber Santiago
 *
 */
@Stateless
public class SegurancaService {

	private static final Long UM_MINUTO = 1l;

	@PersistenceContext(unitName = "wallmart-pu")
	private EntityManager em;

	/**
	 * Autenticacao de usuario com username e password.
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void authenticate(final Crendenciais user) throws Exception {

		final TypedQuery<Usuario> query = em.createNamedQuery(Usuario.GET_BY_LOGIN, Usuario.class);
		query.setParameter("login", user.getLogin());

		try {
			query.getSingleResult();
		} catch (NoResultException nre) {
			throw new SecurityException("Usuario/Senha invalido");
		}

	}

	/**
	 * Criacao de token para validacao de login de usuario.
	 * 
	 * @param login
	 * @param uriInfo
	 * @param keyGenerator
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String createToken(final String login, UriInfo uriInfo, KeyGenerator keyGenerator)
			throws NoSuchAlgorithmException {
		final Key key = keyGenerator.generateKey();

		final String token = Jwts.builder().setSubject(login).setIssuer(uriInfo.getAbsolutePath().toString())
				.setIssuedAt(Calendar.getInstance().getTime())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(UM_MINUTO)))
				.signWith(SignatureAlgorithm.HS512, key).compact();

		return token;
	}

	private Date toDate(final LocalDateTime local) {
		final Date dt = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
		return dt;
	}
}
