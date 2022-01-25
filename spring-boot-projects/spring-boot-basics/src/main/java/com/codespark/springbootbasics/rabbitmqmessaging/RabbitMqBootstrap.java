package com.codespark.springbootbasics.rabbitmqmessaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootbasics.rabbitmqmessaging.default_routing.DefaultEventProducer;
import com.codespark.springbootbasics.rabbitmqmessaging.direct_routing.DirectEventProducer;
import com.codespark.springbootbasics.rabbitmqmessaging.fanout_routing.FanoutEventProducer;
import com.codespark.springbootbasics.rabbitmqmessaging.topic_routing.TopicEventProducer;

@Component
public class RabbitMqBootstrap implements CommandLineRunner {

	@Autowired
	private DefaultEventProducer defaultEventProducer;

	@Autowired
	private FanoutEventProducer fanoutEventProducer;

	@Autowired
	private DirectEventProducer directEventProducer;

	@Autowired
	private TopicEventProducer topicEventProducer;

	@Override
	public void run(String... args) throws Exception {
		// Send message to a specific queue by the queue name
		defaultEventProducer.sendMessage("This is a default message!");

		// Send message to a fan-out exchange which relays message to all binded queues
		fanoutEventProducer.sendMessage("This is a fanout message!");

		// Send message to a specific queue by route key i.e binded to the queue
		directEventProducer.sendMessage("ROUTE_KEY_1", "This is a direct message!");

		// Send message to a specific queue binded to the pattern matching the route key
		topicEventProducer.sendMessage("topic1.dead", "This is a topic message!");
	}

}
