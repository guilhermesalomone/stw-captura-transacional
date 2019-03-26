package br.com.stw.captura.transacional.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.stw.captura.transacional.client.CapturaTransacionalClient;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Component
public class RequestSuccessHandler extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(RequestSuccessHandler.class);

	private final CapturaTransacionalClient server;

	@Autowired
	public RequestSuccessHandler(CapturaTransacionalClient server) {
		super();
		this.server = server;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		addToModelUserDetails(request.getSession());
		server.enviarRequest(request.getRequestURL().toString(), request.getSession().getId(), request.getMethod(),
				request.getRemoteAddr(), request.getRemoteHost());

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		log.debug("Request Finished");
	}

	private void addToModelUserDetails(HttpSession session) {
		log.debug("=============== addToModelUserDetails =========================");

		log.debug(" session : " + session.getId());
		log.debug("=============== addToModelUserDetails =========================");
	}
}
