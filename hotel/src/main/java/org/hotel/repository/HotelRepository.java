package org.hotel.repository;

import org.hotel.model.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    List<Hotel> findByPricePerNightBetween(int min, int max, Pageable pageable);
}
