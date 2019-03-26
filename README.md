#API-Analytics

## Referencia

LIB responsavel por interceptar qualquer requisicao e enviar para uma fila onde sera salva para futuro processamento.

### Pre-requisitos

##### Adicionar a dependência no pom do projeto.

``` sh	
	<dependency>
		<groupId>br.com.analytics</groupId>
		<artifactId>api-analytics-logger</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
```
> Verificar versionamento no artifactory. 

#### Configuracao do projeto	
	
	
##### Adicionar no properties do projeto as variáveis que serão utilizados pela lib para identificar o projeto.


``` sh
 
######################################################
#
# Configuracao do Inicializador de Threads task-executo
# para o sistema asyncrono do Observador que sera executado
# em paralelo a requisicao
#
#####################################################
task-executor:
    core-pool-size: 10
    max-pool-size: 100
    queue-capacity: 100
	
######################################################
#
# Configuracao API Interceptor Para indicar qual servidor 
# e URL devera ser enviado as requisiçoes capturadas
# 
#
######################################################
server-logger:
  url-request: http://localhost:8081/api/logger-request
  url-response: http://localhost:8081/api/logger-response
  version: v1  	
  
```
	
> Os dados devem ser substituídos conforme os projetos em que serão aplicados.


##### Criar classe de configuração para inicializar as propriedades da Lib.

``` sh

@Configuration
@ComponentScan(basePackageClasses = {AnalyticsAdapterInit.class})
public class AnalyticsInterceptorConfiguration {

	private static final String SERVER_LOGGER_VERSION = "server-logger.version";

	private static final String SPRING_APPLICATION_NAME = "spring.application.name";

	private static final String SERVER_LOGGER_REQUEST_URL = "server-logger.url-request";
	private static final String SERVER_LOGGER_RESPONSE_URL = "server-logger.url-response";
	
	@Autowired
	private Environment env;
	
	@Bean
	public AnalyticsProperties analyticsProperties() {
		
		return AnalyticsProperties.builder()
				.withName(env.getProperty(SPRING_APPLICATION_NAME))
				.withApiVersion(env.getProperty(SERVER_LOGGER_VERSION))
				.withUrlServerRequestLogger(env.getProperty(SERVER_LOGGER_REQUEST_URL))
				.withUrlServerResponseLogger(env.getProperty(SERVER_LOGGER_RESPONSE_URL))
				.build();
	}
}


```

 
	
	

	
	
	
	