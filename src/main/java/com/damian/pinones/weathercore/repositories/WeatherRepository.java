package com.damian.pinones.weathercore.repositories;

import com.damian.pinones.weathercore.entities.WeatherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer> {

    public Page<WeatherEntity> findAllByCityAndDate(String city, Date date, Pageable pageable);

    public Page<WeatherEntity> findAllByCity(String city, Pageable pageable);

    public Page<WeatherEntity> findAllByDate(Date date, Pageable pageable);

}
