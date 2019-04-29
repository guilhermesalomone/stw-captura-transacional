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
	protected HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPathServico() {
		return pathServico;
	}
	public void setPathServico(String pathServico) {
		this.pathServico = pathServico;
	}
	
}
