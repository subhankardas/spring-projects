package com.codespark.springbootbasics.restfulweb;

/**
 * 
 * Model Representation Class: @RequestBody annotation maps the HttpRequest body
 * to a transfer or domain object, enabling automatic deserialization of the
 * inbound HttpRequest body onto a Java object of this class.
 *
 */
public class RequestModel {

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
