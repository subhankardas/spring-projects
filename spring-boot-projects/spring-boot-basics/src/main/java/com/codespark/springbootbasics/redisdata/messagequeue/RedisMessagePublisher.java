package com.codespark.springbootbasics.redisdata.messagequeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ChannelTopic topic;

	public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	/**
	 * Publishes message by topic.
	 */
	public void publish(Object report) {
		redisTemplate.convertAndSend(topic.getTopic(), report);
	}

}