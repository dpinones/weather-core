package com.damian.pinones.weathercore.services.impl;

import com.damian.pinones.weathercore.entities.WeatherEntity;
import com.damian.pinones.weathercore.exception.WeatherException;
import com.damian.pinones.weathercore.mappers.WeatherMapper;
import com.damian.pinones.weathercore.model.WeatherDTO;
import com.damian.pinones.weathercore.repositories.WeatherRepository;
import com.damian.pinones.weathercore.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @NonNull
    private WeatherRepository weatherRepository;

    @NonNull
    private WeatherMapper weatherMapper;

    public Integer saveWeather(WeatherDTO weatherDTO) throws Exception {
        LOGGER.debug("Inicio proceso de guardado de Weather");

        if(weatherDTO.getId() != null) {
            LOGGER.error("El Weather a guardar no debe tener ID");
            throw new WeatherException("El Weather a guardar no debe tener ID");
        }

        WeatherEntity weatherEntity = weatherMapper.getWeatherEntity(weatherDTO);
        weatherEntity = weatherRepository.save(weatherEntity);
        LOGGER.debug("Finalizo proceso de guardado de Weather");
        return weatherEntity.getId();
    }

    public Optional<WeatherDTO> getWeatherById(Integer id) {
        LOGGER.debug("Inicio proceso para obtener Weather con ID: {}" , id);
        Optional<WeatherEntity> optionWeatherEntity = weatherRepository.findById(id);
        if(!optionWeatherEntity.isPresent()){
            LOGGER.error("Weather inexistente");
            throw new NoSuchElementException("Weather inexistente");
        }
        WeatherEntity weatherEntity = optionWeatherEntity.get();
        WeatherDTO weatherDTO = weatherMapper.getWeatherDTO(weatherEntity);
        LOGGER.debug("Finalizo proceso para obtener Weather con ID: {}" , id);
        return Optional.ofNullable(weatherDTO);
    }

    public List<WeatherDTO> findAllWeather(String city, Date date, String sort, Integer page, Integer size) {
        LOGGER.debug("Inicio proceso para listar Weathers");

        List<Sort.Order> orders = generateFilters(sort);

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<WeatherEntity> pageWeathers;
        if(city == null && date == null) {
            LOGGER.debug("listar Weathers sin filtros");
            pageWeathers = weatherRepository.findAll(pagingSort);
        } else if(city != null && date != null ) {
            LOGGER.debug("listar Weathers con filtro ciudad y fecha");
            pageWeathers = weatherRepository.findAllByCityAndDate(city, date, pagingSort);
        } else if (city != null) {
            LOGGER.debug("listar Weathers con filtro ciudad");
            pageWeathers = weatherRepository.findAllByCity(city, pagingSort);
        } else {
            LOGGER.debug("listar Weathers con filtro fecha");
            pageWeathers = weatherRepository.findAllByDate(date, pagingSort);
        }
        List<WeatherEntity> weathersEntities = pageWeathers.getContent();
        LOGGER.debug("Inicio proceso para listar Weathers");
        return weatherMapper.getWeathersDTO(weathersEntities);
    }

    private List<Sort.Order> generateFilters(String sort){
        LOGGER.debug("Generando filtros");
        List<Sort.Order> orders = new ArrayList<>();
        List<String> listSort = Arrays.asList(sort.split(","));
        Sort.Direction direction = getSortDirection(listSort.get(listSort.size() -1 ));
        for (int i = 0; i <  listSort.size() - 1; i++) {
            orders.add(new Sort.Order(direction, listSort.get(i)));
        }
        return orders;
    }

    private Sort.Direction getSortDirection(String direction) {
        LOGGER.debug("Detectanto direccion de la query");
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }






}
