package com.hackathon.inventoryserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.hackathon.inventoryserver.service.AggregationService;
import com.hackathon.inventoryserver.service.AggregationServiceImpl;

@EnableAsync
@Configuration
public class AsyncConfig {

	@Value("${pool.size:10}")
	private int poolSize;;

	@Value("${queue.capacity:20}")
	private int queueCapacity;

	@Bean(name = "processExecutor")
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setThreadNamePrefix("Async-");
		taskExecutor.setMaxPoolSize(poolSize);
		taskExecutor.setQueueCapacity(queueCapacity);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

	@Bean
	public AggregationService aggregationService() {
		return new AggregationServiceImpl();
	}

}
