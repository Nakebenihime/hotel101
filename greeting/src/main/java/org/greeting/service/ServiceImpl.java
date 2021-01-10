package org.greeting.service;

import org.greeting.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface ServiceImpl<T> {

    T save(T t);

    List<T> findAll();

    T updateById(T t, String id);

    void deleteById(String id);

    Optional<T> findById(String id);

    List<Hotel> findByPricePerNightBetween(int min, int max);
}
