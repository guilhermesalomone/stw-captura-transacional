package br.com.stw.captura.transacional.domain;

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
