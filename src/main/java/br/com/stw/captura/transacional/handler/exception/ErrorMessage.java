package br.com.stw.captura.transacional.handler.exception;

import java.time.LocalDateTime;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.stw.captura.transacional.config.prop.CapturaTransacionalProperties;



public class ErrorMessage {

	private static final Logger log = LoggerFactory.getLogger(ErrorMessage.class);

	private final HttpStatus codigo;

	private final String api;
	
    private final String error;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime date;

	private ErrorMessage(final HttpStatus codigo, final String api, final String error) {
		this.codigo = codigo;
		this.api = api;
		this.error = error;
		this.date = LocalDateTime.now();
		log.error("ErrorMessage - Codigo " + codigo + " api " + api  + " data " + date);
	}

	public static ErrorMessage of(final HttpStatus codigo, final CapturaTransacionalProperties buildPropertiesRenner, final Throwable throwable) {

		final String stackTrace = ExceptionUtils.getStackTrace(throwable);
		final String api = buildPropertiesRenner.getName();

		return new ErrorMessage(codigo, api, stackTrace);
	}


	public HttpStatus getCodigo() {
		return codigo;
	}

	public String getApi() {
		return api;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		return "ErrorMessage{" + "codigo='" + codigo + '\'' + ", api='" + api + '\'' + ", date="
				+ date + '}';
	}


}
