package br.com.stw.captura.transacional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.stw.captura.transacional.handler.RequestSuccessHandler;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Configuration
public class CapturaTransacionalInit implements WebMvcConfigurer {
 
    private final RequestSuccessHandler taxiFareRequestInterceptor;
 
    @Autowired
    public CapturaTransacionalInit(RequestSuccessHandler taxiFareRequestInterceptor) {
		super();
		this.taxiFareRequestInterceptor = taxiFareRequestInterceptor;
	}


	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(taxiFareRequestInterceptor);
    }
    
 
}
