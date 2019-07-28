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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Repository.ProjectRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectServiceTest.
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
public class ProjectServiceTest {

	/** The project service. */
	@Autowired
	private ProjectService projectService;
	
	/** The projectrepo. */
	@MockBean
	private ProjectRepository projectrepo;
	
    /** The project. */
    private Project project = null;
    
    /** The project const. */
    private Project projectConst = null;
	
	/** The project string. */
	@Value("${testcase.project}")
	private String projectString;
	
	/** The project list. */
	private List<Project> projectList = new ArrayList();
	

	
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
        projectConst = new Project(project.getProjectId(), project.getProject(), project.getStartDate(), project.getEndDate(), project.getPriority(),null, null);
        projectList.add(project);
        projectList.add(project);
	}
	
	/**
	 * Test add project.
	 */
	@Test
	public void testAddProject() {
		Mockito.when((projectrepo.save(project))).thenReturn(project);
		assertEquals(projectService.addProject(project).getPriority(), project.getPriority());
	}
	
	/**
	 * Test find project by id.
	 */
	@Test
	public void testFindProjectById() {
		Mockito.when((projectrepo.findById(project.getProjectId()))).thenReturn(Optional.of(project));
		assertEquals(projectService.findProjectById(project.getProjectId()).getPriority(), project.getPriority());
	}
	
	/**
	 * Test negative for project id.
	 */
	@Test
	public void testNegativeForProjectId()
	{
		Mockito.when((projectrepo.findById(project.getProjectId()))).thenReturn(null);
		Project projectDummy = projectService.findProjectById(project.getProjectId());
		assertEquals(projectDummy, null);
	}
	
	/**
	 * Test update project.
	 */
	@Test
	public void testUpdateProject() {
		Mockito.when((projectrepo.save(project))).thenReturn(project);
		assertEquals(projectService.addProject(project).getPriority(), project.getPriority());
	}
	
	/**
	 * Testget all projects.
	 */
	@Test
	public void testgetAllProjects() {
		Mockito.when(projectrepo.findAll()).thenReturn(projectList);
		assertEquals(projectService.getAllProject().get(0).getPriority(), projectList.get(0).getPriority());
	}
	
	/**
	 * Testget delete projects.
	 */
	@Test
	public void testgetDeleteProjects() {
	    Mockito.doNothing().when(projectrepo).delete(project);
		projectService.deleteProject(project);
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
