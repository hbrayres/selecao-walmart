package br.com.selecao.wallmart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidade representa tabela produto.
 * 
 * @author Heber
 *
 */
@Entity
@Table(name = "PRODUTO")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = Produto.GET_ALL, query = "SELECT DISTINCT p FROM Produto p ORDER BY p.nome")
})
public class Produto implements Serializable {

	public static final String GET_ALL = "produto.getAll";
	
	/**
	 */
	private static final long serialVersionUID = -7479140585295895213L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(length = 45, name = "NOME", nullable = false)
	private String nome;

	@Column(length = 200, name = "DESCRICAO")
	private String descricao;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Produto)) {
			return false;
		}
		final Produto other = (Produto) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricoa) {
		this.descricao = descricoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer result = new StringBuffer();
		result.append(getClass().getSimpleName() + " ");
		if (nome != null && !nome.trim().isEmpty())
			result.append("nome: " + nome);
		if (descricao != null && !descricao.trim().isEmpty())
			result.append(", descricao: " + descricao);
		return result.toString();
	}
}