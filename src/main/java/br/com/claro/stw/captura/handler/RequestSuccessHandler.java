package br.com.claro.stw.captura.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.claro.stw.captura.handler.utils.ParametersUtil;
import br.com.claro.stw.captura.web.client.CapturaTransacionalClient;

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

		log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
	      + "]" + request.getRequestURI() + ParametersUtil.get(request));
		
		service.enviarRequest(request.getRequestURL().toString(), request.getSession().getId(), request.getMethod(),
				request.getRemoteAddr(), request.getRemoteHost(), ParametersUtil.get(request));
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		log.debug("Request Finished");
	}

	
}
