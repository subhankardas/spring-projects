package com.codespark.springbootbasics.rabbitmqmessaging.topic_routing;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicEventProducer {

	private final RabbitTemplate rabbitTemplate;

	private final TopicExchange topicExchange;

	public TopicEventProducer(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.topicExchange = topicExchange;
	}

	public void sendMessage(String topic, String message) {
		rabbitTemplate.convertAndSend(topicExchange.getName(), topic, message);
	}

}
