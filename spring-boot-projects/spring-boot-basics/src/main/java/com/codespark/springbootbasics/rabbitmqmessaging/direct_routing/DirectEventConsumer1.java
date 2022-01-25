package com.codespark.springbootbasics.rabbitmqmessaging.direct_routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectEventConsumer1 {

	private final Logger logger = LoggerFactory.getLogger(DirectEventConsumer1.class);

	@RabbitListener(queues = "${events.direct-queue1-name}")
	public void receiveDirectMessage(String message) {
		logger.info("[*] Received message: '{}' in Consumer1", message);
	}

}
