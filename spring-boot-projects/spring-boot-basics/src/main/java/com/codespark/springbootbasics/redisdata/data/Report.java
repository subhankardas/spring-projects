package com.codespark.springbootbasics.redisdata.data;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Marks the objects of this class to be stored in Redis hash. Objects are
 * stored in Redis with name REDIS_REPORT. Object is stored into individual
 * hashes with ID appended to the name.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("REDIS_REPORT")
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String creator;

	private double balance;

}