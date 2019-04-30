#API-Analytics

## Referencia

LIB responsavel por interceptar qualquer requisicao e enviar para uma fila onde sera salva para futuro processamento.

### Pre-requisitos

##### Adicionar a depend�ncia no pom do projeto.

``` sh	
	<parent>
		<groupId>br.com.claro.stw</groupId>
		<artifactId>stw-dependencies</artifactId>
		<version>1.0.0-RELEASE</version>
	</parent>
	
	<dependency>
		<groupId>br.com.analytics</groupId>
		<artifactId>stw-captura</artifactId>
	</dependency>
```
> Verificar versionamento no artifactory. 

#### Configuracao do projeto	
	
	
##### Adicionar no properties do projeto as vari�veis que ser�o utilizados pela lib para identificar o projeto.

> Em YAML

``` sh
 

spring:
  application:
    name: conector-teste
 
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
# e URL devera ser enviado as requisi�oes capturadas
# 
#
######################################################
server-logger:
  url-request: http://localhost:8081/api/logger-request
  url-response: http://localhost:8081/api/logger-response
  url-management-health: http://localhost:8081/management/health
  version: v1  	
  
  

  
```
	
> Em Properties

``` sh
	
spring.application.name= conector-teste
  
######################################################
#
# Configuracao do Inicializador de Threads task-executo
#
#
#
#####################################################
task-executor.core-pool-size= 100
task-executor.max-pool-size= 1000
task-executor.queue-capacity= 1000

######################################################
#
# Configuracao API Interceptor
#
######################################################
server-logger.url-request= http://localhost:8081/api/captura-dados/logger-request
server-logger.url-response= http://localhost:8081/api/captura-dados/logger-response
server-logger.version= v1 	
	

  


```
	
> Os dados devem ser substitu�dos conforme os projetos em que ser�o aplicados.


##### Criar classe de configura��o para inicializar as propriedades da Lib.

``` sh


/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Configuration
@ComponentScan(basePackageClasses = {CapturaTransacionalInit.class})
public class CapturaTransacionalConfiguration {


	private static final String SERVER_LOGGER_URL_MANAGEMENT_HEALTH = "server-logger.url-management-health";

	private static final String SERVER_LOGGER_VERSION = "server-logger.version";

	private static final String SPRING_APPLICATION_NAME = "spring.application.name";

	private static final String SERVER_LOGGER_REQUEST_URL = "server-logger.url-request";
	private static final String SERVER_LOGGER_RESPONSE_URL = "server-logger.url-response";
	
	@Autowired
	private Environment env;
	
	@Bean
	public CapturaTransacionalProperties analyticsProperties() {
		
		return CapturaTransacionalProperties.builder()
				.withName(env.getProperty(SPRING_APPLICATION_NAME))
				.withApiVersion(env.getProperty(SERVER_LOGGER_VERSION))
				.withUrlServerRequestLogger(env.getProperty(SERVER_LOGGER_REQUEST_URL))
				.withUrlServerResponseLogger(env.getProperty(SERVER_LOGGER_RESPONSE_URL))
				.withUrlServerManagementHealth(env.getProperty(SERVER_LOGGER_URL_MANAGEMENT_HEALTH))
				.build();
	}
}


```

 
	
	

	
	
	
	