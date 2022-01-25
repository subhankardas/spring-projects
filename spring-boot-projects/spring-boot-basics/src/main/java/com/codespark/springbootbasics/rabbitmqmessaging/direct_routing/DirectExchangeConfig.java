package com.codespark.springbootbasics.rabbitmqmessaging.direct_routing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TYPE 2: Direct routing
 * Routing is based on binding key i.e route key is matched with binding key.
 * Here route key is the binding key.
 * 
 * PRODUCER ---[ROUTE_KEY]---> EXCHANGE ---[BINDING_KEY1]---> QUEUE1
 * 										---[BINDING_KEY2]---> QUEUE2
 * 
 */
@Configuration
public class DirectExchangeConfig {

	@Value("${events.direct-queue1-name}")
	private String QUEUE_NAME1;

	@Value("${events.direct-queue2-name}")
	private String QUEUE_NAME2;

	@Value("${events.direct-exchange-name}")
	private String EXCHANGE_NAME;

	@Bean
	public Queue directQueue1() {
		return new Queue(QUEUE_NAME1);
	}

	@Bean
	public Queue directQueue2() {
		return new Queue(QUEUE_NAME2);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	public Binding directBinding1(DirectExchange directExchange, Queue directQueue1) {
		return BindingBuilder.bind(directQueue1).to(directExchange).with("ROUTE_KEY_1");
	}

	@Bean
	public Binding directBinding2(DirectExchange directExchange, Queue directQueue2) {
		return BindingBuilder.bind(directQueue2).to(directExchange).with("ROUTE_KEY_2");
	}
}
