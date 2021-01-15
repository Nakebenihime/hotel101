package org.greeting.service;

import org.greeting.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface IHotelService {

    Hotel save(Hotel t);

    List<Hotel> findAll();

    Hotel updateById(Hotel t, String id);

    void deleteById(String id);

    Optional<Hotel> findById(String id);

    List<Hotel> findByPricePerNightBetween(int min, int max);
}
