package com.codespark.springbootbasics.rabbitmqmessaging.direct_routing;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class DirectEventProducer {

	private final RabbitTemplate rabbitTemplate;

	private final DirectExchange directExchange;

	public DirectEventProducer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.directExchange = directExchange;
	}

	public void sendMessage(String routeKey, String message) {
		rabbitTemplate.convertAndSend(directExchange.getName(), routeKey, message);
	}

}
