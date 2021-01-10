package org.greeting.service;

import lombok.extern.slf4j.Slf4j;
import org.greeting.exception.NotFoundException;
import org.greeting.model.Hotel;
import org.greeting.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HotelServiceImpl implements ServiceImpl<Hotel> {

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
    public List<Hotel> findAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel updateById(Hotel hotel, String id) {
        log.info("Fetching hotel with id {}", id);
        Hotel nhotel = this.hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id (%s) could not be found", id)));
        nhotel.setName(hotel.getName());
        nhotel.setPricePerNight(hotel.getPricePerNight());
        nhotel.setAddress(hotel.getAddress());
        nhotel.setReviews(hotel.getReviews());
        return this.hotelRepository.save(nhotel);
    }

    @Override
    public void deleteById(String id) {
        log.info("Fetching hotel with id {}", id);
        this.hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id (%s) could not be found", id)));
        this.hotelRepository.deleteById(id);
    }

    @Override
    public Optional<Hotel> findById(String id) {
        log.info("Fetching hotel with id {}", id);
        this.hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id (%s) could not be found", id)));
        return this.hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> findByPricePerNightBetween(int min, int max) {
        log.info("Fetching hotels with a price per night between {} and {}", min, max);
        if (this.hotelRepository.findByPricePerNightBetween(min, max).isEmpty()) {
            throw new NotFoundException(String.format("Hotels with a price per night between (%s)€ and (%s)€ could not be found", min, max));
        }
        return this.hotelRepository.findByPricePerNightBetween(min, max);
    }
}
