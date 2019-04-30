package br.com.claro.stw.captura.domain;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
public abstract class StwMonitorDto {


	protected String hostName;
	protected String apiVersion;
	protected String sessionId;
	protected String pathServico;
	protected String method;
	protected String parametros;
	protected HttpStatus status;

	protected StwMonitorDto() {
		super();
	}


	
	public String getHostName() {
		return hostName;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public String getSessionId() {
		return sessionId;
	}
	public String getPathServico() {
		return pathServico;
	}
	public String getMethod() {
		return method;
	}
	public String getParametros() {
		return parametros;
	}
	public HttpStatus getStatus() {
		return status;
	}
	
}
