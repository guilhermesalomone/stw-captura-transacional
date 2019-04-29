package br.com.claro.stw.captura.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * @author Guilherme.Salomone
 *
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer {

	private static final String TASK_EXECUTOR_QUEUE_CAPACITY = "task-executor.queue-capacity";
	private static final String TASK_EXECUTOR_MAX_POOL_SIZE = "task-executor.max-pool-size";
	private static final String TASK_EXECUTOR_CORE_POOL_SIZE = "task-executor.core-pool-size";


	private final Logger log = LoggerFactory.getLogger(AsyncConfiguration.class);

	
	private final Environment env;
	
	@Autowired
	public AsyncConfiguration(Environment env) {
		super();
		this.env = env;
	}

	@Bean(name = "taskExecutor")
	public Executor getAsyncExecutor() {
		log.debug("Creating Async Task Executor");
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(env.getProperty(TASK_EXECUTOR_CORE_POOL_SIZE, Integer.class));
		executor.setMaxPoolSize(env.getProperty(TASK_EXECUTOR_MAX_POOL_SIZE, Integer.class));
		executor.setQueueCapacity(env.getProperty(TASK_EXECUTOR_QUEUE_CAPACITY, Integer.class));
		executor.setThreadNamePrefix("Executor-");
		return executor;
	}

	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}
