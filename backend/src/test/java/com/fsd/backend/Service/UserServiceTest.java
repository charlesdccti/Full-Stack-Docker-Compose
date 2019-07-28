package com.fsd.backend.Service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Repository.UserRepo;
import com.fsd.backend.Service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceTest.
 */
/**
 * @author paiprasadm
 *
 */
/**
 * @author paiprasadm
 *
 */
/**
 * @author paiprasadm
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The urepo. */
	@MockBean
	private UserRepo urepo;
	
	/** The user. */
	@Value("${testcase.user}")
	private String user;
	
	/** The test user. */
	private User testUser = null;
	
	/** The user list. */
	private List<User> userList = new ArrayList();
	
	/**
	 * Adds the user details.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void addUserDetails() throws IOException
	{
		testUser = new User();
		testUser = createObject(user);
		userList.add(testUser);
		userList.add(testUser);
	}
	
	/**
	 * Test add user.
	 */
	@Test
	public void testAddUser() {
		Mockito.when((urepo.save(testUser))).thenReturn(testUser);
		assertEquals(userService.addUser(testUser).getEmployeeId(), testUser.getEmployeeId());
	}
	
	/**
	 * Test find user by id.
	 */
	@Test
	public void testFindUserById() {
		Mockito.when((urepo.findById(testUser.getUserId()))).thenReturn(Optional.of(testUser));
		assertEquals(userService.findUserById(testUser.getUserId()).getEmployeeId(), testUser.getEmployeeId());
	}
	
	/**
	 * Test find user by id negative.
	 */
	@Test
	public void testFindUserByIdNegative() {
		Mockito.when((urepo.findById(testUser.getUserId()))).thenReturn(null);
		User userDummy = userService.findUserById(testUser.getUserId());
		assertEquals(userDummy, null);
	}
	
	/**
	 * Testget all users.
	 */
	@Test
	public void testgetAllUsers() {
		Mockito.when(urepo.findAll()).thenReturn(userList);
		assertEquals(userService.getAllUsers().get(0).getEmployeeId(), userList.get(0).getEmployeeId());
	}
	
	/**
	 * Test update user.
	 */
	@Test
	public void testUpdateUser() {
		Mockito.when((urepo.save(testUser))).thenReturn(testUser);
		assertEquals(userService.addUser(testUser).getEmployeeId(), testUser.getEmployeeId());
	}
	
	/**
	 * Testget delete users.
	 */
	@Test
	public void testgetDeleteUsers() {
	    Mockito.doNothing().when(urepo).delete(testUser);
		userService.deleteUser(testUser);
	}
	
	/**
	 * Creates the object.
	 *
	 * @param json the json
	 * @return the user
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private User createObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.readValue(json,User.class);
		
	}
	
}
