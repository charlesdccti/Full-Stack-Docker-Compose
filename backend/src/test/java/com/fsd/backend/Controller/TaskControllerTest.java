package com.fsd.backend.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fsd.backend.Entity.ParentTask;
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Entity.Task;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Service.TaskService;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskControllerTest.
 *
 * @author paiprasadm
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value=TaskController.class)
public class TaskControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;
	
	/** The task service. */
	@MockBean
	private TaskService taskService;
	
	/** The taskcontroller. */
	@Autowired
	private TaskController taskcontroller;
	
	/** The task string. */
	@Value("${testcase.task}")
	private String taskString;
	
	/** The task. */
	private Task task = null;
	
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
        taskList.add(task);
        taskList.add(task);
	}
	
	/**
	 * Adds the task.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addTask() throws Exception {
		String expectedJson = createJson(task);
		String restURI = "/taskManager/taskAction/addTask";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Task retTask = createObject(outJson);
        assertEquals(retTask.getPriority(), retTask.getPriority());
	}
	
	/**
	 * Gets the task.
	 *
	 * @return the task
	 * @throws Exception the exception
	 */
	@Test
	public void getTask() throws Exception {
		String expectedJson = createJson(task);
		String restURI = "/taskManager/taskAction/getTaskById/" + Long.toString(task.getTaskId());
        String outJson = returnExpectedJson(expectedJson,restURI);
        Task retTask = createObject(outJson);
        assertEquals(retTask.getPriority(), retTask.getPriority());
	}
	
	/**
	 * Delete task.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteTask() throws Exception {
		String expectedJson = createJson(task);
		String restURI = "/taskManager/taskAction/deleteTask";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Task retTask = createObject(outJson);
        assertEquals(retTask.getPriority(), retTask.getPriority());
	}
	
	/**
	 * Update task.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateTask() throws Exception {
		String expectedJson = createJson(task);
		String restURI = "/taskManager/taskAction/updateTask";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Task retTask = createObject(outJson);
        assertEquals(retTask.getPriority(), retTask.getPriority());
	}
	
	/**
	 * Gets the all task.
	 *
	 * @return the all task
	 * @throws Exception the exception
	 */
	@Test
	public void getAllTask() throws Exception {
	 
		Mockito.when(taskService.getAllTask()).thenReturn(taskList);
		List<Task> actual = taskcontroller.getAllTask();
		verify(taskService,times(1)).getAllTask();
		verifyNoMoreInteractions(taskService);
	}
	
	
	/**
	 * Return expected json.
	 *
	 * @param expectedJson the expected json
	 * @param restURI the rest URI
	 * @return the string
	 * @throws Exception the exception
	 */
	private String returnExpectedJson(String expectedJson, String restURI) throws Exception
	{
		
		Mockito.when(taskService.findTaskById((Mockito.any(Long.class)))).thenReturn(task);
		Mockito.when(taskService.addTask(Mockito.any(Task.class))).thenReturn(task);
		Mockito.when(taskService.deleteTask((Mockito.any(Task.class)))).thenReturn(task);
		
	        RequestBuilder reqBuilder = MockMvcRequestBuilders.post(restURI)
	        		                                          .accept(MediaType.APPLICATION_JSON)
	        		                                          .content(expectedJson)
	        		                                          .contentType(MediaType.APPLICATION_JSON);
	       MvcResult mvcResult= mockMvc.perform(reqBuilder).andReturn();
	       MockHttpServletResponse mockresponse = mvcResult.getResponse();
	       return mockresponse.getContentAsString(); 
	}
	
	/**
	 * Creates the json.
	 *
	 * @param object the object
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	private String createJson(Object object) throws JsonProcessingException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.writeValueAsString(object);
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
