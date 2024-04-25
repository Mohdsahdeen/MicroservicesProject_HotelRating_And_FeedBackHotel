package com.userservices.services;

import java.util.*;
import java.util.stream.Collectors;

import com.userservices.entities.Hotel;
import com.userservices.entities.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.userservices.entities.User;
import com.userservices.exception.ResourceNotFoundException;
import com.userservices.repositories.UserRepository;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		// generate unique userId
	String randomUserId = 	UUID.randomUUID().toString();
	user.setUserId(randomUserId);
	
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
	
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String userId) {

		// get user from databse
		User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! :"+ userId));

		// fetch rating of the above user from Rating service
		// http://localhost:8083/ratings/users/1ff44493-fae0-497f-a187-72bfa7f4f3aa
        // here we use rest template for calling http api from microservices

		Rating[] ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingOfUser);
	    List<Rating> ratings = 	Arrays.stream(ratingOfUser).toList();

		// here we fetch the hotels by rating which is user provide
	    List<Rating> ratingList = 	ratings.stream().map(rating -> {

             // api call of hotel service to get the hotel
			//http://localhost:8082/hotels/6c1f15d6-fead-4ed5-a6bc-9a37a21c4514
		ResponseEntity<Hotel> forEntity =  restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
        Hotel hotel =   forEntity.getBody();
		logger.info("response status code: {} ", forEntity.getStatusCode());
			 // set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());



         user.setRatings(ratingList);
		return user;
	}

	

	

}
