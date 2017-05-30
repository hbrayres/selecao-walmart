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

/**
 * Classe representa a tabela USUARIO
 * 
 * @author Heber
 *
 */
@Entity
@Table(name = "USUARIO")
@NamedQueries({
	@NamedQuery(name = Usuario.GET_ALL, query = "SELECT u FROM Usuario u order by u.login"),
	@NamedQuery(name = Usuario.GET_BY_LOGIN, query = "SELECT u FROM Usuario u WHERE u.login = :login")
})
public class Usuario implements Serializable {

	public static final String GET_BY_LOGIN = "usuario.getByLogin";
	public static final String GET_ALL = "usuario.getAll";
	
	/**
	 */
	private static final long serialVersionUID = 7137721477200999737L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(length = 50, name = "LOGIN", nullable = false)
	private String login;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (login != null && !login.trim().isEmpty())
			result += "login: " + login;
		if (password != null && !password.trim().isEmpty())
			result += ", password: " + password;
		return result;
	}
}