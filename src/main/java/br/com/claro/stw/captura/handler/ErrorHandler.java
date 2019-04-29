package br.com.claro.stw.captura.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.claro.stw.captura.config.prop.CapturaTransacionalProperties;
import br.com.claro.stw.captura.handler.exception.ErrorMessage;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@ControllerAdvice
public class ErrorHandler {


    private final CapturaTransacionalProperties buildProperties;

    @Autowired
    public ErrorHandler(final CapturaTransacionalProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorMessage businessError(final RuntimeException businessException) {

    	return ErrorMessage.of(HttpStatus.INTERNAL_SERVER_ERROR, buildProperties, businessException);
    }
}