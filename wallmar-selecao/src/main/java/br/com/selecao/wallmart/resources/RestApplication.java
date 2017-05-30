package br.com.selecao.wallmart.resources;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

/**
 * Implementacao d classe Application, define o caminho raiz dos services.
 * 
 * @author Heber Santiago
 *
 */
@ApplicationPath("/api")
public class RestApplication extends Application {
}