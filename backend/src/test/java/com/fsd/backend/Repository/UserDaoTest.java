package com.fsd.backend.Repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Repository.UserRepo;
import com.google.common.collect.Lists;

/**
 * @author paiprasadm
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableTransactionManagement
public class UserDaoTest {
	
	private final Logger log = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private UserRepo userRepo; 
	
	@Value("${testcase.user}")
	private String testUser;
	
	private User user2ConstTest = null; 
	
	private User user = null;
	
	@Before
	public void createUser() throws IOException
	{
		user = new User();
		user = createObject(testUser);
        user2ConstTest = new User(user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmployeeId(), null, null);
	}
	
	
	@Test
    @Rollback(false)
   	public void testAddUser()
    {
 	    User returnUser = null;
 	    returnUser=userRepo.save(user);
 	    assertEquals(returnUser.getEmployeeId(), user.getEmployeeId());
	}
	
	@Test
    @Rollback(false)
   	public void testUpdateUser()
    {
 	    User returnUser = null;
 	    returnUser=userRepo.save(user);
 	    assertEquals(returnUser.getEmployeeId(), user.getEmployeeId());
	}
 
	
 	@Test
	public void testfindUserById()
	{
 		User returnUser=userRepo.save(user);
    	Optional<User> retUser = userRepo.findById(returnUser.getUserId());
    	assertEquals(retUser.get().getEmployeeId(),user.getEmployeeId());
	}
 	
 	private User createObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.readValue(json,User.class);
		
	}
 
}
