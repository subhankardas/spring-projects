package com.codespark.springbootrabbitmqrediskafka.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/*
 * TYPE 1: Default routing
 * Routing is based on queue name i.e routing key is the queue name.
 * 
 * PRODUCER ---[QUEUE_NAME = QUEUE1]---> EXCHANGE ------> QUEUE1
 * 
 */
@Configuration
public class DefaultRoutingConfig {

    @Bean
    public Queue defaultQueue() {
        return new Queue("default-queue-1");
    }

}

@Component
class DefaultEventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue defaultQueue;

    public void send(String message) {
        rabbitTemplate.convertAndSend(defaultQueue.getName(), message);
    }

}

@Component
class DefaultEventConsumer {

    private final Logger log = LoggerFactory.getLogger(DefaultEventConsumer.class);

    @RabbitListener(queues = "default-queue-1")
    public void receive(String message) {
        log.info("Received message from default: '{}'", message);
    }

}