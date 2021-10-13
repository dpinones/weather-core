package com.damian.pinones.weathercore.startup;

import com.damian.pinones.weathercore.entities.WeatherEntity;
import com.damian.pinones.weathercore.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class WeatherStartup {

    @Autowired
    private WeatherRepository weatherRepository;

    @PostConstruct
    public void init(){

        WeatherEntity weatherEntity1 = new WeatherEntity(new GregorianCalendar(2019, Calendar.JUNE, 11).getTime(), (float) 41.8818, (float) -87.6231, "Chicago", "Illinois",
                Arrays.asList(24.0, 21.5, 24.0, 19.5, 25.5, 24.0, 25.0, 23.0, 22.0, 18.0, 18.0, 23.5, 23.0, 23.0, 25.5, 21.0, 20.5, 20.0, 18.5, 20.5, 21.0, 25.0, 20.5));
        weatherRepository.save(weatherEntity1);

        WeatherEntity weatherEntity2 = new WeatherEntity(new GregorianCalendar(2019, Calendar.JUNE, 12).getTime(), (float) 37.8043, (float) -122.2711, "Oakland", "California",
                Arrays.asList(24.0, 36.0, 28.5, 29.0, 32.0, 36.0, 28.5, 34.5, 30.5, 31.5, 29.5, 27.0, 30.5, 23.5, 29.0, 22.0, 28.5, 32.5, 24.5, 28.5, 22.5, 35.0, 26.5, 32.5));
        weatherRepository.save(weatherEntity2);

        WeatherEntity weatherEntity3 = new WeatherEntity(new GregorianCalendar(2019, Calendar.JUNE, 11).getTime(), (float) 55.7512, (float) 37.6184, "Moscow", "N/A",
                Arrays.asList(-2.0, -4.5, 1.0, -6.0, 1.0, 1.5, -9.0, -2.5, -3.0, -0.5, -13.5, -9.0, -11.5, -5.5, -5.5, -3.5, -14.0, -9.5, 1.5, -15.0, -6.5, -7.0, -13.5, -14.5));
        weatherRepository.save(weatherEntity3);

        WeatherEntity weatherEntity4 = new WeatherEntity(new GregorianCalendar(2019, Calendar.JUNE, 12).getTime(), (float) 55.7512, (float) 37.6184, "Moscow", "N/A",
                Arrays.asList(-13.5, -2.0, -4.5, 1.0, -6.0, 1.0, 1.5, -9.0, -2.5, -3.0, -0.5, -13.5, -9.0, -11.5, -5.5, -5.5, -3.5, -14.0, -9.5, 1.5, -15.0, -6.5, -7.0, -13.5, -14.5));
        weatherRepository.save(weatherEntity4);
    }
}
