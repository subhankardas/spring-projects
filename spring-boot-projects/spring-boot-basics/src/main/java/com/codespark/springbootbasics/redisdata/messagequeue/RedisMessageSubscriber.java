package com.codespark.springbootbasics.redisdata.messagequeue;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import com.codespark.springbootbasics.redisdata.data.Report;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

	/**
	 * Handles message listener implementation.
	 */
	public void onMessage(final Message message, final byte[] pattern) {
		Report report = new Jackson2JsonRedisSerializer<>(Report.class).deserialize(message.getBody());
		String channel = new String(pattern);

		log.info("[X] Received report : '{}' on redis channel '{}'", report.getId(), channel);
	}

}