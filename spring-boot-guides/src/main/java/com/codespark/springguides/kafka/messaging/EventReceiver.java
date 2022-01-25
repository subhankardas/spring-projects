package com.codespark.springguides.kafka.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventReceiver {

	@KafkaListener(topics = "${kafka.topics.event}", groupId = "${kafka.group-id.event}")
	public void receive(Event<String> event, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
			@Header(KafkaHeaders.OFFSET) Long offset) {
		handle(event, offset);
	}

	private void handle(Event<String> event, Long offset) {
		switch (event.getType()) {
		case AUDIT:
			log.info("Audit event [" + offset + "] -> [" + event + "]");
			break;
		case CLEANUP:
			log.info("Cleanup event [" + offset + "] -> [" + event + "]");
			break;
		case NOTIFICATION:
			log.info("Notification event [" + offset + "] -> [" + event + "]");
			break;
		default:
			break;
		}
	}

}