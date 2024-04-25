package com.userservices.services;

import java.util.List;
import java.util.Optional;

import com.userservices.entities.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUserById(String userId);
	
	
	
	

}
