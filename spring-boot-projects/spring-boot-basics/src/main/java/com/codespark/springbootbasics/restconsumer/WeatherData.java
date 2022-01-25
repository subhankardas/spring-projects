package com.codespark.springbootbasics.restconsumer;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

	private Object coord;
	private Object[] weather;
	private String base;
	private Object main;
	private long visibility;
	private Object wind;
	private Object rain;
	private Object clouds;
	private long dt;
	private Object sys;
	private long id;
	private String name;
	private int cod;

	@Override
	public String toString() {
		return "WeatherData [coord=" + coord + ", weather=" + Arrays.toString(weather) + ", base=" + base + ", main="
				+ main + ", visibility=" + visibility + ", wind=" + wind + ", rain=" + rain + ", clouds=" + clouds
				+ ", dt=" + dt + ", sys=" + sys + ", id=" + id + ", name=" + name + ", cod=" + cod + "]";
	}

}