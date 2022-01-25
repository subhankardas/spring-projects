package com.codespark.springbootbasics.rabbitmqmessaging.fanout_routing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TYPE 3: Fan-out routing
 * Routing is like broadcasting to all binded queues.
 * All the queues binded to exchange receives the message.
 * 
 * PRODUCER ---> EXCHANGE ---> QUEUE1
 * 						  ---> QUEUE2
 * 
 */
@Configuration
public class FanoutExchangeConfig {
	
	@Value("${events.fanout-queue1-name}")
	private String QUEUE_NAME1;

	@Value("${events.fanout-queue2-name}")
	private String QUEUE_NAME2;

	@Value("${events.fanout-exchange-name}")
	private String EXCHANGE_NAME;

	@Bean
	public Queue fanoutQueue1() {
		return new Queue(QUEUE_NAME1);
	}

	@Bean
	public Queue fanoutQueue2() {
		return new Queue(QUEUE_NAME2);
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(EXCHANGE_NAME);
	}

	@Bean
	public Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
		return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
	}

	@Bean
	public Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue fanoutQueue2) {
		return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
	}
}
