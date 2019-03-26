package br.com.stw.captura.transacional.handler;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import br.com.stw.captura.transacional.client.CapturaTransacionalClient;
import br.com.stw.captura.transacional.handler.exception.ErrorMessage;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@ControllerAdvice
public class ResponseSuccessHandler implements ResponseBodyAdvice<Object> {

	private static Logger log = LoggerFactory.getLogger(ResponseSuccessHandler.class);
	
	private final CapturaTransacionalClient server;
	
    public ResponseSuccessHandler(CapturaTransacionalClient server) {
		super();
		this.server = server;
	}

	@Override
    public boolean supports(final MethodParameter returnType, 
    		final Class<? extends HttpMessageConverter<?>> converterType) {
        final Class<?> methodReturnType = returnType.getMethod().getReturnType();

        return methodReturnType != ErrorMessage.class;
    }

	@Override
    public Object beforeBodyWrite(final Object body, 
    							  final MethodParameter returnType,
                                  final MediaType selectedContentType, 
                                  final Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  final ServerHttpRequest request, 
                                  final ServerHttpResponse response) {
		try {
			
	    	final HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
	    	server.enviarResponse(request.getURI().toString(), request.getLocalAddress().getHostString(), request.getMethodValue(), HttpStatus.valueOf(servletResponse.getStatus()));
	    	
		} catch (Exception e) {
			log.warn("Erro ao processar response para URL: {}", request.getURI().toString());
			log.debug("Erro ao processar response para URL:", e);
		}
		
        return body;
    }
}