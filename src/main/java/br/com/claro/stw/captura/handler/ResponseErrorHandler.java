package br.com.claro.stw.captura.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import br.com.claro.stw.captura.handler.exception.ErrorMessage;
import br.com.claro.stw.captura.web.client.CapturaTransacionalClient;

/**
 * 
 * @author Guilherme.Salomone
 *
 * @param <T>
 */
@ControllerAdvice
public class ResponseErrorHandler implements ResponseBodyAdvice<Object> {

	private final CapturaTransacionalClient service;
	
	public ResponseErrorHandler(CapturaTransacionalClient service) {
		super();
		this.service = service;
	}

	@Override
	public boolean supports(final MethodParameter returnType,
			final Class<? extends HttpMessageConverter<?>> converterType) {

		final Class<?> methodReturnType = returnType.getMethod().getReturnType();

		return methodReturnType == ErrorMessage.class;

	}

	@Override
	public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
			final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType,
			final ServerHttpRequest request, final ServerHttpResponse response) {

		final HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
		
		service.enviarResponse(request.getURI().toString(), request.getLocalAddress().getHostString(),
				request.getMethodValue(), HttpStatus.valueOf(servletResponse.getStatus()));

		return body;
	}

}
