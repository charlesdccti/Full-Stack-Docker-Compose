package com.fsd.backend.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fsd.backend.Entity.Project;
import com.fsd.backend.Entity.User;
import com.fsd.backend.Service.ProjectService;
import com.fsd.backend.Service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectController.
 */
/**
 * @author paiprasadm
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/taskManager/projectAction")
public class ProjectController {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	/** The project service. */
	@Autowired
	private ProjectService projectService;
	
	/**
	 * Adds the project.
	 *
	 * @param project the project
	 * @return the project
	 * @throws Exception the exception
	 */
	@PostMapping("/addProject")
	public Project addProject(@RequestBody Project project) throws Exception
	{
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Controller ");
		log.debug("Inside Method Name: addProject ");
		log.debug("Project Details to be added:" + project.toString());
		log.debug("===========================================");
        projectService.addProject(project);
		return project;
	}
	

	/**
	 * Gets the all project.
	 *
	 * @return the all project
	 * @throws Exception the exception
	 */
	@RequestMapping("/getAllProject")
	public List<Project> getAllProject() throws Exception
	{
		List<Project> projectList = null;
	
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Controller ");
		log.debug("Inside Method Name: getAllProject ");
		log.debug("===========================================");
		projectList=projectService.getAllProject();
		return  projectList;
	}
	
	
	/**
	 * Delete project.
	 *
	 * @param project the project
	 * @return the project
	 * @throws Exception the exception
	 */
	@RequestMapping("/deleteProject")
	public Project deleteProject(@RequestBody Project project) throws Exception
	{
		Project returned = null;
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Controller ");
		log.debug("Inside Method Name: deleteProject ");
		log.debug("===========================================");
		project.setUser(null);
		returned=projectService.deleteProject(project);
		return  returned;
	}
	
	
	/**
	 * Gets the project by id.
	 *
	 * @param projectId the project id
	 * @return the project by id
	 * @throws Exception the exception
	 */
	@RequestMapping("/getProjectById/{projectId}")
	public Project getProjectById(@PathVariable(name="projectId")String projectId ) throws Exception
	{
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Controller ");
		log.debug("Inside Method Name: getProjectById ");
		log.debug("Project Id Searched for: " + projectId);
		log.debug("===========================================");
		Project project = null;
		project = projectService.findProjectById(Long.parseLong(projectId));
		return project;
		
	}

	/**
	 * Update project.
	 *
	 * @param project the project
	 * @return the project
	 * @throws Exception the exception
	 */
	@PostMapping("/updateProject")
	private Project updateProject(@RequestBody Project project) throws Exception{

		log.debug("===========================================");
		log.debug("Inside Class Name: Project Controller ");
		log.debug("Inside Method Name: updateProject ");
		log.debug("===========================================");
		projectService.addProject(project);
		return  project;
	}


}
