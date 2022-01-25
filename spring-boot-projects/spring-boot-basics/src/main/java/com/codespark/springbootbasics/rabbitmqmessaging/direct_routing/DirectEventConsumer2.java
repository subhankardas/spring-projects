package com.codespark.springbootbasics.rabbitmqmessaging.direct_routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectEventConsumer2 {

	private final Logger logger = LoggerFactory.getLogger(DirectEventConsumer2.class);

	@RabbitListener(queues = "${events.direct-queue2-name}")
	public void receiveDirectMessage(String message) {
		logger.info("[*] Received message: '{}' in Consumer2", message);
	}

}
