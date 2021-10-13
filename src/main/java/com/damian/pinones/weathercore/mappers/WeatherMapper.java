package com.damian.pinones.weathercore.mappers;

import com.damian.pinones.weathercore.entities.WeatherEntity;
import com.damian.pinones.weathercore.model.WeatherDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherDTO getWeatherDTO(WeatherEntity weatherEntity);

    WeatherEntity getWeatherEntity(WeatherDTO weatherDTO);

    List<WeatherDTO> getWeathersDTO(List<WeatherEntity> weatherEntity);

    List<WeatherEntity> getWeathersEntity(List<WeatherDTO> weatherDTO);

}
