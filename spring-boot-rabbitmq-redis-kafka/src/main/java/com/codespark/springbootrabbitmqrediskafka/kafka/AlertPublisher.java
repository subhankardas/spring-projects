package com.codespark.springbootrabbitmqrediskafka.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlertPublisher {

    @Autowired
    private KafkaTemplate<String, AlertMessage<String>> kafkaTemplate;

    public void notify(String message) {
        // Create alert message
        AlertMessage<String> alert = new AlertMessage<>();
        alert.setId(System.currentTimeMillis());
        alert.setContent(message);
        alert.setType(AlertType.NOTIFICATION);

        // Send alert message on --> topic
        kafkaTemplate.send("alerts", alert);
    }

}
