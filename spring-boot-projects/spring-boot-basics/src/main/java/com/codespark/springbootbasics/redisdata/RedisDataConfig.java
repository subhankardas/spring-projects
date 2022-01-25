package com.codespark.springbootbasics.redisdata;

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

import com.codespark.springbootbasics.redisdata.data.Report;
import com.codespark.springbootbasics.redisdata.messagequeue.MessagePublisher;
import com.codespark.springbootbasics.redisdata.messagequeue.RedisMessagePublisher;
import com.codespark.springbootbasics.redisdata.messagequeue.RedisMessageSubscriber;

@Configuration
@EnableCaching
@EnableRedisRepositories
//@EnableScheduling
public class RedisDataConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

		// Set connection factory and serializer to parse JSON objects
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Report.class));

		return template;
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

	@Bean
	MessagePublisher redisPublisher() {
		// Setup message publisher for a topic
		return new RedisMessagePublisher(redisTemplate(), topic());
	}

	@Bean
	ChannelTopic topic() {
		// Create message topic
		return new ChannelTopic("pubsub:queue");
	}

}
