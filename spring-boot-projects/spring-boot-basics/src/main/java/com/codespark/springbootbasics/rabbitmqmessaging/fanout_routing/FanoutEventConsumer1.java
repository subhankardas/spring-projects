package com.codespark.springbootbasics.rabbitmqmessaging.fanout_routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutEventConsumer1 {

	private final Logger logger = LoggerFactory.getLogger(FanoutEventConsumer1.class);

	@RabbitListener(queues = "${events.fanout-queue1-name}")
	public void receiveFanoutMessage(String message) {
		logger.info("[*] Received message: '{}' in Consumer 1", message);
	}

}
