package com.codespark.springguides.kafka.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventSender {

	@Autowired
	private KafkaTemplate<String, Event<String>> kafkaTemplate;

	@Value("${kafka.topics.event}")
	public String EVENT_TOPIC;

	/**
	 * Publish messages for handling events.
	 */
	public void send(Event<String> event) {
		ListenableFuture<SendResult<String, Event<String>>> future = kafkaTemplate.send(EVENT_TOPIC, event);
		setEventHandlers(future, event);
	}

	/**
	 * Set message result success and error handlers.
	 */
	private void setEventHandlers(ListenableFuture<SendResult<String, Event<String>>> future, Event<String> message) {
		future.addCallback(new ListenableFutureCallback<SendResult<String, Event<String>>>() {
			// 1. Handle success for event messages
			@Override
			public void onSuccess(SendResult<String, Event<String>> result) {
				handleSucess(result);
			}

			// 2. Handle errors for event messages
			@Override
			public void onFailure(Throwable ex) {
				handleError(ex, message);
			}
		});
	}

	/**
	 * Handle event message sending success.
	 */
	private void handleSucess(SendResult<String, Event<String>> result) {
		long offset = result.getRecordMetadata().offset();
		Event<String> message = result.getProducerRecord().value();
		log.info("Sent message [" + message + "] -> [" + offset + "]");
	}

	/**
	 * Handle event message sending failure.
	 */
	private void handleError(Throwable ex, Event<String> message) {
		log.info("Unable to send message [" + message + "]");
		log.info(ex.getLocalizedMessage());
	}

}
