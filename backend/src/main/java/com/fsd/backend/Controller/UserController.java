
package com.fsd.backend.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fsd.backend.Entity.User;
import com.fsd.backend.Service.UserService;


// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
/**
 * @author paiprasadm
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/taskManager/userAction")
public class UserController {

		
/** The log. */
private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws Exception the exception
	 */
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user)throws Exception
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: UserController ");
		log.debug("Inside Method Name: addUser ");
		log.debug("User Details to be added:" + user.toString());
		log.debug("===========================================");
		userService.addUser(user);		
		return user;
	}
	
	/**
	 * Gets the all user.
	 *
	 * @return the all user
	 * @throws Exception the exception
	 */
	@GetMapping("/getAllUser")
	public List<User> getAllUser() throws Exception
	{
		List<User> userList = null;
		log.debug("===========================================");
		log.debug("Inside Class Name: UserController ");
		log.debug("Inside Method Name: getAllUser ");
		log.debug("===========================================");
		userList=userService.getAllUsers();
		return  userList;
	}
	
	/**
	 * Delete user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws Exception the exception
	 */
	@PostMapping("/deleteUser")
	public User deleteUser(@RequestBody User user) throws Exception
	{
		User returned = null;
	
		log.debug("===========================================");
		log.debug("Inside Class Name: UserController ");
		log.debug("Inside Method Name: deleteUser ");
		log.debug("===========================================");
		returned=userService.deleteUser(user);
		return  returned;
	}
	
	
	/**
	 * Gets the user by id.
	 *
	 * @param userId the user id
	 * @return the user by id
	 * @throws Exception the exception
	 */
	@RequestMapping("/getUserById/{userId}")
	public User getUserById(@PathVariable(name="userId")String userId ) throws Exception
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: UserController ");
		log.debug("Inside Method Name: getUserById ");
		log.debug("User Id Searched for: " + userId);
		log.debug("===========================================");
		User user = null;
		user = userService.findUserById(Long.parseLong(userId));
		return user;
	}
    
	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws Exception the exception
	 */
	@PostMapping("/updateUser")
	public User updateUser(@RequestBody User user) throws Exception  {

		log.debug("===========================================");
		log.debug("Inside Class Name: UserController");
		log.debug("Inside Method Name: updateUser ");
		log.debug("===========================================");
	    userService.addUser(user);
		return  user;
	}
}
