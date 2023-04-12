package com.codespark.springbootrabbitmqrediskafka.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class TopicExchangeConfig {

    @Bean
    public Queue topicQueue1() {
        return new Queue("topic-queue-1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic-queue-2");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Binding topicBinding1(TopicExchange topicExchange, Queue topicQueue1) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("topic1.*");
    }

    @Bean
    public Binding topicBinding2(TopicExchange topicExchange, Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("topic2.*.*");
    }

}

@Component
class TopicEventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    public void send(String topic, String message) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), topic, message);
    }

}

@Component
class TopicEventConsumer1 {

    private final Logger log = LoggerFactory.getLogger(TopicEventConsumer1.class);

    @RabbitListener(queues = "topic-queue-1")
    public void receive(String message) {
        log.info("Received message from topic1: '{}' in consumer1", message);
    }

}

@Component
class TopicEventConsumer2 {

    private final Logger log = LoggerFactory.getLogger(TopicEventConsumer2.class);

    @RabbitListener(queues = "topic-queue-2")
    public void receive(String message) {
        log.info("Received message from topic2: '{}' in consumer2", message);
    }

}