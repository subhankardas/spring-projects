package com.codespark.springguides.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springguides.kafka.messaging.Event;
import com.codespark.springguides.kafka.messaging.EventSender;
import com.codespark.springguides.kafka.messaging.EventType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaBootstrap implements CommandLineRunner {

	@Autowired
	private EventSender sender;

	@Override
	public void run(String... args) throws Exception {
		log.info("Handling events using kafka");
		sender.send(new Event<String>(123, EventType.NOTIFICATION, "Mr. XYZ account created successfully."));
		sender.send(new Event<String>(456, EventType.AUDIT, "Mr. ABC changed account type."));
		sender.send(new Event<String>(789, EventType.CLEANUP, "Clean unused accounts."));
	}

}
