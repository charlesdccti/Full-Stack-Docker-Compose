package com.fsd.backend.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Service.ProjectService;
import com.fsd.backend.Service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectControllerTest.
 *
 * @author paiprasadm
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value=ProjectController.class)
public class ProjectControllerTest {
	
	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;
	
	/** The project service. */
	@MockBean
	private ProjectService projectService;
	
	/** The project. */
	private Project project = null;
	
	
	/** The project list. */
	private List<Project> projectList = new ArrayList();
	
	/** The project controller. */
	@Autowired
	private ProjectController projectController;
	
	
	/** The project string. */
	@Value("${testcase.project}")
	private String projectString;
	
	/**
	 * Creates the test data.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void createTestData() throws IOException
	{
         project = new Project();
         project = createObject(projectString);
         projectList.add(project);
         projectList.add(project);
	}

	
	/**
	 * Adds the project.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addProject() throws Exception {
		String expectedJson = createJson(project);
		String restURI = "/taskManager/projectAction/addProject";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Project retProject = createObject(outJson);
        assertEquals(retProject.getPriority(), project.getPriority());
	}
	
	/**
	 * Gets the project.
	 *
	 * @return the project
	 * @throws Exception the exception
	 */
	@Test
	public void getProject() throws Exception {
		String expectedJson = createJson(project);
		String restURI = "/taskManager/projectAction/getProjectById/" + Long.toString(project.getProjectId());
        String outJson = returnExpectedJson(expectedJson,restURI);
        Project retProject = createObject(outJson);
        assertEquals(retProject.getPriority(), project.getPriority());
	}
	
	
	/**
	 * Update project.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateProject() throws Exception {
		String expectedJson = createJson(project);
		String restURI = "/taskManager/projectAction/updateProject";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Project retProject = createObject(outJson);
        assertEquals(retProject.getPriority(), project.getPriority());
	}
	
	@Test
	public void deleteProject() throws Exception {
	 
		String expectedJson = createJson(project);
		String restURI = "/taskManager/projectAction/deleteProject";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Project retProject = createObject(outJson);
        assertEquals(retProject.getPriority(), project.getPriority());
	}
	
	/**
	 * Gets the all project.
	 *
	 * @return the all project
	 * @throws Exception the exception
	 */
	@Test
	public void getAllProject() throws Exception {
	 
		Mockito.when(projectService.getAllProject()).thenReturn(projectList);
		List<Project> actual = projectController.getAllProject();
		verify(projectService,times(1)).getAllProject();
		verifyNoMoreInteractions(projectService);
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
		
		Mockito.when(projectService.findProjectById((Mockito.any(Long.class)))).thenReturn(project);
		Mockito.when(projectService.addProject(Mockito.any(Project.class))).thenReturn(project);
		Mockito.when(projectService.deleteProject((Mockito.any(Project.class)))).thenReturn(project);
		
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
