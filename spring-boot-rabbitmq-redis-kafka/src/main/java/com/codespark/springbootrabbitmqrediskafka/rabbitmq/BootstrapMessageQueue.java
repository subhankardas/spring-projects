package com.codespark.springbootrabbitmqrediskafka.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapMessageQueue implements CommandLineRunner {

    @Autowired
    private DefaultEventProducer defaultEventProducer;

    @Autowired
    private DirectEventProducer directEventProducer;

    @Autowired
    private FanoutEventProducer fanoutEventProducer;

    @Autowired
    private TopicEventProducer topicEventProducer;

    @Override
    public void run(String... args) throws Exception {
        // Sends message to default queue by using queue name
        defaultEventProducer.send("Hello RabbitMQ!");

        // Sends message to direct queue by using routing key
        directEventProducer.send("direct-route-key-1", "This is a alert for admins!");
        directEventProducer.send("direct-route-key-2", "This is a notification for users!");

        // Sends message to fanout queues binded to an exchange
        fanoutEventProducer.send("This is a message broadcast...");

        // Sends message to topic exchange by using topic names as routing keys
        topicEventProducer.send("topic1.login", "New login detected for John!");
        topicEventProducer.send("topic2.account.delete", "Account deleted for Jane :(");
        topicEventProducer.send("topic2.profile.update", "Profile updated for Mike!");
    }

}
