package br.com.stw.captura.transacional.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.stw.captura.transacional.config.prop.CapturaTransacionalProperties;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Component
public class HealthServerIndicator {

    private static final String STATUS = "status";
	private static final String UP = "UP";
	private RestTemplate restTemplate;
    private final CapturaTransacionalProperties env;
    private Boolean ativo;

    public HealthServerIndicator(RestTemplate restTemplate, CapturaTransacionalProperties env) {
		super();
		this.restTemplate = restTemplate;
		this.env = env;
		this.ativo = false;
	}

    @Scheduled(fixedDelay = 1000)
    public void health() {
        try {

        	final JsonNode resp = restTemplate.getForObject(env.getUrlServerManagementHealth(), JsonNode.class);
            
        	if (resp.get(STATUS).asText().equalsIgnoreCase(UP)) {
            	this.ativo = true;
            	return;
            } 
        } catch (Exception ex) {
            this.ativo = false;
        }
        this.ativo = false;
    }

	public Boolean indisponivel() {
		return !ativo;
	}
}