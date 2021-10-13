package com.damian.pinones.weathercore.exception;

public class WeatherException extends Exception {

    private static final long serialVersionUID = 1L;

    public WeatherException(String msg, Exception e) {
        super(msg, e);
    }

    public WeatherException(String msg) {
        super(msg);
    }
}
