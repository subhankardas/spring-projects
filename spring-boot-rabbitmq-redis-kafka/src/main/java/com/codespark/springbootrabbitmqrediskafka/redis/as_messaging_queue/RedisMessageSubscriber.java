package com.codespark.springbootrabbitmqrediskafka.redis.as_messaging_queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.lang.Nullable;

import com.codespark.springbootrabbitmqrediskafka.redis.as_datastore.Account;

public class RedisMessageSubscriber implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(RedisMessageSubscriber.class);

    @Override
    public void onMessage(final Message message, @Nullable final byte[] pattern) {
        Account account = new Jackson2JsonRedisSerializer<>(Account.class)
                .deserialize(message.getBody());
        String channel = new String(pattern);

        log.info("Received message on channel {}: {}", channel, account);
    }

}
