package com.codespark.springbootbasics.rabbitmqmessaging.topic_routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicEventConsumer1 {

	private final Logger logger = LoggerFactory.getLogger(TopicEventConsumer1.class);

	@RabbitListener(queues = "${events.topic-queue1-name}")
	public void receiveTopicMessage(String message) {
		logger.info("[*] Received message: '{}' in Topic 1 Consumer", message);
	}

}
