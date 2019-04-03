package br.com.stw.captura.transacional.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EXCHANGE_NAME = "captura-transacional-exchange";

	public static final String QUEUE_NAME_REQUEST = "captura-transacional-request";
	public static final String QUEUE_NAME_RESPONSE = "captura-transacional-response";

	public static final String ROUTING_KEY_RESPONSE = "response.#";
	private static final String ROUTING_KEY_REQUEST = "request.#";
	
	private static final String FANOUT_REQUEST = "fanout.request";
	private static final String FANOUT_RESPONSE = "fanout.response";
	
	@Bean
	Queue queueResponse() {
		return new Queue(QUEUE_NAME_RESPONSE, false);
	}
	@Bean
	Queue queueRequest() {
		return new Queue(QUEUE_NAME_REQUEST, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	Binding bindingRequest() {
		return BindingBuilder.bind(queueRequest()).to(exchange()).with(ROUTING_KEY_RESPONSE);
	}

	@Bean
	Binding bindingResponse() {
		return BindingBuilder.bind(queueResponse()).to(exchange()).with(ROUTING_KEY_REQUEST);
	}

	@Bean
	FanoutExchange fanoutResponse() {
		return  new FanoutExchange(FANOUT_REQUEST);
	}
	
	@Bean
	FanoutExchange fanoutRequest() {
		return  new FanoutExchange(FANOUT_RESPONSE);
	}
	
	
	@Bean
	Binding bindingFanoutRequest() {
		return BindingBuilder.bind(queueRequest()).to(fanoutRequest());
	}

	@Bean
	Binding bindingFanoutResponse() {
		return BindingBuilder.bind(queueResponse()).to(fanoutResponse());
	}
	
	
	@Bean
	@ConditionalOnSingleCandidate(ConnectionFactory.class)
	@ConditionalOnMissingBean(RabbitTemplate.class)
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
