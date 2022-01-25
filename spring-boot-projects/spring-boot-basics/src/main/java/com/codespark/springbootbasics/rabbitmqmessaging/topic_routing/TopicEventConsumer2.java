package com.codespark.springbootbasics.rabbitmqmessaging.topic_routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicEventConsumer2 {

	private final Logger logger = LoggerFactory.getLogger(TopicEventConsumer2.class);

	@RabbitListener(queues = "${events.topic-queue2-name}")
	public void receiveTopicMessage(String message) {
		logger.info("[*] Received message: '{}' in Topic 2 Consumer", message);
	}

}
