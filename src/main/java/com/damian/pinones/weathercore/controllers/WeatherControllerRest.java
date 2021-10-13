package com.damian.pinones.weathercore.controllers;

import com.damian.pinones.weathercore.exception.WeatherException;
import com.damian.pinones.weathercore.model.WeatherDTO;
import com.damian.pinones.weathercore.services.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
@Api(tags = "Weather API")
public class WeatherControllerRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherControllerRest.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test");
    }

    @ApiOperation(value="Guardar Weather")
    @PostMapping
    public ResponseEntity<?> saveWeather(@RequestBody WeatherDTO weatherDTO){

        try {
            Integer id = weatherService.saveWeather(weatherDTO);
            URI location = ServletUriComponentsBuilder.
                    fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (WeatherException e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation(value="Obtener Weather con ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getWeatherById(@PathVariable Integer id){

        WeatherDTO weatherDTO;
        try {
            Optional<WeatherDTO> weatherDTOOptional = weatherService.getWeatherById(id);
            weatherDTO = weatherDTOOptional.orElseThrow(NoSuchElementException::new);
            return ResponseEntity.ok(weatherDTO);
        }catch (NoSuchElementException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation(value="Obtener lista de Weathers")
    @GetMapping
    public ResponseEntity<?> getWeathers(@RequestParam(required = false, name = "city")  String city,
                                                                           @RequestParam(required = false, name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                                           @RequestParam(defaultValue = "id,asc", name = "sort") String sort,
                                                                           @RequestParam(defaultValue = "0", name = "page") Integer page,
                                                                           @RequestParam(defaultValue = "10", name = "size") Integer size) {
        try {
            List<WeatherDTO> weathers = weatherService.findAllWeather(city, date, sort, page, size);
            return ResponseEntity.ok(weathers);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
