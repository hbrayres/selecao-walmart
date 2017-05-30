package br.com.selecao.wallmart.vo;

import java.io.Serializable;

/**
 * Value Object para autenticacao de usuario.
 * 
 * @author Heber Santiago
 *
 */
public class Crendenciais implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -7708677111701959680L;
	
	private String login;
	private String password;

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

}
