/*
 * 
 */
package com.fsd.backend.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fsd.backend.Entity.User;
import com.fsd.backend.Repository.UserRepo;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 *
 */
/**
 * @author paiprasadm
 *
 */
@Service
@Transactional
public class UserService {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(UserService.class);
	
	/** The user repo. */
	private final UserRepo userRepo;
	
	/**
	 * Instantiates a new user service.
	 *
	 * @param userRepo the user repo
	 */
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User addUser(User user) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: User Service ");
		log.debug("Inside Method Name: addUser");
		log.debug("===========================================");
		User i = userRepo.save(user);
		return i;
	}
	
	/**
	 * Find user by id.
	 *
	 * @param Id the id
	 * @return the user
	 */
	public User findUserById(Long Id) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: User Service ");
		log.debug("Inside Method Name: findUserById ");
		log.debug("===========================================");
		Optional<User> userIfExist = userRepo.findById(Id);
		if(userIfExist != null) 
			return (User) userIfExist.get();
			else
			return null;
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<User> getAllUsers()
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: User Service ");
		log.debug("Inside Method Name: getAllUsers ");
		log.debug("===========================================");
		Iterable<User> user = userRepo.findAll();
		List<User> userList = Lists.newArrayList(user);
		return userList;
	}
	

	/**
	 * Delete user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User deleteUser(User user) {
		log.debug("===========================================");
		log.debug("Inside Class Name: User Service ");
		log.debug("Inside Method Name: deleteUser ");
		log.debug("===========================================");
		userRepo.delete(user);
		return user;
	}
	
}
