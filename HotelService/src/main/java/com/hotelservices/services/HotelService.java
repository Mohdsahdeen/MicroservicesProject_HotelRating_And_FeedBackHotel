package com.hotelservices.services;

import com.hotelservices.entites.Hotel;

import java.util.List;

public interface HotelService {

    // create hotel
    Hotel createHotel(Hotel hotel);

    // get all hotel
    List<Hotel> getAllHotel();


    // get single hotel
    Hotel getSingleHotel(String id);

}
