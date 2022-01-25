package com.codespark.springbootbasics.rabbitmqmessaging.default_routing;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class DefaultEventProducer {

	private final RabbitTemplate rabbitTemplate;

	private final Queue defaultQueue;

	public DefaultEventProducer(RabbitTemplate rabbitTemplate, Queue defaultQueue) {
		this.rabbitTemplate = rabbitTemplate;
		this.defaultQueue = defaultQueue;
	}

	public void sendMessage(String message) {
		rabbitTemplate.convertAndSend(defaultQueue.getName(), message);
	}

}
