package br.com.claro.stw.captura.domain;

import org.springframework.http.HttpStatus;
import javax.annotation.Generated;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
public class StwMonitorResponseDto extends StwMonitorDto {


	@Generated("SparkTools")
	private StwMonitorResponseDto(Builder builder) {
		this.hostName = builder.hostName;
		this.apiVersion = builder.apiVersion;
		this.sessionId = builder.sessionId;
		this.pathServico = builder.pathServico;
		this.method = builder.method;
		this.status = builder.status;
	}

	/**
	 * Creates builder to build {@link StwMonitorResponseDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link StwMonitorResponseDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String hostName;
		private String apiVersion;
		private String sessionId;
		private String pathServico;
		private String method;
		private HttpStatus status;

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

		public Builder withStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		public StwMonitorResponseDto build() {
			return new StwMonitorResponseDto(this);
		}
	}


	
}
