package com.codespark.springbootbasics.rabbitmqmessaging.topic_routing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TYPE 4: Topic routing
 * Routing is based on topic i.e routing key is partially matched with the binded topic/pattern.
 * 
 * PRODUCER ---[ROUTE_KEY]---> EXCHANGE ---[BINDING_PATTERN1]---> QUEUE1
 * 										---[BINDING_PATTERN2]---> QUEUE2
 * 
 */
@Configuration
public class TopicExchangeConfig {

	@Value("${events.topic-queue1-name}")
	private String QUEUE_NAME1;

	@Value("${events.topic-queue2-name}")
	private String QUEUE_NAME2;

	@Value("${events.topic-exchange-name}")
	private String EXCHANGE_NAME;

	@Value("${events.topic1}")
	private String TOPIC_1;

	@Value("${events.topic2}")
	private String TOPIC_2;

	@Bean
	public Queue topicQueue1() {
		return new Queue(QUEUE_NAME1);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(QUEUE_NAME2);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Binding topicBinding1(TopicExchange topicExchange, Queue topicQueue1) {
		return BindingBuilder.bind(topicQueue1).to(topicExchange).with(TOPIC_1);
	}

	@Bean
	public Binding topicBinding2(TopicExchange topicExchange, Queue topicQueue2) {
		return BindingBuilder.bind(topicQueue2).to(topicExchange).with(TOPIC_2);
	}

}
