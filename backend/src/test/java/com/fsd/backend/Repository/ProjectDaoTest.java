package com.fsd.backend.Repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Repository.ProjectRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectDaoTest.
 */
/**
 * @author paiprasadm
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableTransactionManagement
public class ProjectDaoTest {
	
/** The log. */
private final Logger log = LoggerFactory.getLogger(ProjectDaoTest.class);
	
	/** The em. */
	@Autowired
	private TestEntityManager em;
	
	/** The project repo. */
	@Autowired
	private ProjectRepository projectRepo; 
	
	
	/** The project. */
	private Project project = null;
	
	/** The return project. */
	private Project returnProject = null;
	
	/** The project string. */
	@Value("${testcase.projectDao}")
	private String projectString;
	
	
	/**
	 * Creates the project.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void createProject() throws IOException
	{
		project = new Project();
        project = createObject(projectString);
	}
	
	
	
	/**
	 * Test add project.
	 */
	@Test
    @Rollback(false)
   	public void testAddProject()
    {
		returnProject=projectRepo.save(project);
 	    assertEquals(project.getPriority(), project.getPriority());
	}
	
	/**
	 * Testfind project by id.
	 */
	@Test
	@Rollback(false)
	public void testfindProjectById()
	{
		returnProject=projectRepo.save(project);
    	Optional<Project> retProject = projectRepo.findById(returnProject.getProjectId());
    	assertEquals(retProject.get().getPriority(), project.getPriority());
	}
	
	/**
	 * Test update project.
	 */
	@Test
    @Rollback(false)
   	public void testUpdateProject()
    {
		returnProject=projectRepo.save(project);
 	   assertEquals(returnProject.getPriority(), project.getPriority());
	}
	
	/**
	 * Creates the object.
	 *
	 * @param json the json
	 * @return the project
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Project createObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.readValue(json,Project.class);
		
	}
}
