package br.com.stw.captura.transacional.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.stw.captura.transacional.web.client.CapturaTransacionalClient;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Component
public class RequestSuccessHandler extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(RequestSuccessHandler.class);

	private final CapturaTransacionalClient service;


	public RequestSuccessHandler(CapturaTransacionalClient service) {
		super();
		this.service = service;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		service.enviarRequest(request.getRequestURL().toString(), request.getSession().getId(), request.getMethod(),
				request.getRemoteAddr(), request.getRemoteHost());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		log.debug("Request Finished");
	}

}
