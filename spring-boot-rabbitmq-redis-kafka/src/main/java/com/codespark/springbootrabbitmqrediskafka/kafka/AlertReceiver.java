package com.codespark.springbootrabbitmqrediskafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class AlertReceiver {

    private final Logger log = LoggerFactory.getLogger(AlertReceiver.class);

    @KafkaListener(topics = "alerts", groupId = "alerts-group")
    public void receive(AlertMessage<String> message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
            @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Received alert: {}", message);
    }

}
