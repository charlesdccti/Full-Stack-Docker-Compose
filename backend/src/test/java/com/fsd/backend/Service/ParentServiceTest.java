package com.fsd.backend.Service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fsd.backend.Entity.ParentTask;
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Repository.ParentTaskRepo;

// TODO: Auto-generated Javadoc
/**
 * The Class ParentServiceTest.
 *
 * @author paiprasadm
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParentServiceTest {
	
	/** The parent service. */
	@Autowired
	private ParentService parentService;
	
	/** The parentrepo. */
	@MockBean
	private ParentTaskRepo parentrepo;
	
    /** The parent task. */
    private ParentTask parentTask = null;
    
    /** The parent task const. */
    private ParentTask parentTaskConst = null;
	
	/** The parent task string. */
	@Value("${testcase.parentTask}")
	private String parentTaskString;
	
	/** The parent list. */
	private List<ParentTask> parentList = new ArrayList();

	/**
	 * Creates the test data.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void createTestData() throws IOException
	{
		parentTask = new ParentTask();
		parentTask = createObject(parentTaskString);
		parentTaskConst = new ParentTask(parentTask.getParentTaskId(), parentTask.getParentTask());
		parentList.add(parentTask);
		parentList.add(parentTask);
	}
	
	/**
	 * Test add parent.
	 */
	@Test
	public void testAddParent() {
		Mockito.when((parentrepo.save(parentTask))).thenReturn(parentTask);
		assertEquals(parentService.addParentTask(parentTask).getParentTask(), parentTask.getParentTask());
	}
	
	/**
	 * Testget all parents.
	 */
	@Test
	public void testgetAllParents() {
		Mockito.when(parentrepo.findAll()).thenReturn(parentList);
		assertEquals(parentService.getAllParent().get(0).getParentTask(), parentList.get(0).getParentTask());
	}
	
	/**
	 * Creates the object.
	 *
	 * @param json the json
	 * @return the parent task
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private ParentTask createObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.readValue(json,ParentTask.class);
		
	}
}
