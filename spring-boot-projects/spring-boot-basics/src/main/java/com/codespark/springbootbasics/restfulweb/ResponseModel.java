package com.codespark.springbootbasics.restfulweb;

/**
 * Resource Representation Class: Create a model resource representation class.
 * To do so, provide a plain old Java object with fields, constructors, and
 * accessors for the fields.
 */
public class ResponseModel {

	private int userId;
	private String username;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
