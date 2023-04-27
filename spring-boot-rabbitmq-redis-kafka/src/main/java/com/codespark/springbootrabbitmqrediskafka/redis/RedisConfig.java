package com.codespark.springbootrabbitmqrediskafka.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.codespark.springbootrabbitmqrediskafka.redis.as_datastore.Account;
import com.codespark.springbootrabbitmqrediskafka.redis.as_messaging_queue.RedisMessagePublisher;
import com.codespark.springbootrabbitmqrediskafka.redis.as_messaging_queue.RedisMessageSubscriber;

@Configuration
@EnableCaching
@EnableScheduling // Required for running scheduled cache eviction task
@EnableRedisRepositories // Required for using Redis as data store
public class RedisConfig {

    /* Configs for using Redis as data store */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        // Set connection factory and serializer to parse JSON objects
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Account.class));

        return template;
    }

    /* Configs for using Redis as message queue */
    @Bean
    RedisMessagePublisher redisPublisher() {
        return new RedisMessagePublisher();
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("accounts-topic");
    }

    @Bean
    MessageListenerAdapter messageListener() {
        // Setup message listener/subscriber
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        // Setup topic for the subscriber
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

}
