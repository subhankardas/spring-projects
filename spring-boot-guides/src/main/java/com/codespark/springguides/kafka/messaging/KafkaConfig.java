package com.codespark.springguides.kafka.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

	@Value("${kafka.bootstrap-servers}")
	public String BOOTSTRAP_SERVERS;

	@Value("${kafka.group-id.event}")
	public String EVENT_GROUP_ID;

	/**
	 * 1. Create kafka producer factory and configure properties.
	 */
	@Bean
	public ProducerFactory<String, Event<String>> producerFactory() {
		// Configure producer properties
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(props);
	}

	/**
	 * 2. Create kafka template (used for sending messages) using producer factory.
	 */
	@Bean
	public KafkaTemplate<String, Event<String>> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/**
	 * 3. Create kafka consumer factory and configure properties.
	 */
	@Bean
	public ConsumerFactory<String, Event<String>> consumerFactory() {
		// Configure consumer properties
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, EVENT_GROUP_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Event.class));
	}

	/**
	 * 4. Configure kafka listeners with consumer factory.
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Event<String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Event<String>> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

}
