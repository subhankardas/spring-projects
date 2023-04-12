package com.codespark.springbootrabbitmqrediskafka.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/*
 * TYPE 2: Direct routing
 * Routing is based on binding key i.e route key is matched with binding key.
 * Here route key is the binding key.
 * 
 * PRODUCER ---[ROUTE_KEY]---> EXCHANGE ---[BINDING_KEY1]---> QUEUE1
 *                                      ---[BINDING_KEY2]---> QUEUE2
 * 
 */
@Configuration
public class DirectRoutingConfig {

    @Bean
    public Queue directQueue1() {
        return new Queue("direct-queue-1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("direct-queue-2");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public Binding directBinding1(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("direct-route-key-1");
    }

    @Bean
    public Binding directBinding2(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("direct-route-key-2");
    }

}

@Component
class DirectEventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    public void send(String routeKey, String message) {
        rabbitTemplate.convertAndSend(directExchange.getName(), routeKey, message);
    }

}

@Component
class DirectEventConsumer1 {

    private final Logger log = LoggerFactory.getLogger(DirectEventConsumer1.class);

    @RabbitListener(queues = "direct-queue-1")
    public void receive(String message) {
        log.info("Received message from direct: '{}' in consumer1", message);
    }

}

@Component
class DirectEventConsumer2 {

    private final Logger log = LoggerFactory.getLogger(DirectEventConsumer2.class);

    @RabbitListener(queues = "direct-queue-2")
    public void receive(String message) {
        log.info("Received message from direct: '{}' in consumer2", message);
    }

}