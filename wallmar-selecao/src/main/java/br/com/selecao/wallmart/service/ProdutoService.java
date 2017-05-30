package br.com.selecao.wallmart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.selecao.wallmart.entity.Produto;

/**
 * Servico da entidade Produto
 * 
 * @author Heber
 *
 */
@Stateless
public class ProdutoService {

	@PersistenceContext(unitName = "wallmart-pu")
	private EntityManager em;

	/**
	 * Criar um novo produto
	 * 
	 * @param entity
	 */
	public void create(final Produto entity) {
		em.persist(entity);
	}

	/**
	 * Remocao de um produto conform ID.
	 * 
	 * @param id
	 */
	public void deleteById(final Long id) {
		Produto entity = em.find(Produto.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	/**
	 * Recuperar um produto pelo ID.
	 * 
	 * @param id
	 * @return
	 */
	public Produto findById(final Long id) {
		return em.find(Produto.class, id);
	}

	/**
	 * Atualizar uma entidade.
	 * 
	 * @param entity
	 * @return
	 */
	public Produto update(final Produto entity) {
		return em.merge(entity);
	}

	/**
	 * Listar entidades de Produto.
	 * 
	 * @return
	 */
	public List<Produto> listAll() {
		final TypedQuery<Produto> findAllQuery = em.createNamedQuery(Produto.GET_ALL, Produto.class);
		return findAllQuery.getResultList();
	}

}
