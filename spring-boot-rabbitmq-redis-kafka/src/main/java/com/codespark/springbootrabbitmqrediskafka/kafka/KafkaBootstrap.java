package com.codespark.springbootrabbitmqrediskafka.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaBootstrap implements CommandLineRunner {

    @Autowired
    private AlertPublisher publisher;

    @Override
    public void run(String... args) throws Exception {
        publisher.notify("Hello Kafka!");
    }

}
