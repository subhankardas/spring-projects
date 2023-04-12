package com.codespark.springbootrabbitmqrediskafka.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/*
 * TYPE 3: Fan-out routing
 * Routing is like broadcasting to all binded queues.
 * All the queues binded to exchange receives the message.
 * 
 * PRODUCER ---> EXCHANGE ---> QUEUE1
 *                        ---> QUEUE2
 * 
 */
@Configuration
public class FanoutRoutingConfig {

	@Bean
	public Queue fanoutQueue1() {
		return new Queue("fanout-queue-1");
	}

	@Bean
	public Queue fanoutQueue2() {
		return new Queue("fanout-queue-2");
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanout-exchange");
	}

	@Bean
	public Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
		return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
	}

	@Bean
	public Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue fanoutQueue2) {
		return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
	}

}

@Component
class FanoutEventProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private FanoutExchange fanoutExchange;

	public void send(String message) {
		rabbitTemplate.convertAndSend(fanoutExchange.getName(), null, message);
	}

}

@Component
class FanoutEventConsumer1 {

	private final Logger log = LoggerFactory.getLogger(FanoutEventConsumer1.class);

	@RabbitListener(queues = "fanout-queue-1")
	public void receive(String message) {
		log.info("Received message from fanout: '{}' in consumer1", message);
	}

}

@Component
class FanoutEventConsumer2 {

	private final Logger log = LoggerFactory.getLogger(FanoutEventConsumer2.class);

	@RabbitListener(queues = "fanout-queue-2")
	public void receive(String message) {
		log.info("Received message from fanout: '{}' in consumer2", message);
	}

}