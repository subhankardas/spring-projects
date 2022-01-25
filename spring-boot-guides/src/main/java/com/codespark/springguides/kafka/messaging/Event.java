package com.codespark.springguides.kafka.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event<T> {

	private long id;
	private EventType type;
	private T content;

}