package com.fsd.backend.Service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDate;
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
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fsd.backend.Entity.ParentTask;
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Entity.Task;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Repository.TaskRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskServiceTest.
 *
 * @author paiprasadm
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
	
	/** The task service. */
	@Autowired
	private TaskService taskService;
	
	/** The taskrepo. */
	@MockBean
	private TaskRepository taskrepo;
	
	/** The task string. */
	@Value("${testcase.task}")
	private String taskString;
	
	/** The task. */
	private Task task = null;
	
	/** The task constructor. */
	private Task taskConstructor = null;
	
	/** The task list. */
	private List<Task> taskList = new ArrayList();
	
	
	
	/**
	 * Creates the task.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void createTask() throws IOException
	{
        task = new Task();
        task = createObject(taskString);
        taskConstructor = new Task(task.getTaskId(), task.getTask(), task.getPriority(), task.getStartDate(), task.getEndDate(), null, null, null, "completed");
        taskList.add(task);
        taskList.add(task);
	}
	
	/**
	 * Test add task.
	 */
	@Test
	public void testAddTask() {
		Mockito.when((taskrepo.save(task))).thenReturn(task);
		assertEquals(taskService.addTask(task).getPriority(), task.getPriority());
	}
	
	/**
	 * Test find task by id.
	 */
	@Test
	public void testFindTaskById() {
		Mockito.when((taskrepo.findById(task.getTaskId()))).thenReturn(Optional.of(task));
		assertEquals(taskService.findTaskById(task.getTaskId()).getPriority(), task.getPriority());
	}
	
	
	/**
	 * Test find task by id negative.
	 */
	@Test
	public void testFindTaskByIdNegative() {
		Mockito.when((taskrepo.findById(task.getTaskId()))).thenReturn(null);
		Task dummyTask = taskService.findTaskById(task.getTaskId());
		assertEquals(dummyTask,null);
	}
	
	/**
	 * Test update task.
	 */
	@Test
	public void testUpdateTask() {
		Mockito.when((taskrepo.save(task))).thenReturn(task);
		assertEquals(taskService.addTask(task).getPriority(), task.getPriority());
	}
	
	/**
	 * Testget all tasks.
	 */
	@Test
	public void testgetAllTasks() {
		Mockito.when(taskrepo.findAll()).thenReturn(taskList);
		assertEquals(taskService.getAllTask().get(0).getPriority(), taskList.get(0).getPriority());
	}

	/**
	 * Testget delete tasks.
	 */
	@Test
	public void testgetDeleteTasks() {
	    Mockito.doNothing().when(taskrepo).delete(task);
		taskService.deleteTask(task);
	}
	
	/**
	 * Creates the object.
	 *
	 * @param json the json
	 * @return the task
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Task createObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.readValue(json,Task.class);
		
	}

}
