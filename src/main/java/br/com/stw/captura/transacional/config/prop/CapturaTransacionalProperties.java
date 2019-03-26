package br.com.stw.captura.transacional.config.prop;

import javax.annotation.Generated;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
public class CapturaTransacionalProperties {

	private String apiVersion;
	private String name;
	private String urlServerRequestLogger;
	private String urlServerResponseLogger;
	private String urlServerManagementHealth;



	@Generated("SparkTools")
	private CapturaTransacionalProperties(Builder builder) {
		this.apiVersion = builder.apiVersion;
		this.name = builder.name;
		this.urlServerRequestLogger = builder.urlServerRequestLogger;
		this.urlServerResponseLogger = builder.urlServerResponseLogger;
		this.urlServerManagementHealth = builder.urlServerManagementHealth;
	}


	
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlServerRequestLogger() {
		return urlServerRequestLogger;
	}
	public void setUrlServerRequestLogger(String urlServerRequestLogger) {
		this.urlServerRequestLogger = urlServerRequestLogger;
	}
	public String getUrlServerResponseLogger() {
		return urlServerResponseLogger;
	}
	public void setUrlServerResponseLogger(String urlServerResponseLogger) {
		this.urlServerResponseLogger = urlServerResponseLogger;
	}
	public String getUrlServerManagementHealth() {
		return urlServerManagementHealth;
	}
	public void setUrlServerManagementHealth(String urlServerManagementHealth) {
		this.urlServerManagementHealth = urlServerManagementHealth;
	}
	/**
	 * Creates builder to build {@link CapturaTransacionalProperties}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link CapturaTransacionalProperties}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String apiVersion;
		private String name;
		private String urlServerRequestLogger;
		private String urlServerResponseLogger;
		private String urlServerManagementHealth;

		private Builder() {
		}

		public Builder withApiVersion(String apiVersion) {
			this.apiVersion = apiVersion;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withUrlServerRequestLogger(String urlServerRequestLogger) {
			this.urlServerRequestLogger = urlServerRequestLogger;
			return this;
		}

		public Builder withUrlServerResponseLogger(String urlServerResponseLogger) {
			this.urlServerResponseLogger = urlServerResponseLogger;
			return this;
		}

		public Builder withUrlServerManagementHealth(String urlServerManagementHealth) {
			this.urlServerManagementHealth = urlServerManagementHealth;
			return this;
		}

		public CapturaTransacionalProperties build() {
			return new CapturaTransacionalProperties(this);
		}
	}

	
}
