package com.codespark.springbootbasics.rabbitmqmessaging.fanout_routing;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class FanoutEventProducer {

	private final RabbitTemplate rabbitTemplate;

	private final FanoutExchange fanoutExchange;

	public FanoutEventProducer(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.fanoutExchange = fanoutExchange;
	}

	public void sendMessage(String message) {
		rabbitTemplate.convertAndSend(fanoutExchange.getName(), null, message);
	}

}
