package org.greeting.service;

import org.greeting.model.Hotel;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IHotelService {

    Hotel save(Hotel t);

    List<Hotel> findAll(Pageable pageable);

    Hotel updateById(Hotel t, String id);

    void deleteById(String id);

    Optional<Hotel> findById(String id);

    List<Hotel> findByPricePerNightBetween(int min, int max, Pageable pageable);
}
