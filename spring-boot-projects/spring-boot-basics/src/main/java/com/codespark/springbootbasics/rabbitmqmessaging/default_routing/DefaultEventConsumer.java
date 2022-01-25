package com.codespark.springbootbasics.rabbitmqmessaging.default_routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultEventConsumer {

	private final Logger logger = LoggerFactory.getLogger(DefaultEventConsumer.class);

	@RabbitListener(queues = "${events.default-queue-name}")
	public void receiveDefaultMessage(String message) {
		logger.info("[*] Received message: '{}'", message);
	}

}
