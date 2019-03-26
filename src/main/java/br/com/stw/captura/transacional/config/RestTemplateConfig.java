package br.com.stw.captura.transacional.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate customRestTemplateCustomizer() {
	    return new RestTemplateBuilder().build();
	}
}
