package com.codespark.springbootbasics.redisdata.messagequeue;

public interface MessagePublisher {

	void publish(final Object message);

}