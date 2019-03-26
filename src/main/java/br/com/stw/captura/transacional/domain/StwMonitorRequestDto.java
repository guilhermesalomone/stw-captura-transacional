package br.com.stw.captura.transacional.domain;

import javax.annotation.Generated;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
public class StwMonitorRequestDto extends StwMonitorDto {

	private String clientIP;
	private String clientHost;

	@Generated("SparkTools")
	private StwMonitorRequestDto(Builder builder) {
		this.hostName = builder.hostName;
		this.apiVersion = builder.apiVersion;
		this.sessionId = builder.sessionId;
		this.pathServico = builder.pathServico;
		this.method = builder.method;
		this.clientIP = builder.clientIP;
		this.clientHost = builder.clientHost;
	}
	
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getClientHost() {
		return clientHost;
	}
	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}
	/**
	 * Creates builder to build {@link StwMonitorRequestDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link StwMonitorRequestDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String hostName;
		private String apiVersion;
		private String sessionId;
		private String pathServico;
		private String method;
		private String clientIP;
		private String clientHost;

		private Builder() {
		}

		public Builder withHostName(String hostName) {
			this.hostName = hostName;
			return this;
		}

		public Builder withApiVersion(String apiVersion) {
			this.apiVersion = apiVersion;
			return this;
		}

		public Builder withSessionId(String sessionId) {
			this.sessionId = sessionId;
			return this;
		}

		public Builder withPathServico(String pathServico) {
			this.pathServico = pathServico;
			return this;
		}

		public Builder withMethod(String method) {
			this.method = method;
			return this;
		}

		public Builder withClientIP(String clientIP) {
			this.clientIP = clientIP;
			return this;
		}

		public Builder withClientHost(String clientHost) {
			this.clientHost = clientHost;
			return this;
		}

		public StwMonitorRequestDto build() {
			return new StwMonitorRequestDto(this);
		}
	}
		
}
