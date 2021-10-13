package com.damian.pinones.weathercore.services;

import com.damian.pinones.weathercore.model.WeatherDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface WeatherService {

    Optional<WeatherDTO> getWeatherById(Integer id) throws Exception;

    List<WeatherDTO> findAllWeather(String city, Date date, String orders, Integer page, Integer size) throws Exception;

    Integer saveWeather(WeatherDTO weatherDTO) throws Exception;

}
