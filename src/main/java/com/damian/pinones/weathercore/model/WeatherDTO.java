package com.damian.pinones.weathercore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class WeatherDTO {

    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Float lat;
    private Float lon;

    @NonNull
    @NotBlank
    private String city;

    @NonNull
    @NotBlank
    private String state;
    @Size(max = 24)
    private List<Double> temperatures;

    public WeatherDTO(Integer id, Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.id = id;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures = temperatures;
    }

    public WeatherDTO(Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures = temperatures;
    }
}
