package com.codespark.springbootrestfulweb.restconsumer;

import java.util.Arrays;

public class WeatherDTO {

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

    public WeatherDTO() {
    }

    public WeatherDTO(Object coord, Object[] weather, String base, Object main, long visibility, Object wind,
            Object rain, Object clouds, long dt, Object sys, long id, String name, int cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public void setCoord(Object coord) {
        this.coord = coord;
    }

    public void setWeather(Object[] weather) {
        this.weather = weather;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setMain(Object main) {
        this.main = main;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public void setWind(Object wind) {
        this.wind = wind;
    }

    public void setRain(Object rain) {
        this.rain = rain;
    }

    public void setClouds(Object clouds) {
        this.clouds = clouds;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public void setSys(Object sys) {
        this.sys = sys;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Object getCoord() {
        return coord;
    }

    public Object[] getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Object getMain() {
        return main;
    }

    public long getVisibility() {
        return visibility;
    }

    public Object getWind() {
        return wind;
    }

    public Object getRain() {
        return rain;
    }

    public Object getClouds() {
        return clouds;
    }

    public long getDt() {
        return dt;
    }

    public Object getSys() {
        return sys;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    @Override
    public String toString() {
        return "WeatherData [coord=" + coord + ", weather=" + Arrays.toString(weather) + ", base=" + base + ", main="
                + main + ", visibility=" + visibility + ", wind=" + wind + ", rain=" + rain + ", clouds=" + clouds
                + ", dt=" + dt + ", sys=" + sys + ", id=" + id + ", name=" + name + ", cod=" + cod + "]";
    }

}
