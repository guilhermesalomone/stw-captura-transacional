package br.com.claro.stw.captura.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.com.claro.stw.captura.config.RabbitMQConfig;
import br.com.claro.stw.captura.domain.StwMonitorRequestDto;
import br.com.claro.stw.captura.domain.StwMonitorResponseDto;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Component
public class ProducerService {

	private static Logger log = LoggerFactory.getLogger(ProducerService.class);

	private static final String ROUTING_KEY_REQUEST = "request.";
	
	private static final String ROUTING_KEY_RESPONSE = "response.";
	
	private final RabbitTemplate rabbitTemplate;

	public ProducerService(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void enviarRequest(StwMonitorRequestDto request, String sessionId) {

		log.debug("Enviando Request Para ser Salvo Fila: {}, Session: {} , Objeto: {}",
				RabbitMQConfig.QUEUE_NAME_REQUEST, sessionId, request);

		enviarViaAMQP(ROUTING_KEY_REQUEST + sessionId, request);

		log.debug("Request Enviada");
	}

	public void enviarResponse(StwMonitorResponseDto response, String sessionId) {

		log.debug("Enviando Response Para ser Salvo Fila: {}, Session: {}, Objeto: {} ",
				RabbitMQConfig.ROUTING_KEY_RESPONSE, sessionId, response);

		enviarViaAMQP(ROUTING_KEY_RESPONSE + sessionId, response);

		log.debug("Response Enviada");

	}
	
	private <E> void enviarViaAMQP(final String routingKey, final E response) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey,
				response);
	}


}