package com.codespark.springbootbasics.rabbitmqmessaging.fanout_routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutEventConsumer2 {

	private final Logger logger = LoggerFactory.getLogger(FanoutEventConsumer2.class);

	@RabbitListener(queues = "${events.fanout-queue2-name}")
	public void receiveFanoutMessage(String message) {
		logger.info("[*] Received message: '{}' in Consumer 2", message);
	}

}
