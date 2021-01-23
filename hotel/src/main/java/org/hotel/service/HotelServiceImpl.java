package org.hotel.service;

import lombok.extern.slf4j.Slf4j;
import org.hotel.exception.NotFoundException;
import org.hotel.model.Hotel;
import org.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelServiceImpl implements IHotelService {

    private static final String FETCHING_HOTEL_BY_ID = "Fetching hotel with id {}";

    private HotelRepository hotelRepository;

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        log.info("creating hotel : {}", hotel);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> findAll(Pageable pageable) {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel updateById(Hotel hotel, String id) {
        log.info(FETCHING_HOTEL_BY_ID, id);
        Hotel updatedHotel = this.hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s could not be found", id)));
        updatedHotel.setName(hotel.getName());
        updatedHotel.setPricePerNight(hotel.getPricePerNight());
        updatedHotel.setAddress(hotel.getAddress());
        updatedHotel.setReviews(hotel.getReviews());
        return this.hotelRepository.save(updatedHotel);
    }

    @Override
    public void deleteById(String id) {
        log.info(FETCHING_HOTEL_BY_ID, id);
        if (this.hotelRepository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("Hotel with id %s could not be found", id));
        }
        this.hotelRepository.deleteById(id);
    }

    @Override
    public Optional<Hotel> findById(String id) {
        log.info(FETCHING_HOTEL_BY_ID, id);
        if (this.hotelRepository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("Hotel with id %s could not be found", id));
        }
        return this.hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> findByPricePerNightBetween(int min, int max, Pageable pageable) {
        log.info("Fetching hotels with a price per night between {} and {}", min, max);
        if (this.hotelRepository.findByPricePerNightBetween(min, max, pageable).isEmpty()) {
            throw new NotFoundException(String.format("Hotels with a price per night between %s € and %s € could not be found", min, max));
        }
        return this.hotelRepository.findByPricePerNightBetween(min, max, pageable);
    }
}
