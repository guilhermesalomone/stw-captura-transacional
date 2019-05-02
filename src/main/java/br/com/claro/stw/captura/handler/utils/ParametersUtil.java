package br.com.claro.stw.captura.handler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
public class ParametersUtil {

	private static final String POST = "POST";
	private static final Logger log = LoggerFactory.getLogger(ParametersUtil.class);

	public static String get(HttpServletRequest request) {

		try {

			final DadosParametros dados = new DadosParametros();

			getParametros(request, dados);
			getHeader(request, dados);
//			getBody(request, dados);

			final ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			
			return mapper.writeValueAsString(dados);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return "";
	}

	static void getParametros(final HttpServletRequest request, final DadosParametros dados) {
		final Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			final String curr = (String) e.nextElement();
			if(StringUtils.isNotBlank(request.getParameter(curr)))
				dados.adicionarParametros(curr, request.getParameter(curr));
		}
	}

	static void getHeader(final HttpServletRequest request, final DadosParametros dados) {
		final Enumeration<?> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			final String curr = (String) e.nextElement();
			if(StringUtils.isNotBlank(request.getHeader(curr)))
				dados.adicionarHeader(curr, request.getHeader(curr));
		}

	}

	static void getBody(final HttpServletRequest request, final DadosParametros dados) {

		try {
			
			if (POST.equalsIgnoreCase(request.getMethod()) ) {

				final String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//				final String body =  read(request.getInputStream());
//				final String body =  getRequestData(request);
				
				dados.adicionarBody(body);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	static String read(InputStream input) throws IOException {
		
		String body;
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
			body =  buffer.lines().collect(Collectors.joining("\n"));
			buffer.close();
		}
		
		return body;
	}
	
	
    static String getRequestData(final HttpServletRequest request) throws IOException {
        String payload = null;
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
            }
        }
        payload = wrapper.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        return payload;
    }
	
}



class DadosParametros {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Headers header;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Parametros parametros;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String body;

	public DadosParametros adicionarHeader(final String key, final String value) {
		
		if(Objects.isNull(header))
			header = new Headers();
		
		header.adicionar(key, value);
		return this;
	}

	public DadosParametros adicionarParametros(final String key, final String value) {
		
		if(Objects.isNull(parametros))
			parametros = new Parametros();
		
		parametros.adicionar(key, value);
		return this;
	}

	public DadosParametros adicionarBody(final String body) {
		this.body = body;
		return this;
	}

}

class Headers {

	public Map<String, String> valor;

	public Headers() {
		super();
		valor = new HashMap<String, String>();
	}

	public Headers adicionar(final String key, final String value) {

		this.valor.put(key, value);
		return this;
	}

}

class Parametros {
	public Map<String, String> valor = new HashMap<String, String>();

	public Parametros() {
		super();
		valor = new HashMap<String, String>();
	}

	public Parametros adicionar(final String key, final String value) {

		this.valor.put(key, value);
		return this;
	}
}
