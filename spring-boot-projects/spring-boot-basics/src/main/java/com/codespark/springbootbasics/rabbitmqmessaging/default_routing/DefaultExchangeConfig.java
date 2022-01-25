package com.codespark.springbootbasics.rabbitmqmessaging.default_routing;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TYPE 1: Default routing
 * Routing is based on queue name i.e routing key is the queue name.
 * 
 * PRODUCER ---[QUEUE_NAME = QUEUE1]---> EXCHANGE ------> QUEUE1
 * 
 */
@Configuration
public class DefaultExchangeConfig {

	@Value("${events.default-queue-name}")
	private String QUEUE_NAME;

	@Bean
	public Queue defaultQueue() {
		return new Queue(QUEUE_NAME);
	}
}
