package br.com.claro.stw.captura.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.claro.stw.captura.config.HealthServerIndicator;
import br.com.claro.stw.captura.config.RabbitMQConfig;
import br.com.claro.stw.captura.config.prop.CapturaTransacionalProperties;
import br.com.claro.stw.captura.domain.StwMonitorRequestDto;
import br.com.claro.stw.captura.domain.StwMonitorResponseDto;
import br.com.claro.stw.captura.service.ProducerService;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Service
public class CapturaTransacionalClient {

	private final static Logger log = LoggerFactory.getLogger(CapturaTransacionalClient.class);

	private final CapturaTransacionalProperties properties;

	private final HealthServerIndicator serverIndicator;

	private final RestTemplate restTemplate;
	
	private final ProducerService produtor;
	
	
	public CapturaTransacionalClient(CapturaTransacionalProperties properties, HealthServerIndicator serverIndicator,
			RestTemplate restTemplate, ProducerService produtor) {
		super();
		this.properties = properties;
		this.serverIndicator = serverIndicator;
		this.restTemplate = restTemplate;
		this.produtor = produtor;
	}

	public void enviarRequest(final String requestUrl, final String sessionId, final String method, final String clientIP, final String clientHost) {

		log.debug("Enviando Request Para ser Salvo URL: {}, Session: {}, Metodo: {} ", requestUrl, sessionId, method);


		try {

			if(serverIndicator.indisponivel()) {
				log.warn("Connection refused: connect is URL: {}", properties.getUrlServerResponseLogger());
				return;
			}
			
			final StwMonitorRequestDto request = StwMonitorRequestDto.builder()
													.withHostName(properties.getName())
													.withApiVersion(properties.getApiVersion())
													.withStatus(HttpStatus.CONTINUE)
													.withPathServico(requestUrl)
													.withMethod(method)
													.withClientIP(clientIP)
													.withClientHost(clientHost)
													.withSessionId(sessionId).build();

			try {
				produtor.enviarRequest(request, sessionId);

			} catch (Exception e) {
				log.warn("Connection refused: connect is Fila: {}", RabbitMQConfig.QUEUE_NAME_REQUEST);

				enviarViaPost(request, StwMonitorRequestDto.class);

			}
		} catch (Exception e) {
			log.debug("ERROR SERVER", e);
		}

		log.debug("Request Enviada");
	}

	public void enviarResponse(final String requestUrl, final String sessionId, final String method, HttpStatus httpStatus) {

		log.debug("Enviando Response Para ser Salvo URL: {}, Metodo: {}, Status: {}", requestUrl, method, httpStatus);

		try {
				
			if(serverIndicator.indisponivel()) {
				log.warn("Connection refused: connect is URL: {}", properties.getUrlServerResponseLogger());
				return;
			}
			
			final StwMonitorResponseDto response = StwMonitorResponseDto.builder()
					.withHostName(properties.getName())
					.withApiVersion(properties.getApiVersion())
					.withMethod(method)
					.withStatus(httpStatus)
					.withPathServico(requestUrl)
					.withSessionId(sessionId).build();

			try {
				produtor.enviarResponse(response, sessionId);
				
			} catch (Exception e) {

				log.warn("Connection refused: connect is Fila: {}", RabbitMQConfig.QUEUE_NAME_RESPONSE);

				enviarViaPost(response, StwMonitorResponseDto.class);
			}

		} catch (Exception e) {
			log.debug("ERROR SERVER", e);
		}

		log.debug("Response Enviada");
		
	}
	
	private  <E> void enviarViaPost(final E object, final Class<?> clazz) {
		final HttpEntity<E> responseHttp = new HttpEntity<>(object);
		restTemplate.postForObject(properties.getUrlServerResponseLogger(), responseHttp, clazz);
	}

}
