package br.com.stw.captura.transacional.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.stw.captura.transacional.config.prop.CapturaTransacionalProperties;
import br.com.stw.captura.transacional.domain.StwMonitorRequestDto;
import br.com.stw.captura.transacional.domain.StwMonitorResponseDto;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Service
public class CapturaTransacionalClient {

	private static Logger log = LoggerFactory.getLogger(CapturaTransacionalClient.class);

	private final CapturaTransacionalProperties properties;

	private final RestTemplate restTemplate;

	@Autowired
	public CapturaTransacionalClient(CapturaTransacionalProperties properties, RestTemplate restTemplate) {
		super();
		this.properties = properties;
		this.restTemplate = restTemplate;
	}

	@Async
	public void enviarRequest(final String requestUrl, final String sessionId, final String method, final String clientIP, final String clientHost) {

		log.debug("Enviando Request Para ser Salvo URL: {}, Session: {}, Metodo: {} ", requestUrl, sessionId, method);

		try {

			final HttpEntity<StwMonitorRequestDto> request = new HttpEntity<>(StwMonitorRequestDto.builder()
					.withHostName(properties.getName())
					.withApiVersion(properties.getApiVersion())
					.withPathServico(requestUrl)
					.withMethod(method)
					.withClientIP(clientIP)
					.withClientHost(clientHost)
					.withSessionId(sessionId).build());

			restTemplate.postForObject(properties.getUrlServerRequestLogger(), request, StwMonitorRequestDto.class);

		} catch (Exception e) {
			log.warn("Connection refused: connect is URL: {}", properties.getUrlServerRequestLogger());
		}

		log.debug("Request Enviada");
	}

	@Async
	public void enviarResponse(final String requestUrl, final String sessionId, final String method, HttpStatus httpStatus) {

		log.debug("Enviando Response Para ser Salvo URL: {}, Metodo: {}, Status: {}", requestUrl, method, httpStatus);

		try {

			final HttpEntity<StwMonitorResponseDto> response = new HttpEntity<>(StwMonitorResponseDto.builder()
					.withHostName(properties.getName())
					.withApiVersion(properties.getApiVersion())
					.withMethod(method)
					.withStatus(httpStatus)
					.withPathServico(requestUrl)
					.withSessionId(sessionId).build());

			restTemplate.postForObject(properties.getUrlServerResponseLogger(), response, StwMonitorResponseDto.class);

		} catch (Exception e) {
			
			log.error("ERROR SERVER", e);
			log.warn("Connection refused: connect is URL: {}", properties.getUrlServerResponseLogger());
		}

		log.debug("Response Enviada");
		
	}
}
